/*
 * Copyright 2025 Nikan Radan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.smushytaco.lwjgl_gradle

import com.smushytaco.lwjgl_gradle.LwjglExtension.Companion.GROUP
import org.apache.maven.artifact.versioning.ComparableVersion
import org.gradle.api.Named
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory
import org.gradle.kotlin.dsl.maven
import java.util.*
import javax.inject.Inject

/**
 * Gradle extension used to configure LWJGL dependencies for a project.
 *
 * This extension is registered by [LwjglPlugin] under the name `"lwjgl"` and
 * exposes a small DSL for:
 *
 * - Choosing the LWJGL version via [version].
 * - Controlling how native artifacts are resolved via [usePredefinedPlatforms]
 *   and [platforms].
 * - Adding LWJGL modules and presets to `implementation` / `testImplementation`
 *   via the [implementation] and [testImplementation] helper functions.
 *
 * @param objects
 * The Gradle [ObjectFactory] used to create lazy [Property] and [ListProperty]
 * instances that are compatible with the configuration cache.
 *
 * @param providers
 * The Gradle [ProviderFactory] used to lazily inspect system properties and
 * derive the current [runningPlatform] in a configuration-cache-friendly way.
 *
 * @param dependencies
 * The project's [DependencyHandler] used internally to register the resolved
 * LWJGL dependencies on the appropriate configurations.
 *
 * @param repositories
 * The project's [RepositoryHandler] used internally to register the LWJGL
 * snapshot Maven repository when snapshot versions are in use.
 */
@Suppress("unused")
abstract class LwjglExtension @Inject constructor(objects: ObjectFactory, providers: ProviderFactory, private val dependencies: DependencyHandler, private val repositories: RepositoryHandler) {
    /**
     * Holds constants used by [LwjglExtension].
     *
     * This companion currently exposes the LWJGL Maven group id [GROUP] that is
     * applied to all resolved artifacts.
     */
    companion object {
        /**
         * Maven group identifier used for all LWJGL artifacts.
         *
         * All module coordinates are built as:
         * `"$GROUP:${Module.artifact}:${version}"`.
         */
        private const val GROUP = "org.lwjgl"
        private const val CURRENT_LATEST_VERSION = "3.3.6"
    }
    /**
     * LWJGL version to use for all resolved artifacts.
     *
     * The value is interpreted as a standard Maven/Gradle version string
     * (for example `"3.3.6"` or `"3.4.0-SNAPSHOT"`). It is applied to the core
     * `org.lwjgl:lwjgl` artifact as well as all selected module artifacts and
     * their native classifiers.
     *
     * The default is `"3.3.6"`.
     */
    val version: Property<String> = objects.property(String::class.java).convention(CURRENT_LATEST_VERSION)
    /**
     * Controls whether native artifacts should be resolved using [platforms].
     *
     * When set to `true`, the plugin will pull LWJGL natives for *every* platform
     * listed in [platforms] (e.g. `natives-linux`, `natives-macos`, …).
     *
     * When set to `false`, the plugin will infer the current host platform using
     * [runningPlatform] and only add natives for that single platform.
     *
     * The default is `false`.
     */
    val usePredefinedPlatforms: Property<Boolean> = objects.property(Boolean::class.java).convention(false)
    /**
     * List of LWJGL native classifiers to resolve when [usePredefinedPlatforms]
     * is enabled.
     *
     * Each entry corresponds to the suffix part of a LWJGL native artifact
     * classifier, for example:
     *
     * - `"linux"` → `natives-linux`
     * - `"linux-arm64"` → `natives-linux-arm64`
     * - `"windows-x86"` → `natives-windows-x86`
     *
     * By default, this contains all native LWJGL targets:
     *
     * - `linux-ppc64le`, `linux-riscv64`, `linux-arm64`, `linux-arm32`, `linux`
     * - `macos-arm64`, `macos`
     * - `windows-arm64`, `windows`, `windows-x86`
     * - `freebsd`
     *
     * You can override this list to customize exactly which native artifacts
     * are pulled in when [usePredefinedPlatforms] is `true`.
     */
    val platforms: ListProperty<String> = objects.listProperty(String::class.java).convention(listOf(
        "linux-ppc64le", "linux-riscv64", "linux-arm64", "linux-arm32", "linux",
        "macos-arm64", "macos",
        "windows-arm64", "windows", "windows-x86",
        "freebsd"
    ))
    /**
     * Lazily computes the current host platform classifier for LWJGL natives.
     *
     * This provider inspects the `os.name` and `os.arch` system properties via
     * [ProviderFactory.systemProperty] in a configuration-cache-friendly way,
     * and normalizes them to one of LWJGL's expected classifier bases, such as:
     *
     * - `"linux"` / `"linux-arm32"` / `"linux-arm64"`
     * - `"macos"` / `"macos-arm64"`
     * - `"windows"` / `"windows-x86"` / `"windows-arm64"`
     *
     * The resulting value is combined with the `"natives-"` prefix when
     * [usePredefinedPlatforms] is `false`, so that only the natives matching the
     * current host platform are added.
     *
     * If the operating system cannot be recognized, this will throw an error and
     * instruct the user to enable [usePredefinedPlatforms] and configure
     * [platforms] explicitly.
     */
    val runningPlatform: Provider<String> =
        providers.systemProperty("os.name")
            .zip(providers.systemProperty("os.arch")) { osName, arch ->
                val os = osName.lowercase(Locale.ROOT).trim()
                val archNorm = arch.lowercase(Locale.ROOT).trim()

                val isAarch64 = archNorm == "aarch64" || archNorm == "arm64"

                return@zip when {
                    "linux" in os -> {
                        val suffix = when {
                            // ARM / AArch64
                            archNorm.startsWith("arm") || isAarch64 ->
                                if ("64" in archNorm || archNorm.startsWith("armv8"))
                                    "arm64"
                                else
                                    "arm32"
                            // PowerPC 64 little-endian
                            archNorm == "ppc64le" -> "ppc64le"
                            // RISC-V 64
                            archNorm == "riscv64" -> "riscv64"
                            else -> ""
                        }
                        "linux" + if (suffix.isNotEmpty()) "-$suffix" else ""
                    }
                    os.startsWith("mac") || os.contains("darwin") -> "macos" + if (isAarch64) "-arm64" else ""
                    os.startsWith("windows") ->
                        "windows" + when {
                            "64" in archNorm -> if (isAarch64) "-arm64" else ""
                            else -> "-x86"
                        }
                    os.contains("freebsd") -> {
                        if (archNorm == "amd64" || archNorm == "x86_64") {
                            "freebsd"
                        } else {
                            error(
                                "FreeBSD architecture '$arch' is not supported by LWJGL natives. " +
                                        "Set LWJGL natives explicitly by enabling `usePredefinedPlatforms` " +
                                        "and customizing `platforms`."
                            )
                        }
                    }
                    // --- Fallback ------------------------------------------------
                    else -> error(
                        "Unrecognized or unsupported operating system '$osName' / arch '$arch'. " +
                                "Set LWJGL natives explicitly by enabling `usePredefinedPlatforms` and " +
                                "customizing `platforms`."
                    )
                }
            }
    /**
     * URL of the Maven repository to use for resolving LWJGL snapshot versions.
     *
     * This repository is added automatically when [autoAddSnapshotRepository] is
     * enabled and the configured LWJGL [version] ends with `-SNAPSHOT`.
     *
     * Defaults to the Maven Central Snapshots repository on central.sonatype.com.
     */
    val snapshotRepositoryUrl: Property<String> = objects.property(String::class.java).convention("https://central.sonatype.com/repository/maven-snapshots/")

    /**
     * The name assigned to the automatically-added snapshot repository.
     *
     * This name is applied to the repository created when
     * [autoAddSnapshotRepository] is `true` and the LWJGL [version] is a snapshot.
     *
     * Defaults to `"mavenCentralSnapshots"`.
     */
    val snapshotRepositoryName: Property<String> = objects.property(String::class.java).convention("mavenCentralSnapshots")

    /**
     * Whether the plugin should automatically add a snapshot repository when the
     * configured LWJGL [version] ends with `-SNAPSHOT`.
     *
     * When enabled (the default), the plugin registers a Maven repository using
     * [snapshotRepositoryUrl] and [snapshotRepositoryName] so that snapshot LWJGL
     * artifacts can be resolved without additional user configuration.
     */
    val autoAddSnapshotRepository: Property<Boolean> = objects.property(Boolean::class.java).convention(true)

    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION implementation} {@set PHRASE A collection of} {@set NATIVES Target configuration for natives, or null to disable them.} */
    fun implementation(entries: Iterable<LwjglEntry>, wireNativesTo: String? = "runtimeOnly") = addModules("implementation", wireNativesTo, entries)
    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION implementation} {@set PHRASE A collection of} {@set NATIVES Configuration whose name natives are added to.} */
    fun implementation(entries: Iterable<LwjglEntry>, wireNativesTo: Named) = implementation(entries, wireNativesTo.name)
    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION implementation} {@set PHRASE One or more} {@set NATIVES Target configuration for natives, or null to disable them.} */
    fun implementation(vararg entries: LwjglEntry, wireNativesTo: String? = "runtimeOnly") = implementation(entries.toList(), wireNativesTo)
    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION implementation} {@set PHRASE One or more} {@set NATIVES Configuration whose name natives are added to.} */
    fun implementation(vararg entries: LwjglEntry, wireNativesTo: Named) = implementation(entries.toList(), wireNativesTo)

    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION testImplementation} {@set PHRASE A collection of} {@set NATIVES Target configuration for natives, or null to disable them.} */
    fun testImplementation(entries: Iterable<LwjglEntry>, wireNativesTo: String? = "testRuntimeOnly") = addModules("testImplementation", wireNativesTo, entries)
    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION testImplementation} {@set PHRASE A collection of} {@set NATIVES Configuration whose name natives are added to.} */
    fun testImplementation(entries: Iterable<LwjglEntry>, wireNativesTo: Named) = testImplementation(entries, wireNativesTo.name)
    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION testImplementation} {@set PHRASE One or more} {@set NATIVES Target configuration for natives, or null to disable them.} */
    fun testImplementation(vararg entries: LwjglEntry, wireNativesTo: String? = "testRuntimeOnly") = testImplementation(entries.toList(), wireNativesTo)
    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION testImplementation} {@set PHRASE One or more} {@set NATIVES Configuration whose name natives are added to.} */
    fun testImplementation(vararg entries: LwjglEntry, wireNativesTo: Named) = testImplementation(entries.toList(), wireNativesTo)

    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION api} {@set PHRASE A collection of} {@set NATIVES Target configuration for natives, or null to disable them.} */
    fun api(entries: Iterable<LwjglEntry>, wireNativesTo: String? = "runtimeOnly") = addModules("api", wireNativesTo, entries)
    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION api} {@set PHRASE A collection of} {@set NATIVES Configuration whose name natives are added to.} */
    fun api(entries: Iterable<LwjglEntry>, wireNativesTo: Named) = api(entries, wireNativesTo.name)
    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION api} {@set PHRASE One or more} {@set NATIVES Target configuration for natives, or null to disable them.} */
    fun api(vararg entries: LwjglEntry, wireNativesTo: String? = "runtimeOnly") = api(entries.toList(), wireNativesTo)
    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION api} {@set PHRASE One or more} {@set NATIVES Configuration whose name natives are added to.} */
    fun api(vararg entries: LwjglEntry, wireNativesTo: Named) = api(entries.toList(), wireNativesTo)

    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION testApi} {@set PHRASE A collection of} {@set NATIVES Target configuration for natives, or null to disable them.} */
    fun testApi(entries: Iterable<LwjglEntry>, wireNativesTo: String? = "testRuntimeOnly") = addModules("testApi", wireNativesTo, entries)
    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION testApi} {@set PHRASE A collection of} {@set NATIVES Configuration whose name natives are added to.} */
    fun testApi(entries: Iterable<LwjglEntry>, wireNativesTo: Named) = testApi(entries, wireNativesTo.name)
    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION testApi} {@set PHRASE One or more} {@set NATIVES Target configuration for natives, or null to disable them.} */
    fun testApi(vararg entries: LwjglEntry, wireNativesTo: String? = "testRuntimeOnly") = testApi(entries.toList(), wireNativesTo)
    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION testApi} {@set PHRASE One or more} {@set NATIVES Configuration whose name natives are added to.} */
    fun testApi(vararg entries: LwjglEntry, wireNativesTo: Named) = testApi(entries.toList(), wireNativesTo)

    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION compileOnly} {@set PHRASE A collection of} {@set NATIVES Target configuration for natives, or null to disable them.} */
    fun compileOnly(entries: Iterable<LwjglEntry>, wireNativesTo: String? = null) = addModules("compileOnly", wireNativesTo, entries)
    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION compileOnly} {@set PHRASE A collection of} {@set NATIVES Configuration whose name natives are added to.} */
    fun compileOnly(entries: Iterable<LwjglEntry>, wireNativesTo: Named) = compileOnly(entries, wireNativesTo.name)
    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION compileOnly} {@set PHRASE One or more} {@set NATIVES Target configuration for natives, or null to disable them.} */
    fun compileOnly(vararg entries: LwjglEntry, wireNativesTo: String? = null) = compileOnly(entries.toList(), wireNativesTo)
    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION compileOnly} {@set PHRASE One or more} {@set NATIVES Configuration whose name natives are added to.} */
    fun compileOnly(vararg entries: LwjglEntry, wireNativesTo: Named) = compileOnly(entries.toList(), wireNativesTo)

    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION testCompileOnly} {@set PHRASE A collection of} {@set NATIVES Target configuration for natives, or null to disable them.} */
    fun testCompileOnly(entries: Iterable<LwjglEntry>, wireNativesTo: String? = null) = addModules("testCompileOnly", wireNativesTo, entries)
    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION testCompileOnly} {@set PHRASE A collection of} {@set NATIVES Configuration whose name natives are added to.} */
    fun testCompileOnly(entries: Iterable<LwjglEntry>, wireNativesTo: Named) = testCompileOnly(entries, wireNativesTo.name)
    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION testCompileOnly} {@set PHRASE One or more} {@set NATIVES Target configuration for natives, or null to disable them.} */
    fun testCompileOnly(vararg entries: LwjglEntry, wireNativesTo: String? = null) = testCompileOnly(entries.toList(), wireNativesTo)
    /** @includeFile (../../../../../../kdoc_function.txt) {@set CONFIGURATION testCompileOnly} {@set PHRASE One or more} {@set NATIVES Configuration whose name natives are added to.} */
    fun testCompileOnly(vararg entries: LwjglEntry, wireNativesTo: Named) = testCompileOnly(entries.toList(), wireNativesTo)

    /** @includeFile (../../../../../../kdoc_runtime_function.txt) {@set CONFIGURATION runtimeOnly} {@set PHRASE A collection of} */
    fun runtimeOnly(entries: Iterable<LwjglEntry>) = addModules(null, "runtimeOnly", entries)
    /** @includeFile (../../../../../../kdoc_runtime_function.txt) {@set CONFIGURATION runtimeOnly} {@set PHRASE One or more} */
    fun runtimeOnly(vararg entries: LwjglEntry) = runtimeOnly(entries.toList())

    /** @includeFile (../../../../../../kdoc_runtime_function.txt) {@set CONFIGURATION testRuntimeOnly} {@set PHRASE A collection of} */
    fun testRuntimeOnly(entries: Iterable<LwjglEntry>) = addModules(null, "testRuntimeOnly", entries)
    /** @includeFile (../../../../../../kdoc_runtime_function.txt) {@set CONFIGURATION testRuntimeOnly} {@set PHRASE One or more} */
    fun testRuntimeOnly(vararg entries: LwjglEntry) = testRuntimeOnly(entries.toList())

    /** @includeFile (../../../../../../kdoc_into_function.txt) {@set PHRASE A collection of} */
    fun into(compileConfiguration: String?, runtimeConfiguration: String?, entries: Iterable<LwjglEntry>) = addModules(compileConfiguration, runtimeConfiguration, entries)
    /** @includeFile (../../../../../../kdoc_into_function.txt) {@set PHRASE A collection of} */
    fun into(compileConfiguration: Named?, runtimeConfiguration: String?, entries: Iterable<LwjglEntry>) = into(compileConfiguration?.name, runtimeConfiguration, entries)
    /** @includeFile (../../../../../../kdoc_into_function.txt) {@set PHRASE A collection of} */
    fun into(compileConfiguration: String?, runtimeConfiguration: Named?, entries: Iterable<LwjglEntry>) = into(compileConfiguration, runtimeConfiguration?.name, entries)
    /** @includeFile (../../../../../../kdoc_into_function.txt) {@set PHRASE A collection of} */
    fun into(compileConfiguration: Named?, runtimeConfiguration: Named?, entries: Iterable<LwjglEntry>) = into(compileConfiguration?.name, runtimeConfiguration?.name, entries)

    /** @includeFile (../../../../../../kdoc_into_function.txt) {@set PHRASE One or more} */
    fun into(compileConfiguration: String?, runtimeConfiguration: String?, vararg entries: LwjglEntry) = into(compileConfiguration, runtimeConfiguration, entries.toList())
    /** @includeFile (../../../../../../kdoc_into_function.txt) {@set PHRASE One or more} */
    fun into(compileConfiguration: Named?, runtimeConfiguration: String?, vararg entries: LwjglEntry) = into(compileConfiguration?.name, runtimeConfiguration, entries.toList())
    /** @includeFile (../../../../../../kdoc_into_function.txt) {@set PHRASE One or more} */
    fun into(compileConfiguration: String?, runtimeConfiguration: Named?, vararg entries: LwjglEntry) = into(compileConfiguration, runtimeConfiguration?.name, entries.toList())
    /** @includeFile (../../../../../../kdoc_into_function.txt) {@set PHRASE One or more} */
    fun into(compileConfiguration: Named?, runtimeConfiguration: Named?, vararg entries: LwjglEntry) = into(compileConfiguration?.name, runtimeConfiguration?.name, entries.toList())

    private var snapshotRepositoryAdded = false

    private fun addModules(compileConfig: String?, runtimeConfig: String?, modules: Iterable<LwjglEntry>) {
        val lwjglVersion = version.get()
        val lwjglComparableVersion = ComparableVersion(lwjglVersion)
        val usePredefinedPlatformList = usePredefinedPlatforms.get()
        var resolvedRunningPlatform: String? = null
        val resolvedPlatforms: List<String> = platforms.get()
        if (!snapshotRepositoryAdded && autoAddSnapshotRepository.get() && lwjglVersion.endsWith("-SNAPSHOT")) {
            snapshotRepositoryAdded = true
            repositories.maven(snapshotRepositoryUrl.get()) {
                name = snapshotRepositoryName.get()
                mavenContent {
                    snapshotsOnly()
                    includeGroup(GROUP)
                }
            }
        }
        fun addModule(module: Module) {
            if (lwjglComparableVersion < ComparableVersion(module.since)) return
            if (compileConfig != null) dependencies.add(compileConfig, "$GROUP:${module.artifact}:$lwjglVersion")

            if (runtimeConfig.isNullOrBlank()) return
            val nativesVersion = if (lwjglComparableVersion > ComparableVersion(CURRENT_LATEST_VERSION)) CURRENT_LATEST_VERSION else lwjglVersion
            val natives = module.versionToNatives[nativesVersion]
            if (natives.isNullOrEmpty()) return
            if (usePredefinedPlatformList) {
                for (platform in resolvedPlatforms) {
                    if (platform !in natives) continue
                    dependencies.add(runtimeConfig, "$GROUP:${module.artifact}:$lwjglVersion:natives-$platform")
                }
            } else {
                if (resolvedRunningPlatform == null) resolvedRunningPlatform = runningPlatform.get()
                if (resolvedRunningPlatform !in natives) return
                dependencies.add(runtimeConfig, "$GROUP:${module.artifact}:$lwjglVersion:natives-$resolvedRunningPlatform")
            }
        }
        val moduleList = arrayListOf<Module>()
        modules.distinct().forEach {
            when(it) {
                is Module -> moduleList.add(it)
                is Preset -> it.modules.forEach { module -> moduleList.add(module) }
            }
        }
        if (Module.CORE !in moduleList) addModule(Module.CORE)
        moduleList.distinct().forEach { addModule(it) }
    }
}
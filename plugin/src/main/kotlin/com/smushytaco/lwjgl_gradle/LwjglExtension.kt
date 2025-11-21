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
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory
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
 */
abstract class LwjglExtension @Inject constructor(objects: ObjectFactory, providers: ProviderFactory, private val dependencies: DependencyHandler) {
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
    private val runningPlatform: Provider<String> =
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
     * Registers LWJGL modules on the `implementation` configuration.
     *
     * The selected modules will:
     * - Always include [Module.CORE] if it is not already present.
     * - Respect each module’s [Module.since] version so that modules newer than
     *   the configured [version] are skipped.
     * - Add the appropriate native artifacts depending on
     *   [usePredefinedPlatforms] and [platforms], or the inferred host platform.
     *
     * This is the main entry point for configuring LWJGL runtime and compile-time
     * dependencies for production code.
     *
     * @param modules
     * A collection of LWJGL [Module] values to register on the
     * `implementation` configuration.
     */
    @Suppress("unused")
    fun implementation(modules: Iterable<Module>) = addModules(false, modules)
    /**
     * Registers LWJGL modules on the `implementation` configuration.
     *
     * The selected modules will:
     * - Always include [Module.CORE] if it is not already present.
     * - Respect each module’s [Module.since] version so that modules newer than
     *   the configured [version] are skipped.
     * - Add the appropriate native artifacts depending on
     *   [usePredefinedPlatforms] and [platforms], or the inferred host platform.
     *
     * This is the main entry point for configuring LWJGL runtime and compile-time
     * dependencies for production code.
     *
     * @param modules
     * One or more LWJGL [Module] values to register on the
     * `implementation` configuration.
     */
    @Suppress("unused")
    fun implementation(vararg modules: Module) = implementation(modules.toList())
    /**
     * Registers LWJGL modules on the `testImplementation` configuration.
     *
     * The selected modules will:
     * - Always include [Module.CORE] if it is not already present.
     * - Respect each module’s [Module.since] version so that modules newer than
     *   the configured [version] are skipped.
     * - Add the appropriate native artifacts depending on
     *   [usePredefinedPlatforms] and [platforms], or the inferred host platform.
     *
     * Use this for LWJGL dependencies that are only required for test sources.
     *
     * @param modules
     * A collection of LWJGL [Module] values to register on the
     * `testImplementation` configuration.
     */
    @Suppress("unused")
    fun testImplementation(modules: Iterable<Module>) = addModules(true, modules)
    /**
     * Registers LWJGL modules on the `testImplementation` configuration.
     *
     * The selected modules will:
     * - Always include [Module.CORE] if it is not already present.
     * - Respect each module’s [Module.since] version so that modules newer than
     *   the configured [version] are skipped.
     * - Add the appropriate native artifacts depending on
     *   [usePredefinedPlatforms] and [platforms], or the inferred host platform.
     *
     * Use this for LWJGL dependencies that are only required for test sources.
     *
     * @param modules
     * One or more LWJGL [Module] values to register on the
     * `testImplementation` configuration.
     */
    @Suppress("unused")
    fun testImplementation(vararg modules: Module) = testImplementation(modules.toList())
    /**
     * Registers LWJGL presets on the `implementation` configuration.
     *
     * Each preset expands to one or more LWJGL [Module] values. The resulting
     * module set will:
     * - Always include [Module.CORE] if it is not already present.
     * - Respect each module’s [Module.since] version so that modules newer than
     *   the configured [version] are skipped.
     * - Add the appropriate native artifacts depending on
     *   [usePredefinedPlatforms] and [platforms], or the inferred host platform.
     *
     * This is a convenient way to express common LWJGL combinations such as
     * “getting started” or “minimal OpenGL” setups.
     *
     * @param presets
     * A collection of LWJGL [Preset] values whose modules should be registered
     * on the `implementation` configuration.
     */
    @JvmName("implementationPreset")
    @Suppress("unused")
    fun implementation(presets: Iterable<Preset>) = addModules(false, presets.flatMap { it.modules })
    /**
     * Registers LWJGL presets on the `implementation` configuration.
     *
     * Each preset expands to one or more LWJGL [Module] values. The resulting
     * module set will:
     * - Always include [Module.CORE] if it is not already present.
     * - Respect each module’s [Module.since] version so that modules newer than
     *   the configured [version] are skipped.
     * - Add the appropriate native artifacts depending on
     *   [usePredefinedPlatforms] and [platforms], or the inferred host platform.
     *
     * This is a convenient way to express common LWJGL combinations such as
     * “getting started” or “minimal OpenGL” setups.
     *
     * @param presets
     * One or more LWJGL [Preset] values whose modules should be registered
     * on the `implementation` configuration.
     */
    @Suppress("unused")
    fun implementation(vararg presets: Preset) = implementation(presets.toList())
    /**
     * Registers LWJGL presets on the `testImplementation` configuration.
     *
     * Each preset expands to one or more LWJGL [Module] values. The resulting
     * module set will:
     * - Always include [Module.CORE] if it is not already present.
     * - Respect each module’s [Module.since] version so that modules newer than
     *   the configured [version] are skipped.
     * - Add the appropriate native artifacts depending on
     *   [usePredefinedPlatforms] and [platforms], or the inferred host platform.
     *
     * Use this for LWJGL dependency presets that are only required for test
     * sources.
     *
     * @param presets
     * A collection of LWJGL [Preset] values whose modules should be registered
     * on the `testImplementation` configuration.
     */
    @JvmName("testImplementationPreset")
    @Suppress("unused")
    fun testImplementation(presets: Iterable<Preset>) = addModules(true, presets.flatMap { it.modules })
    /**
     * Registers LWJGL presets on the `testImplementation` configuration.
     *
     * Each preset expands to one or more LWJGL [Module] values. The resulting
     * module set will:
     * - Always include [Module.CORE] if it is not already present.
     * - Respect each module’s [Module.since] version so that modules newer than
     *   the configured [version] are skipped.
     * - Add the appropriate native artifacts depending on
     *   [usePredefinedPlatforms] and [platforms], or the inferred host platform.
     *
     * Use this for LWJGL dependency presets that are only required for test
     * sources.
     *
     * @param presets
     * One or more LWJGL [Preset] values whose modules should be registered
     * on the `testImplementation` configuration.
     */
    @Suppress("unused")
    fun testImplementation(vararg presets: Preset) = testImplementation(presets.toList())

    private fun addModules(test: Boolean, modules: Iterable<Module>) {
        val lwjglVersion = version.get()
        val lwjglComparableVersion = ComparableVersion(lwjglVersion)
        val usePredefinedPlatformList = usePredefinedPlatforms.get()
        var resolvedRunningPlatform: String? = null
        val resolvedPlatforms: List<String> = platforms.get()
        fun addModule(test: Boolean, module: Module) {
            if (lwjglComparableVersion < ComparableVersion(module.since)) return

            val implementation = if (test) "testImplementation" else "implementation"
            dependencies.add(implementation, "$GROUP:${module.artifact}:$lwjglVersion")

            val nativesVersion = if (lwjglComparableVersion > ComparableVersion(CURRENT_LATEST_VERSION)) CURRENT_LATEST_VERSION else lwjglVersion
            if (module.versionToNatives[nativesVersion].isNullOrEmpty()) return
            val runtimeOnly = if (test) "testRuntimeOnly" else "runtimeOnly"
            if (usePredefinedPlatformList) {
                for (platform in resolvedPlatforms) {
                    if (platform !in module.versionToNatives.getOrDefault(nativesVersion, emptyList())) continue
                    dependencies.add(runtimeOnly, "$GROUP:${module.artifact}:$lwjglVersion:natives-$platform")
                }
            } else {
                if (resolvedRunningPlatform == null) resolvedRunningPlatform = runningPlatform.get()
                if (resolvedRunningPlatform !in module.versionToNatives.getOrDefault(nativesVersion, emptyList())) return
                dependencies.add(runtimeOnly, "$GROUP:${module.artifact}:$lwjglVersion:natives-$resolvedRunningPlatform")
            }
        }
        if (Module.CORE !in modules) addModule(test, Module.CORE)
        modules.distinct().forEach { addModule(test, it) }
    }
}
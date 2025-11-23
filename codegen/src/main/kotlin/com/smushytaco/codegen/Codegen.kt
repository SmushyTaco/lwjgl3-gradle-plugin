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

package com.smushytaco.codegen

import org.apache.maven.artifact.versioning.ComparableVersion
import org.apache.maven.repository.internal.MavenRepositorySystemUtils
import org.eclipse.aether.DefaultRepositorySystemSession
import org.eclipse.aether.RepositorySystem
import org.eclipse.aether.RepositorySystemSession
import org.eclipse.aether.artifact.Artifact
import org.eclipse.aether.artifact.DefaultArtifact
import org.eclipse.aether.repository.LocalRepository
import org.eclipse.aether.repository.RemoteRepository
import org.eclipse.aether.resolution.ArtifactRequest
import org.eclipse.aether.resolution.ArtifactResolutionException
import org.eclipse.aether.resolution.VersionRangeRequest
import org.eclipse.aether.supplier.RepositorySystemSupplier
import org.eclipse.aether.version.Version
import java.nio.file.Files
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory

// This file was AI generated to automate the Module.kt file creation for the plugin.
// This code generation eliminates human error and makes sure that nothing is missed.
data class LwjglMetadata(val artifact: String, val versionToNatives: Map<String, List<String>>)



private fun newRepositorySystem(): RepositorySystem {
    val supplier = RepositorySystemSupplier()
    return supplier.get()
}

private fun newSession(system: RepositorySystem): RepositorySystemSession {
    val session: DefaultRepositorySystemSession = MavenRepositorySystemUtils.newSession()
    val localRepo = LocalRepository("target/local-repo")
    session.localRepositoryManager = system.newLocalRepositoryManager(session, localRepo)
    return session
}

private fun mavenCentral(): RemoteRepository =
    RemoteRepository.Builder(
        "central",
        "default",
        "https://repo1.maven.org/maven2/"
    ).build()

private const val LWJGL_GROUP = "org.lwjgl"
private const val LWJGL_BOM_COORD = "org.lwjgl:lwjgl-bom:3.3.6"

@Suppress("SameParameterValue")
private fun parseCoords(coords: String): Triple<String, String, String> {
    val parts = coords.split(':')
    require(parts.size == 3) { "Expected 'groupId:artifactId:version' but got '$coords'" }
    return Triple(parts[0], parts[1], parts[2])
}

fun findLwjglModulesFromBom(system: RepositorySystem, session: RepositorySystemSession): List<String> {
    val (groupId, artifactId, version) = parseCoords(LWJGL_BOM_COORD)
    val bomArtifact: Artifact = DefaultArtifact(groupId, artifactId, "pom", version)

    val request = ArtifactRequest(bomArtifact, listOf(mavenCentral()), null)
    val result = system.resolveArtifact(session, request)

    val pomPath = result.artifact.path
        ?: error("Failed to resolve BOM POM for $LWJGL_BOM_COORD")
    val pomFile = pomPath.toFile()

    val factory = DocumentBuilderFactory.newInstance()
    val builder = factory.newDocumentBuilder()
    val doc = builder.parse(pomFile)
    doc.documentElement.normalize()

    val nodes = doc.getElementsByTagName("dependency")
    val artifactIds = mutableSetOf<String>()

    for (i in 0 until nodes.length) {
        val dep = nodes.item(i)
        val children = dep.childNodes
        var depGroupId: String? = null
        var depArtifactId: String? = null

        for (j in 0 until children.length) {
            val child = children.item(j)
            when (child.nodeName) {
                "groupId" -> depGroupId = child.textContent.trim()
                "artifactId" -> depArtifactId = child.textContent.trim()
            }
        }

        if (depGroupId == LWJGL_GROUP && depArtifactId != null && depArtifactId.startsWith("lwjgl") && depArtifactId != "lwjgl-platform") {
            artifactIds += depArtifactId
        }
    }

    return artifactIds.sorted()
}

fun createNativesMap(
    system: RepositorySystem,
    session: RepositorySystemSession,
    groupId: String,
    artifactId: String,
    versions: List<Version>
): Map<String, List<String>> {

    val classifiersToTry = listOf(
        "natives-windows",
        "natives-windows-x86",
        "natives-windows-arm64",
        "natives-linux",
        "natives-linux-arm64",
        "natives-linux-arm32",
        "natives-linux-ppc64le",
        "natives-linux-riscv64",
        "natives-macos",
        "natives-macos-arm64",
        "natives-freebsd"
    )

    val result = mutableMapOf<String, List<String>>()

    for (version in versions) {
        val versionStr = version.toString()
        val foundClassifiers = mutableListOf<String>()

        for (classifier in classifiersToTry) {
            val nativeArtifact = DefaultArtifact(groupId, artifactId, classifier, "jar", versionStr)
            val req = ArtifactRequest(nativeArtifact, listOf(mavenCentral()), null)
            try {
                val res = system.resolveArtifact(session, req)
                if (res.artifact?.path != null) {
                    foundClassifiers += classifier.removePrefix("natives-")
                }
            } catch (_: ArtifactResolutionException) {
                // Ignore missing artifacts
            }
        }
        result[versionStr] = foundClassifiers.ifEmpty { emptyList() }
    }

    return result
}


fun collectLwjglMetadata(): List<LwjglMetadata> {
    val system = newRepositorySystem()
    val session = newSession(system)

    val modules = findLwjglModulesFromBom(system, session)

    return modules.map { artifactId ->
        val rangeArtifact = DefaultArtifact("$LWJGL_GROUP:$artifactId:[3.1.0,)")
        val rangeRequest = VersionRangeRequest(rangeArtifact, listOf(mavenCentral()), null)
        val rangeResult = system.resolveVersionRange(session, rangeRequest)

        val natives = createNativesMap(system, session, LWJGL_GROUP, artifactId, rangeResult.versions)

        LwjglMetadata(artifact = artifactId, versionToNatives = natives)
    }
}

fun enumNameFromArtifact(artifact: String): String {
    return if (artifact == "lwjgl") {
        "CORE"
    } else {
        artifact.removePrefix("lwjgl-").uppercase()
    }
}

fun generateModuleEnum(metadata: List<LwjglMetadata>): String {
    val sb = StringBuilder()

    sb.appendLine("package com.smushytaco.lwjgl_gradle")
    sb.appendLine()
    sb.appendLine(
        """
/**
 * Represents an individual LWJGL module that can be added as a Gradle dependency.
 *
 * Each entry corresponds to a published `org.lwjgl:lwjgl-*` artifact on Maven Central`,
 * and is used by the plugin to wire both Java and native dependencies.
 *
 * @property artifact the Maven artifact ID for this LWJGL module.
 * @property since the minimum LWJGL version (inclusive) that provides this module.
 *                 The plugin uses this for version gating, so modules are only added
 *                 when the configured LWJGL version is new enough.
 * @property versionToNatives map of LWJGL version → native classifiers (without the `natives-` prefix).
 */
        """.trimIndent()
    )

    sb.appendLine("@Suppress(\"kotlin:S1192\")")
    sb.appendLine("enum class Module(val artifact: String, val since: String, val versionToNatives: Map<String, List<String>>): LwjglEntry {")

    metadata.forEachIndexed { index, meta ->
        val name = enumNameFromArtifact(meta.artifact)

        sb.appendLine("    $name(")
        sb.appendLine("        \"${meta.artifact}\",")

        val sorted = meta.versionToNatives
            .toSortedMap { a, b -> ComparableVersion(a).compareTo(ComparableVersion(b)) }

        sb.appendLine("        \"${sorted.firstKey()}\",")

        sb.appendLine("        mapOf(")

        sorted.entries.forEachIndexed { i, (version, natives) ->

            sb.append("            \"$version\" to ")

            if (natives.isEmpty()) {
                sb.append("emptyList()")
            } else {
                sb.appendLine("listOf(")
                natives.forEachIndexed { ni, native ->
                    val comma = if (ni == natives.lastIndex) "" else ","
                    sb.appendLine("                \"$native\"$comma")
                }
                sb.append("            )")
            }

            val comma = if (i == sorted.size - 1) "" else ","
            sb.appendLine(comma)
        }

        sb.appendLine("        )")
        sb.append("    )")

        if (index != metadata.lastIndex) sb.appendLine(",") else sb.appendLine()
    }

    sb.appendLine("}")

    return sb.toString()
}

fun main() {
    Files.writeString(Path.of("../plugin/src/main/kotlin/com/smushytaco/lwjgl_gradle/Module.kt"), generateModuleEnum(collectLwjglMetadata()))
}

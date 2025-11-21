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

import org.gradle.testkit.runner.GradleRunner
import java.nio.file.Files
import kotlin.test.Test
import kotlin.test.assertTrue

class LwjglPluginFunctionalTest {
    @Test
    fun `wires lwjgl dependencies`() {
        val testProjectDir = Files.createTempDirectory("lwjgl-plugin-test").toFile()
        testProjectDir.resolve("settings.gradle.kts").writeText(
            "rootProject.name = \"test-lwjgl\""
        )
        testProjectDir.resolve("build.gradle.kts").writeText(
            // language=kotlin
            """
            import com.smushytaco.lwjgl_gradle.Preset
            
            plugins {
                id("com.smushytaco.lwjgl3")
                java
            }

            repositories {
                mavenCentral()
            }

            lwjgl {
                version = "3.3.6"
                implementation(Preset.MINIMAL_OPENGL)
            }
            """.trimIndent()
        )

        val result = GradleRunner.create()
            .withProjectDir(testProjectDir)
            .withPluginClasspath()
            .withArguments("--configuration-cache", "dependencies", "--configuration", "implementation")
            .forwardOutput()
            .build()

        assertTrue(result.output.contains("org.lwjgl:lwjgl"))
    }
    @Test
    fun `wires lwjgl native dependencies`() {
        val testProjectDir = Files.createTempDirectory("lwjgl-plugin-test").toFile()
        testProjectDir.resolve("settings.gradle.kts").writeText(
            "rootProject.name = \"test-lwjgl\""
        )
        testProjectDir.resolve("build.gradle.kts").writeText(
            // language=kotlin
            $$"""
            import com.smushytaco.lwjgl_gradle.Preset
            
            plugins {
                id("com.smushytaco.lwjgl3")
                java
            }

            repositories {
                mavenCentral()
            }

            lwjgl {
                version = "3.3.5"
                usePredefinedPlatforms = true
                implementation(Preset.MINIMAL_OPENGL)
            }
            abstract class PrintArtifactsTask @Inject constructor() : DefaultTask() {
                @get:InputFiles
                @get:Classpath
                abstract val runtimeClasspath: ConfigurableFileCollection
                @TaskAction
                fun printArtifacts() {
                    runtimeClasspath.files.forEach { file ->
                        println("ARTIFACT_FILE: ${file.name}")
                    }
                }
            }
            tasks.register<PrintArtifactsTask>("printArtifacts") {
                runtimeClasspath.from(configurations.runtimeClasspath)
            }
            """.trimIndent()
        )

        val result = GradleRunner.create()
            .withProjectDir(testProjectDir)
            .withPluginClasspath()
            .withArguments("--configuration-cache", "printArtifacts")
            .forwardOutput()
            .build()

        assertTrue(result.output.contains("lwjgl-assimp-3.3.5-natives-linux-arm64.jar"))
        assertTrue(result.output.contains("lwjgl-assimp-3.3.5-natives-linux-arm32.jar"))
        assertTrue(result.output.contains("lwjgl-assimp-3.3.5-natives-linux.jar"))
        assertTrue(result.output.contains("lwjgl-assimp-3.3.5-natives-macos-arm64.jar"))
        assertTrue(result.output.contains("lwjgl-assimp-3.3.5-natives-macos.jar"))
        assertTrue(result.output.contains("lwjgl-assimp-3.3.5-natives-windows-arm64.jar"))
        assertTrue(result.output.contains("lwjgl-assimp-3.3.5-natives-windows.jar"))
        assertTrue(result.output.contains("lwjgl-assimp-3.3.5-natives-windows-x86.jar"))
    }
    // Check https://central.sonatype.com/repository/maven-snapshots/org/lwjgl/lwjgl-stb/maven-metadata.xml
    // for snapshots because after a while snapshots get deleted.
    @Test
    fun `wires lwjgl snapshot dependencies`() {
        val testProjectDir = Files.createTempDirectory("lwjgl-plugin-test").toFile()
        testProjectDir.resolve("settings.gradle.kts").writeText(
            "rootProject.name = \"test-lwjgl\""
        )
        testProjectDir.resolve("build.gradle.kts").writeText(
            // language=kotlin
            $$"""
            import com.smushytaco.lwjgl_gradle.Preset
            import com.smushytaco.lwjgl_gradle.mavenCentralSnapshots
            
            plugins {
                id("com.smushytaco.lwjgl3")
                java
            }

            repositories {
                mavenCentral()
                mavenCentralSnapshots()
            }

            lwjgl {
                version = "3.4.0-SNAPSHOT"
                implementation(Preset.MINIMAL_OPENGL)
            }
            abstract class PrintArtifactsTask @Inject constructor() : DefaultTask() {
                @get:InputFiles
                @get:Classpath
                abstract val runtimeClasspath: ConfigurableFileCollection
                @TaskAction
                fun printArtifacts() {
                    runtimeClasspath.files.forEach { file ->
                        println("ARTIFACT_FILE: ${file.name}")
                    }
                }
            }
            tasks.register<PrintArtifactsTask>("printArtifacts") {
                runtimeClasspath.from(configurations.runtimeClasspath)
            }
            """.trimIndent()
        )

        val result = GradleRunner.create()
            .withProjectDir(testProjectDir)
            .withPluginClasspath()
            .withArguments("--configuration-cache", "printArtifacts")
            .forwardOutput()
            .build()

        assertTrue(result.output.contains("lwjgl-assimp-3.4.0-SNAPSHOT"))
    }
}

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

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Gradle plugin that provides the `lwjgl { ... }` DSL for configuring
 * LWJGL dependencies in a project.
 *
 * Once applied, it registers a [LwjglExtension] named `"lwjgl"` on the
 * target [Project], which can be used from `build.gradle.kts` like:
 *
 * ```
 * import com.smushytaco.lwjgl_gradle.Preset
 *
 * plugins {
 *     id("com.smushytaco.lwjgl3")
 * }
 *
 * lwjgl {
 *     version = "3.3.6"
 *     implementation(Preset.MINIMAL_OPENGL)
 * }
 * ```
 */
class LwjglPlugin : Plugin<Project> {
    /**
     * Applies the LWJGL plugin to the given [project] by registering the
     * [LwjglExtension] under the name `"lwjgl"`.
     *
     * This method is called by Gradle when the plugin is applied, and it wires
     * in the extension with access to the project's [ObjectFactory][org.gradle.api.model.ObjectFactory],
     * [ProviderFactory][org.gradle.api.provider.ProviderFactory],
     * and [DependencyHandler][org.gradle.api.artifacts.dsl.DependencyHandler].
     *
     * @param project The Gradle [Project] to which this plugin is being applied.
     */
    override fun apply(project: Project) {
        project.extensions.create(
            "lwjgl",
            LwjglExtension::class.java,
            project.objects,
            project.providers,
            project.dependencies,
            project.repositories
        )
    }
}

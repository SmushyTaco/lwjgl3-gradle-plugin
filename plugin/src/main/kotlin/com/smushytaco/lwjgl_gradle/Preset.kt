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

import com.smushytaco.lwjgl_gradle.Module.*

/**
 * A predefined collection of LWJGL modules that can be added as a group.
 *
 * Presets provide convenient bundles of commonly used LWJGL modules, such as
 * minimal OpenGL or Vulkan configurations, without requiring users to list
 * each module manually.
 *
 * Example:
 * ```
 * import com.smushytaco.lwjgl_gradle.Preset
 * lwjgl {
 *     implementation(Preset.MINIMAL_OPENGL)
 * }
 * ```
 *
 * @property modules
 * A stable, immutable list of LWJGL [Module] values included in this preset.
 * This list is consumed by the plugin when resolving dependencies.
 */
@Suppress("unused")
enum class Preset(val modules: List<Module>): LwjglEntry {
    NONE(emptyList()),
    EVERYTHING(Module.entries),
    GETTING_STARTED(listOf(CORE, ASSIMP, BGFX, GLFW, NANOVG, NUKLEAR, OPENAL, OPENGL, PAR, STB, VULKAN)),
    MINIMAL_OPENGL(listOf(CORE, ASSIMP, GLFW, OPENAL, OPENGL, STB)),
    MINIMAL_OPENGLES(listOf(CORE, ASSIMP, EGL, GLFW, OPENAL, OPENGLES, STB)),
    MINIMAL_VULKAN(listOf(CORE, ASSIMP, GLFW, OPENAL, STB, VULKAN));
}
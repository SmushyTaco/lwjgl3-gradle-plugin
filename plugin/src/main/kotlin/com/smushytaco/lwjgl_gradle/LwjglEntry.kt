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

/**
 * A sealed interface representing any value that can participate in LWJGL
 * dependency resolution within the LWJGL Gradle plugin.
 *
 * The plugin defines two concrete implementations:
 *
 * - [Module] — describes a single LWJGL module published to Maven
 * - [Preset] — describes a named collection of LWJGL modules for convenience
 *
 * All entry points of the LWJGL DSL (`implementation`, `api`,
 * `compileOnly`, `into`, etc.) accept [LwjglEntry] values. Using a sealed
 * interface allows callers to supply modules and presets interchangeably:
 *
 * ```
 * lwjgl {
 *     implementation(
 *         Module.GLFW,
 *         Module.OPENGL,
 *         Preset.GETTING_STARTED
 *     )
 * }
 * ```
 */
sealed interface LwjglEntry
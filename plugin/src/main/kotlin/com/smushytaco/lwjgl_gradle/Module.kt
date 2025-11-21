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
@Suppress("kotlin:S1192")
enum class Module(val artifact: String, val since: String, val versionToNatives: Map<String, List<String>>) {
    CORE(
        "lwjgl",
        "3.1.0",
        mapOf(
            "3.1.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.3" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    ASSIMP(
        "lwjgl-assimp",
        "3.1.1",
        mapOf(
            "3.1.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.3" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    BGFX(
        "lwjgl-bgfx",
        "3.1.0",
        mapOf(
            "3.1.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.3" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    CUDA(
        "lwjgl-cuda",
        "3.2.1",
        mapOf(
            "3.2.1" to emptyList(),
            "3.2.2" to emptyList(),
            "3.2.3" to emptyList(),
            "3.3.0" to emptyList(),
            "3.3.1" to emptyList(),
            "3.3.2" to emptyList(),
            "3.3.3" to emptyList(),
            "3.3.4" to emptyList(),
            "3.3.5" to emptyList(),
            "3.3.6" to emptyList()
        )
    ),
    EGL(
        "lwjgl-egl",
        "3.1.0",
        mapOf(
            "3.1.0" to emptyList(),
            "3.1.1" to emptyList(),
            "3.1.2" to emptyList(),
            "3.1.3" to emptyList(),
            "3.1.4" to emptyList(),
            "3.1.5" to emptyList(),
            "3.1.6" to emptyList(),
            "3.2.0" to emptyList(),
            "3.2.1" to emptyList(),
            "3.2.2" to emptyList(),
            "3.2.3" to emptyList(),
            "3.3.0" to emptyList(),
            "3.3.1" to emptyList(),
            "3.3.2" to emptyList(),
            "3.3.3" to emptyList(),
            "3.3.4" to emptyList(),
            "3.3.5" to emptyList(),
            "3.3.6" to emptyList()
        )
    ),
    FMOD(
        "lwjgl-fmod",
        "3.3.2",
        mapOf(
            "3.3.2" to emptyList(),
            "3.3.3" to emptyList(),
            "3.3.4" to emptyList(),
            "3.3.5" to emptyList(),
            "3.3.6" to emptyList()
        )
    ),
    FREETYPE(
        "lwjgl-freetype",
        "3.3.2",
        mapOf(
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    GLFW(
        "lwjgl-glfw",
        "3.1.0",
        mapOf(
            "3.1.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.3" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    HARFBUZZ(
        "lwjgl-harfbuzz",
        "3.3.2",
        mapOf(
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    HWLOC(
        "lwjgl-hwloc",
        "3.3.2",
        mapOf(
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    JAWT(
        "lwjgl-jawt",
        "3.1.0",
        mapOf(
            "3.1.0" to emptyList(),
            "3.1.1" to emptyList(),
            "3.1.2" to emptyList(),
            "3.1.3" to emptyList(),
            "3.1.4" to emptyList(),
            "3.1.5" to emptyList(),
            "3.1.6" to emptyList(),
            "3.2.0" to emptyList(),
            "3.2.1" to emptyList(),
            "3.2.2" to emptyList(),
            "3.2.3" to emptyList(),
            "3.3.0" to emptyList(),
            "3.3.1" to emptyList(),
            "3.3.2" to emptyList(),
            "3.3.3" to emptyList(),
            "3.3.4" to emptyList(),
            "3.3.5" to emptyList(),
            "3.3.6" to emptyList()
        )
    ),
    JEMALLOC(
        "lwjgl-jemalloc",
        "3.1.0",
        mapOf(
            "3.1.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.3" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    KTX(
        "lwjgl-ktx",
        "3.3.2",
        mapOf(
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    LIBDIVIDE(
        "lwjgl-libdivide",
        "3.2.1",
        mapOf(
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    LLVM(
        "lwjgl-llvm",
        "3.2.1",
        mapOf(
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    LMDB(
        "lwjgl-lmdb",
        "3.1.0",
        mapOf(
            "3.1.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.3" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    LZ4(
        "lwjgl-lz4",
        "3.1.4",
        mapOf(
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    MEOW(
        "lwjgl-meow",
        "3.2.1",
        mapOf(
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    MESHOPTIMIZER(
        "lwjgl-meshoptimizer",
        "3.3.0",
        mapOf(
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    MSDFGEN(
        "lwjgl-msdfgen",
        "3.3.4",
        mapOf(
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    NANOVG(
        "lwjgl-nanovg",
        "3.1.0",
        mapOf(
            "3.1.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.3" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    NFD(
        "lwjgl-nfd",
        "3.1.0",
        mapOf(
            "3.1.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.3" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    NUKLEAR(
        "lwjgl-nuklear",
        "3.1.0",
        mapOf(
            "3.1.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.3" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    ODBC(
        "lwjgl-odbc",
        "3.1.4",
        mapOf(
            "3.1.4" to emptyList(),
            "3.1.5" to emptyList(),
            "3.1.6" to emptyList(),
            "3.2.0" to emptyList(),
            "3.2.1" to emptyList(),
            "3.2.2" to emptyList(),
            "3.2.3" to emptyList(),
            "3.3.0" to emptyList(),
            "3.3.1" to emptyList(),
            "3.3.2" to emptyList(),
            "3.3.3" to emptyList(),
            "3.3.4" to emptyList(),
            "3.3.5" to emptyList(),
            "3.3.6" to emptyList()
        )
    ),
    OPENAL(
        "lwjgl-openal",
        "3.1.0",
        mapOf(
            "3.1.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.3" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    OPENCL(
        "lwjgl-opencl",
        "3.1.0",
        mapOf(
            "3.1.0" to emptyList(),
            "3.1.1" to emptyList(),
            "3.1.2" to emptyList(),
            "3.1.3" to emptyList(),
            "3.1.4" to emptyList(),
            "3.1.5" to emptyList(),
            "3.1.6" to emptyList(),
            "3.2.0" to emptyList(),
            "3.2.1" to emptyList(),
            "3.2.2" to emptyList(),
            "3.2.3" to emptyList(),
            "3.3.0" to emptyList(),
            "3.3.1" to emptyList(),
            "3.3.2" to emptyList(),
            "3.3.3" to emptyList(),
            "3.3.4" to emptyList(),
            "3.3.5" to emptyList(),
            "3.3.6" to emptyList()
        )
    ),
    OPENGL(
        "lwjgl-opengl",
        "3.1.0",
        mapOf(
            "3.1.0" to emptyList(),
            "3.1.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.3" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    OPENGLES(
        "lwjgl-opengles",
        "3.1.0",
        mapOf(
            "3.1.0" to emptyList(),
            "3.1.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.3" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    OPENVR(
        "lwjgl-openvr",
        "3.1.2",
        mapOf(
            "3.1.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.3" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "macos"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "macos"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "macos"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "macos"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "macos"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "macos"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "macos"
            )
        )
    ),
    OPENXR(
        "lwjgl-openxr",
        "3.3.1",
        mapOf(
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "freebsd"
            )
        )
    ),
    OPUS(
        "lwjgl-opus",
        "3.2.1",
        mapOf(
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    OVR(
        "lwjgl-ovr",
        "3.1.0",
        mapOf(
            "3.1.0" to listOf(
                "windows"
            ),
            "3.1.1" to listOf(
                "windows"
            ),
            "3.1.2" to listOf(
                "windows"
            ),
            "3.1.3" to listOf(
                "windows"
            ),
            "3.1.4" to listOf(
                "windows"
            ),
            "3.1.5" to listOf(
                "windows"
            ),
            "3.1.6" to listOf(
                "windows"
            ),
            "3.2.0" to listOf(
                "windows"
            ),
            "3.2.1" to listOf(
                "windows"
            ),
            "3.2.2" to listOf(
                "windows"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86"
            )
        )
    ),
    PAR(
        "lwjgl-par",
        "3.1.0",
        mapOf(
            "3.1.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.3" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    REMOTERY(
        "lwjgl-remotery",
        "3.1.4",
        mapOf(
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    RPMALLOC(
        "lwjgl-rpmalloc",
        "3.1.3",
        mapOf(
            "3.1.3" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    SHADERC(
        "lwjgl-shaderc",
        "3.2.3",
        mapOf(
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    SPVC(
        "lwjgl-spvc",
        "3.3.0",
        mapOf(
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    SSE(
        "lwjgl-sse",
        "3.1.0",
        mapOf(
            "3.1.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.3" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "macos"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "macos"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "macos"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "macos"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "macos",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "macos",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "macos",
                "freebsd"
            )
        )
    ),
    STB(
        "lwjgl-stb",
        "3.1.0",
        mapOf(
            "3.1.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.3" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    TINYEXR(
        "lwjgl-tinyexr",
        "3.1.2",
        mapOf(
            "3.1.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.3" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    TINYFD(
        "lwjgl-tinyfd",
        "3.1.0",
        mapOf(
            "3.1.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.3" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    TOOTLE(
        "lwjgl-tootle",
        "3.1.5",
        mapOf(
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "macos"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "macos"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "macos"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "macos"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "macos",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "macos",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "macos",
                "freebsd"
            )
        )
    ),
    VMA(
        "lwjgl-vma",
        "3.2.0",
        mapOf(
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    VULKAN(
        "lwjgl-vulkan",
        "3.1.0",
        mapOf(
            "3.1.0" to emptyList(),
            "3.1.1" to emptyList(),
            "3.1.2" to emptyList(),
            "3.1.3" to emptyList(),
            "3.1.4" to emptyList(),
            "3.1.5" to emptyList(),
            "3.1.6" to emptyList(),
            "3.2.0" to listOf(
                "macos"
            ),
            "3.2.1" to listOf(
                "macos"
            ),
            "3.2.2" to listOf(
                "macos"
            ),
            "3.2.3" to listOf(
                "macos"
            ),
            "3.3.0" to listOf(
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "macos",
                "macos-arm64"
            ),
            "3.3.5" to listOf(
                "macos",
                "macos-arm64"
            ),
            "3.3.6" to listOf(
                "macos",
                "macos-arm64"
            )
        )
    ),
    XXHASH(
        "lwjgl-xxhash",
        "3.1.0",
        mapOf(
            "3.1.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.3" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    YOGA(
        "lwjgl-yoga",
        "3.1.2",
        mapOf(
            "3.1.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.3" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    ),
    ZSTD(
        "lwjgl-zstd",
        "3.1.4",
        mapOf(
            "3.1.4" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.5" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.1.6" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.0" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.1" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.2" to listOf(
                "windows",
                "linux",
                "macos"
            ),
            "3.2.3" to listOf(
                "windows",
                "windows-x86",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos"
            ),
            "3.3.0" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.1" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.2" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.3" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "macos",
                "macos-arm64"
            ),
            "3.3.4" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.5" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            ),
            "3.3.6" to listOf(
                "windows",
                "windows-x86",
                "windows-arm64",
                "linux",
                "linux-arm64",
                "linux-arm32",
                "linux-ppc64le",
                "linux-riscv64",
                "macos",
                "macos-arm64",
                "freebsd"
            )
        )
    )
}

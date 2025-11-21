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

import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.maven

/**
 * Adds the Sonatype-hosted Maven Central snapshots repository to this [RepositoryHandler].
 *
 * This is useful when depending on LWJGL (or any other library) snapshot versions, such as
 * `3.4.0-SNAPSHOT`, which are not available in the regular `mavenCentral()` repository.
 *
 * Example:
 * ```kotlin
 * import com.smushytaco.lwjgl_gradle.mavenCentralSnapshots
 * repositories {
 *     mavenCentral()
 *     mavenCentralSnapshots() // enables resolving snapshot artifacts
 * }
 * ```
 *
 * @receiver the [RepositoryHandler] to which the snapshots repository will be added.
 * @param repositoryName the logical name to assign to the repository in Gradle metadata.
 */
@Suppress("unused")
fun RepositoryHandler.mavenCentralSnapshots(repositoryName: String = "MavenCentralSnapshots") {
    maven("https://central.sonatype.com/repository/maven-snapshots/") {
        name = repositoryName
    }
}
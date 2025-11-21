# LWJGL3 Dependency Helper

[![Gradle Plugin Portal](https://img.shields.io/gradle-plugin-portal/v/com.smushytaco.lwjgl3?label=Gradle%20Plugin%20Portal)](https://plugins.gradle.org/plugin/com.smushytaco.lwjgl3)
[![Dokka Docs](https://img.shields.io/badge/docs-dokka-brightgreen.svg)](https://smushytaco.github.io/lwjgl3-gradle-plugin)

---

## 🚀 TL;DR (Quick Start)

```kotlin
plugins {
    id("com.smushytaco.lwjgl3") version "1.0.0"
}

repositories { mavenCentral() }

lwjgl {
    version = "3.3.6"                   // LWJGL version (explicitly set this!)
    implementation(Preset.EVERYTHING)   // Add all LWJGL modules + correct natives
}
```

That's it.  
All required Java + native LWJGL artifacts are added automatically.

To include **all native platforms**, not just your host machine:

```kotlin
lwjgl { usePredefinedPlatforms = true }
```

---

# LWJGL3 Dependency Helper

Managing LWJGL3 dependencies manually can be painful. This plugin fully automates the process, including:

- adding all required Java artifacts
- adding the correct native artifacts
- selecting natives based on your OS or a custom platform list
- supporting presets and individual modules
- handling version compatibility automatically

---

## ❌ Without this plugin (manual approach)

```kotlin
val lwjglVersion = "3.3.6"
val lwjglNatives = "natives-linux"

repositories { mavenCentral() }

dependencies {
    implementation(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))

    implementation("org.lwjgl", "lwjgl")
    implementation("org.lwjgl", "lwjgl-assimp")
    implementation("org.lwjgl", "lwjgl-bgfx")
    // ...
    runtimeOnly("org.lwjgl", "lwjgl", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-assimp", classifier = lwjglNatives)
    // ...
}
```

This quickly becomes hundreds of lines if you need every module and every native.

---

## ✔ With this plugin

```kotlin
import com.smushytaco.lwjgl_gradle.Preset

plugins {
    id("com.smushytaco.lwjgl3") version "1.0.0"
}

repositories { mavenCentral() }

lwjgl {
    version = "3.3.6"
    implementation(Preset.EVERYTHING)
}
```

That’s it.  
All Java dependencies and the correct natives are automatically added.

---

## Native Handling

- **Default:** only host OS natives are included
- **Enable all:**
  ```kotlin
  lwjgl { usePredefinedPlatforms = true }
  ```

You can also override the platform list:

```kotlin
lwjgl {
    platforms = listOf("linux", "macos", "windows", "windows-x86", "windows-arm64", "linux-riscv64")
}
```

---

## LWJGL Versions

The default LWJGL version is **3.3.6**.

It is **strongly recommended** that you explicitly set the version:

```kotlin
lwjgl { version = "3.3.3" }
```

### Snapshot versions

```kotlin
import com.smushytaco.lwjgl_gradle.mavenCentralSnapshots

repositories {
    mavenCentral()
    mavenCentralSnapshots()
}
lwjgl { version = "3.4.0-SNAPSHOT" }
```

---

## Selecting Modules

```kotlin
import com.smushytaco.lwjgl_gradle.Module

lwjgl {
    implementation(
        Module.ASSIMP,
        Module.BGFX,
        Module.GLFW,
        Module.OPENAL,
        Module.OPENGL,
        Module.VULKAN
    )
}
```

`Module.CORE` is always added automatically.

---

## Using LWJGL in tests

```kotlin
lwjgl { testImplementation(Preset.EVERYTHING) }
```

---

## Preset + module mix

```kotlin
lwjgl {
    implementation(Preset.GETTING_STARTED + Module.JEMALLOC)
}
```

---

## How the plugin works

The `Module` enum is **generated** using a separate codegen project.  
It contains *every LWJGL module*, *every version*, and *every native classifier supported* by that version.

This allows the plugin to:

- know exactly which natives exist for each LWJGL module version
- avoid adding non-existent natives
- support modules that added natives later
- support modules that gained new platforms over time

Only the correct natives for the chosen version are ever added.
If a certain native for a certain version doesn't exist, then it's skipped.
For example, there are FreeBSD natives in the latest LWJGL versions that didn't exist in older versions.
To give one more example, the Vulkan module used to contain no natives and later on added macOS natives.

---

For more detailed information, see the generated Dokka documentation.

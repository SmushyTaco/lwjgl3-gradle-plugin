# LWJGL3 Dependency Helper

[![Gradle Plugin Portal](https://img.shields.io/gradle-plugin-portal/v/com.smushytaco.lwjgl3?label=Gradle%20Plugin%20Portal)](https://plugins.gradle.org/plugin/com.smushytaco.lwjgl3)
[![Dokka Docs](https://img.shields.io/badge/docs-dokka-brightgreen.svg)](https://smushytaco.github.io/lwjgl3-gradle-plugin)

---

## 🚀 TL;DR (Quick Start)

```kotlin
import com.smushytaco.lwjgl_gradle.Preset

plugins {
    id("com.smushytaco.lwjgl3") version "1.0.0"
}

repositories {
    mavenCentral()
}

lwjgl {
    // Strongly recommended: set LWJGL version explicitly
    version = "3.3.6"

    // Add all LWJGL modules + the correct native artifacts
    implementation(Preset.EVERYTHING)
}
```

That’s it.  
All required **Java artifacts** and the correct **native artifacts** are added automatically.

To pull **all supported native platforms**, not just the current host:

```kotlin
lwjgl {
    usePredefinedPlatforms = true
}
```

---

## 💡 What this plugin solves

Managing LWJGL3 by hand is a mess:

- dozens of modules
- different native classifiers per OS / arch
- modules that gained natives in later versions
- new platforms only supported in newer LWJGL releases
- “does this native even exist for *this* version?”

This plugin handles all of that for you:

- ✅ Adds **core + selected modules** automatically
- ✅ Wires **natives** for the right platforms
- ✅ Skips modules that don’t exist in your LWJGL version
- ✅ Skips natives that don’t exist for that module/version
- ✅ Supports **presets** and **individual modules**
- ✅ Works with **all common Gradle configurations**
- ✅ Is **configuration-cache friendly**

---

## ❌ Without this plugin (manual pain)

```kotlin
val lwjglVersion = "3.3.6"
val lwjglNatives = "natives-linux" // or macos, windows, etc.

repositories {
    mavenCentral()
}

dependencies {
    // BOM + modules
    implementation(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))

    implementation("org.lwjgl", "lwjgl")
    implementation("org.lwjgl", "lwjgl-assimp")
    implementation("org.lwjgl", "lwjgl-bgfx")
    // ...

    // Natives for each module
    runtimeOnly("org.lwjgl", "lwjgl", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-assimp", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-bgfx", classifier = lwjglNatives)
    // ...
}
```

This explodes into **hundreds of lines** if you want everything and multiple platforms.

---

## ✔ With this plugin

```kotlin
import com.smushytaco.lwjgl_gradle.Preset

plugins {
    id("com.smushytaco.lwjgl3") version "1.0.0"
}

repositories {
    mavenCentral()
}

lwjgl {
    version = "3.3.6"
    implementation(Preset.EVERYTHING)
}
```

- All Java dependencies are added.
- All appropriate natives are added.
- Non-existent natives for that version are skipped.

---

## 🔢 LWJGL Versions

### Default version

By default, the plugin uses:

```text
3.3.6
```

### Explicit version (recommended)

Always set the version explicitly so it’s obvious what you’re targeting:

```kotlin
lwjgl {
    version = "3.3.3"
}
```

The plugin uses Maven’s version comparison logic under the hood and knows which modules / natives exist at which versions. Modules whose `since` version is **newer** than your configured version are simply skipped.

### Snapshot versions

```kotlin
lwjgl {
    version = "3.4.0-SNAPSHOT"
}
```

For versions ending in `-SNAPSHOT`:

- By default, the plugin **automatically adds** a Maven snapshots repository:
    - URL: `https://central.sonatype.com/repository/maven-snapshots/`
    - Name: `mavenCentralSnapshots`
- The repository is restricted to:
    - **snapshots only**, and
    - the **`org.lwjgl` group**.

You can customize or disable this behavior:

```kotlin
lwjgl {
    // Turn off automatic snapshot repo registration
    autoAddSnapshotRepository = false

    // Or customize it
    snapshotRepositoryUrl = "https://example.com/maven-snapshots/"
    snapshotRepositoryName = "myCustomSnapshots"
}
```

> The snapshots repo is added lazily the first time you call an LWJGL DSL function  
> (`implementation`, `api`, `into`, etc.) while using a snapshot version.

---

## 🧱 Modules & Presets

The plugin uses a **sealed interface**:

```kotlin
sealed interface LwjglEntry
```

Implemented by:

- `Module` — individual LWJGL modules
- `Preset` — named bundles of modules (e.g. “minimal OpenGL”, “everything”)

Every DSL entry point (e.g. `implementation`, `api`, `testImplementation`, `into`) accepts **`LwjglEntry` varargs or iterables**, so you can mix modules and presets freely.

### Selecting individual modules

```kotlin
import com.smushytaco.lwjgl_gradle.Module

lwjgl {
    implementation(
        Module.CORE,     // added automatically if omitted, but allowed explicitly
        Module.GLFW,
        Module.OPENGL,
        Module.OPENAL,
        Module.VULKAN
    )
}
```

> `Module.CORE` is always added automatically if not present in the set.  
> You never need to remember to add it manually.

### Using presets

```kotlin
import com.smushytaco.lwjgl_gradle.Preset

lwjgl {
    implementation(
        Preset.MINIMAL_OPENGL
    )
}
```

### Mixing presets and modules

Since both `Module` and `Preset` implement `LwjglEntry`, you can mix them directly:

```kotlin
import com.smushytaco.lwjgl_gradle.Module
import com.smushytaco.lwjgl_gradle.Preset

lwjgl {
    implementation(
        Preset.GETTING_STARTED,
        Module.JEMALLOC,
        Module.STB
    )
}
```

---

## 🌍 Native Handling

The plugin automatically wires **native classifiers** for each chosen module and platform.

### Default behavior (host-only natives)

By default, only natives for the **current host platform** are added.  
The plugin detects:

- OS (`os.name`)
- Arch (`os.arch`)

and resolves to one of LWJGL’s native classifier bases, such as:

- `linux`
- `linux-arm64`
- `macos`
- `macos-arm64`
- `windows`
- `windows-x86`
- `windows-arm64`
- `freebsd`

The `-natives` prefix is handled when adding natives so that it can be omitted here.

### All known platforms

To pull natives for all supported platforms at once:

```kotlin
lwjgl {
    usePredefinedPlatforms = true
}
```

The default `platforms` list is:

```kotlin
listOf(
    "linux-ppc64le", "linux-riscv64", "linux-arm64", "linux-arm32", "linux",
    "macos-arm64", "macos",
    "windows-arm64", "windows", "windows-x86",
    "freebsd"
)
```

### Custom platform list

You can override this list entirely:

```kotlin
lwjgl {
    usePredefinedPlatforms = true

    platforms = listOf(
        "linux",
        "linux-arm64",
        "macos",
        "windows",
        "windows-x86",
        "windows-arm64"
    )
}
```

This list corresponds to the suffix **without** the `natives-` prefix  
(e.g. `"linux"` → `natives-linux`).

---

## ⚙️ Which configurations are supported?

The extension provides helpers for all the usual configurations.

All of these accept `LwjglEntry` (`Module` or `Preset`) varargs or iterables:

- `implementation(...)`
- `testImplementation(...)`
- `api(...)`
- `testApi(...)`
- `compileOnly(...)`
- `testCompileOnly(...)`
- `runtimeOnly(...)`
- `testRuntimeOnly(...)`
- `into(...)` — custom configurations

### Defaults for natives

For each compile-time configuration, natives go to a sensible runtime configuration by default:

| Compile-time helper        | Default runtime for natives |
|----------------------------|-----------------------------|
| `implementation(...)`      | `runtimeOnly`               |
| `testImplementation(...)`  | `testRuntimeOnly`           |
| `api(...)`                 | `runtimeOnly`               |
| `testApi(...)`             | `testRuntimeOnly`           |
| `compileOnly(...)`         | _no natives_                |
| `testCompileOnly(...)`     | _no natives_                |
| `runtimeOnly(...)`         | **natives only**            |
| `testRuntimeOnly(...)`     | **natives only**            |

### Overriding where natives go

Every helper that wires compile-time deps has a `wireNativesTo` parameter:

```kotlin
// Default: natives to runtimeOnly
lwjgl {
    implementation(Module.GLFW, wireNativesTo = "runtimeOnly")
}

// Use a named configuration object
lwjgl {
    val myRuntime by configurations.creating
    implementation(Module.GLFW, wireNativesTo = myRuntime)
}

// Disable natives entirely for this call
lwjgl {
    implementation(Module.GLFW, wireNativesTo = null)
}
```

The same pattern applies to:

- `implementation`
- `testImplementation`
- `api`
- `testApi`
- `compileOnly`
- `testCompileOnly`

---

## 🎯 Custom configurations with `into`

If you have custom configurations, use `into`:

```kotlin
val lwjglCompile by configurations.creating
val lwjglRuntime by configurations.creating

lwjgl {
    into(
        compileConfiguration = lwjglCompile,
        runtimeConfiguration = lwjglRuntime,
        entries = listOf(
            Preset.MINIMAL_OPENGL,
            Module.GLFW
        )
    )
}
```

Or with varargs:

```kotlin
lwjgl {
    into(
        compileConfiguration = "myCompile",
        runtimeConfiguration = "myRuntime",
        Preset.MINIMAL_OPENGL,
        Module.GLFW,
        Module.OPENAL
    )
}
```

You can also pass `null` to skip compile-time or runtime wiring:

```kotlin
lwjgl {
    // Only natives to "myRuntime"
    into(
        compileConfiguration = null,
        runtimeConfiguration = "myRuntime",
        Module.GLFW
    )

    // Only compile-time artifacts, no natives
    into(
        compileConfiguration = "myCompile",
        runtimeConfiguration = null,
        Module.GLFW
    )
}
```

---

## 🧠 Version- and native-awareness

The `Module` enum is **generated** by a separate codegen step and encodes:

- which LWJGL versions each module exists in (`since`)
- which native classifiers exist for each module and version

At resolution time the plugin:

1. **Skips modules** whose `since` version is newer than your configured `version`.
2. Chooses the **appropriate “natives version”** (up to the latest known stable).
3. Looks up the list of supported native classifiers for that `(module, version)`.
4. Only wires natives that actually exist for that combination.

Examples of what this gets right:

- FreeBSD natives only appear once LWJGL started publishing them.
- Vulkan natives only appear on the versions/platforms where they exist.
- Modules that initially had no natives but gained them later are handled correctly.

You never get dependencies for artifacts that don’t exist.

---

## 🧱 Configuration cache friendliness

The plugin is written to be compatible with Gradle’s **configuration cache**:

- No use of `afterEvaluate { ... }`.
- All wiring (dependencies, repositories) happens during configuration.
- Snapshot repository registration is lazy but still configuration-phase only.

You can safely enable the configuration cache for builds using this plugin.

---

## 📚 Documentation

Full API documentation is available via Dokka:

- **Dokka docs:** https://smushytaco.github.io/lwjgl3-gradle-plugin
- **Plugin Portal:** https://plugins.gradle.org/plugin/com.smushytaco.lwjgl3

If you spot a missed module, platform, or version edge case, please open an issue or PR —  
the codegen-backed `Module` enum makes it straightforward to keep everything perfectly in sync with LWJGL’s releases.

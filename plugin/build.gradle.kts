import nl.jolanrensen.kodex.defaultProcessors.ARG_DOC_PROCESSOR
import nl.jolanrensen.kodex.defaultProcessors.INCLUDE_FILE_DOC_PROCESSOR
import nl.jolanrensen.kodex.gradle.creatingRunKodexTask
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `maven-publish`
    embeddedKotlin("jvm")
    alias(libs.plugins.dokka)
    alias(libs.plugins.yumiGradleLicenser)
    alias(libs.plugins.gradlePluginPublish)
    alias(libs.plugins.kodex)
}

val projectName = providers.gradleProperty("name")
val projectGroup = providers.gradleProperty("group")
val projectVersion = providers.gradleProperty("version")
val projectDescription = providers.gradleProperty("description")
val projectUrl = providers.gradleProperty("url")

val javaVersion = libs.versions.java.map { it.toInt() }

base.archivesName = projectName
group = projectGroup.get()
version = projectVersion.get()
description = projectDescription.get()

repositories {
    mavenCentral()
    gradlePluginPortal()
}
dependencies {
    implementation(platform(embeddedKotlin("bom")))
    implementation(embeddedKotlin("gradle-plugin"))
    implementation(embeddedKotlin("stdlib"))
    implementation(libs.maven.artifact)
    testImplementation(gradleTestKit())
    testImplementation(kotlin("test"))
}
gradlePlugin {
    website = projectUrl
    vcsUrl = projectUrl
    plugins.create("lwjgl3") {
        id = "com.smushytaco.lwjgl3"
        displayName = "LWJGL3 Dependency Helper"
        implementationClass = "com.smushytaco.lwjgl_gradle.LwjglPlugin"
        description = projectDescription.get()
        tags = listOf("lwjgl", "opengl", "vulkan", "native", "gamedev", "dependency", "management")
    }
}
java {
    toolchain {
        languageVersion = javaVersion.map { JavaLanguageVersion.of(it) }
        vendor = JvmVendorSpec.ADOPTIUM
    }
    sourceCompatibility = JavaVersion.toVersion(javaVersion.get())
    targetCompatibility = JavaVersion.toVersion(javaVersion.get())
    withSourcesJar()
}
val kotlinMainSources = kotlin.sourceSets.main.get().kotlin.sourceDirectories

val processKdocMain by creatingRunKodexTask(sources = kotlinMainSources) {
    processors = listOf(INCLUDE_FILE_DOC_PROCESSOR, ARG_DOC_PROCESSOR)
}
dokka {
    dokkaSourceSets.configureEach {
        sourceRoots.setFrom(processKdocMain.target.get().toString())
        reportUndocumented = true
    }
}
val licenseFile = run {
    val rootLicense = layout.projectDirectory.file("LICENSE")
    val parentLicense = layout.projectDirectory.file("../LICENSE")
    when {
        rootLicense.asFile.exists() -> {
            logger.lifecycle("Using LICENSE from project root: {}", rootLicense.asFile)
            rootLicense
        }
        parentLicense.asFile.exists() -> {
            logger.lifecycle("Using LICENSE from parent directory: {}", parentLicense.asFile)
            parentLicense
        }
        else -> {
            logger.warn("No LICENSE file found in project or parent directory.")
            null
        }
    }
}
tasks {
    dokkaGenerateHtml {
        dependsOn(processKdocMain)
    }
    withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        sourceCompatibility = javaVersion.get().toString()
        targetCompatibility = javaVersion.get().toString()
        if (javaVersion.get() > 8) options.release = javaVersion
    }
    withType<JavaExec>().configureEach { defaultCharacterEncoding = "UTF-8" }
    withType<Javadoc>().configureEach { options.encoding = "UTF-8" }
    withType<Test>().configureEach { defaultCharacterEncoding = "UTF-8" }
    register<Jar>("dokkaJar") {
        group = JavaBasePlugin.DOCUMENTATION_GROUP
        dependsOn(dokkaGenerateHtml)
        archiveClassifier = "javadoc"
        from(layout.buildDirectory.dir("dokka/html"))
    }
    named("build") { dependsOn(named("dokkaJar")) }
    withType<KotlinCompile>().configureEach {
        compilerOptions {
            extraWarnings = true
            jvmTarget = javaVersion.map { JvmTarget.valueOf("JVM_${if (it == 8) "1_8" else it}") }
        }
    }
    withType<Jar>().configureEach {
        dependsOn(processKdocMain)
        doFirst {
            kotlin {
                sourceSets {
                    main {
                        kotlin.setSrcDirs(processKdocMain.targets)
                    }
                }
            }
        }
        doLast {
            kotlin {
                sourceSets {
                    main {
                        kotlin.setSrcDirs(kotlinMainSources)
                    }
                }
            }
        }
        licenseFile?.let {
            from(it) {
                rename { original -> "${original}_${archiveBaseName.get()}" }
            }
        }
    }
}
license {
    rule(file("../HEADER"))
    include("**/*.kt")
    exclude("**/*.properties")
}
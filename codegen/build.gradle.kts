import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.yumiGradleLicenser)
    application
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
    implementation(platform(kotlin("bom")))
    implementation(kotlin("stdlib"))
    implementation(libs.maven.resolver.api)
    implementation(libs.maven.resolver.spi)
    implementation(libs.maven.resolver.util)
    implementation(libs.maven.resolver.impl)
    implementation(libs.maven.resolver.connector.basic)
    implementation(libs.maven.resolver.transport.http)
    implementation(libs.maven.resolver.transport.file)
    implementation(libs.maven.resolver.provider)
    implementation(libs.maven.resolver.supplier.mvn3)
    implementation(libs.slf4jSimple)
}
java {
    toolchain {
        languageVersion = javaVersion.map { JavaLanguageVersion.of(it) }
        vendor = JvmVendorSpec.ADOPTIUM
    }
    sourceCompatibility = JavaVersion.toVersion(javaVersion.get())
    targetCompatibility = JavaVersion.toVersion(javaVersion.get())
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
    withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        sourceCompatibility = javaVersion.get().toString()
        targetCompatibility = javaVersion.get().toString()
        if (javaVersion.get() > 8) options.release = javaVersion
    }
    withType<JavaExec>().configureEach { defaultCharacterEncoding = "UTF-8" }
    withType<Javadoc>().configureEach { options.encoding = "UTF-8" }
    withType<Test>().configureEach { defaultCharacterEncoding = "UTF-8" }
    withType<KotlinCompile>().configureEach {
        compilerOptions {
            extraWarnings = true
            jvmTarget = javaVersion.map { JvmTarget.valueOf("JVM_${if (it == 8) "1_8" else it}") }
        }
    }
    withType<Jar>().configureEach {
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
application {
    mainClass = "com.smushytaco.codegen.CodegenKt"
}
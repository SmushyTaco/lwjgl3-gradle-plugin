tasks.named<UpdateDaemonJvm>("updateDaemonJvm") {
    languageVersion = libs.versions.gradleJava.map { JavaLanguageVersion.of(it.toInt()) }
    vendor = JvmVendorSpec.ADOPTIUM
}
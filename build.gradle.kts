// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.12.3" apply false
    id("org.jetbrains.kotlin.android") version "2.3.21" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.3.21" apply false
    id("com.ncorti.ktfmt.gradle") version "0.26.0" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.8" apply false
}

tasks.register("qualityCheck") {
    group = "verification"
    description = "Runs Kotlin formatting and static analysis checks."
    dependsOn(":app:ktfmtCheck", ":app:detekt")
}

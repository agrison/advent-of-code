import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    application
    kotlin("jvm") version "1.4.10"
    kotlin("kapt") version "1.4.10"
}

application {
    mainClass.set("util.Runner")
}

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.reflections", "reflections", "0.9.12")

    implementation("io.arrow-kt", "arrow-fx", "0.11.0")
    implementation("io.arrow-kt", "arrow-optics", "0.11.0")
    implementation("io.arrow-kt", "arrow-syntax", "0.11.0")
    kapt("io.arrow-kt", "arrow-meta", "0.11.0")

    testImplementation("junit", "junit", "4.13.1")
    testImplementation("org.hamcrest", "hamcrest", "2.2")

    implementation("io.vavr", "vavr", "0.10.3")
    implementation("io.vavr", "vavr-kotlin", "0.10.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

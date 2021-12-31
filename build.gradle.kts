import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(15))
    }
}

plugins {
    application
    kotlin("jvm") version "1.6.10"
    kotlin("kapt") version "1.6.10"
}

application {
    mainClass.set("util.Runner")
}

version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
    maven("https://repo.kotlin.link")
}

dependencies {
    //implementation(kotlin("stdlib-jdk8"))
    implementation("org.reflections", "reflections", "0.9.12")

    implementation("io.arrow-kt", "arrow-fx", "0.11.0")
    implementation("io.arrow-kt", "arrow-optics", "0.11.0")
    implementation("io.arrow-kt", "arrow-syntax", "0.11.0")
    kapt("io.arrow-kt", "arrow-meta", "0.11.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
    testImplementation("org.hamcrest", "hamcrest", "2.2")

    implementation("io.vavr", "vavr", "0.10.3")
    implementation("io.vavr", "vavr-kotlin", "0.10.2")
    implementation("com.vdurmont", "emoji-java", "5.1.1")
    implementation("com.beust", "klaxon", "5.0.1")

    implementation("commons-io", "commons-io", "2.8.0")

    implementation("space.kscience:kmath-core:0.2.1")
    implementation("space.kscience:kmath-complex:0.2.1")

    implementation("com.github.freva:ascii-table:1.1.0")

    implementation("org.seleniumhq.selenium:selenium-java:2.41.0")
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "15"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

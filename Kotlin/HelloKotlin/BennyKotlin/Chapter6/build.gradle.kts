import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "cn.kotliner.kotlin"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

configure<JavaPluginConvention> {
    setSourceCompatibility(1.5)
}

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
}


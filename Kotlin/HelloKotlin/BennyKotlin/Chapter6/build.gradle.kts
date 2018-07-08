group = "cn.kotliner.kotlin"
version = "1.0-SNAPSHOT"

apply {
    plugin("java")
    plugin("kotlin")
}

configure<JavaPluginConvention>{
    setSourceCompatibility(1.5)
}

repositories {
    mavenCentral()
}

dependencies {
    add("compile", "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.2.30")
    //testCompile("junit", "junit", "4.12")
    //compile "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${rootProject.kotlin_version}"
}

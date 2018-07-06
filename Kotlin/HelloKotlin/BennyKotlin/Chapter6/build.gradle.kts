group = "cn.kotliner.kotlin"
version = "1.0-SNAPSHOT"

buildscript {
    extra["kotlin_version"] = "1.1.2"

    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${extra["kotlin_version"]}")
    }
}

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
    //compile("org.jetbrains.kotlin:kotlin-stdlib-jre8:${extra["kotlin_version"]}")
    //testCompile("junit", "junit", "4.12")
    add("compile", "org.jetbrains.kotlin:kotlin-stdlib-jre8:${extra["kotlin_version"]}")
}

group = "com.ztiany"
version = "1.0"

buildscript {

    repositories {
        jcenter()
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.2.0")
    }

}

apply {
    plugin("java")
    plugin("kotlin")
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies{
//    testCompile("junit", "junit", "4.11")
//    testCompile("org.jetbrains.kotlin:kotlin-test")
//    testCompile("org.jetbrains.kotlin:kotlin-test-junit")
//    compile("org.jetbrains.kotlin:kotlin-stdlib:1.2.0")
//    compile("org.jetbrains.kotlin:kotlin-reflect")
//    compile("io.reactivex.rxjava2:rxkotlin:2.2.0")
    add("compile","org.jetbrains.kotlin:kotlin-stdlib:1.2.0")
    add("compile","org.jetbrains.kotlin:kotlin-reflect")
    add("compile","io.reactivex.rxjava2:rxkotlin:2.2.0")
}
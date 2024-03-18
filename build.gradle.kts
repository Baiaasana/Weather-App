
buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.51")

    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id("com.google.dagger.hilt.android") version "2.50" apply false



}

//ext{
//    var compose_version = "1.1.1"
//}




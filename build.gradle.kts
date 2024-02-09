
buildscript {
//    ext {
//        compose_version = "1.1.1"
//    }

    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.44.2")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}

ext{
    var compose_version = "1.1.1"
}




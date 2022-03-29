plugins {
    kotlin("jvm")
}

tasks.wrapper {
    gradleVersion = "7.4.1"
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    group = "org.paradigm"
    version = "0.0.1"

    repositories {
        mavenLocal()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }

    dependencies {
        implementation(kotlin("reflect"))
    }

    tasks {
        val jvmVersion = JavaVersion.VERSION_11.toString()

        compileKotlin {
            kotlinOptions {
                jvmTarget = jvmVersion
                sourceCompatibility = jvmVersion
                targetCompatibility = jvmVersion
            }
        }

        compileJava {
            sourceCompatibility = jvmVersion
            targetCompatibility = jvmVersion
        }
    }
}
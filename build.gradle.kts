import org.jetbrains.kotlin.gradle.targets.js.npm.NpmResolverPlugin
import java.util.*

plugins {
    kotlin("multiplatform") version "1.8.0"
    id("com.android.library")
    id("maven-publish")
}
plugins.apply(NpmResolverPlugin::class.java)

group = "io.github.vinccool96.ref"
version = "1.0.1"

repositories {
    google()
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(BOTH) {
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
        }
        nodejs()
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    android()
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting
        val jsMain by getting {
            dependencies {
                implementation(npm("node-weak-ref", "2.0.1"))
                implementation(npm("expose-ts-gc", "1.0.0"))
            }
            project.tasks["kotlinStoreYarnLock"].dependsOn("buildNodeWeakRef")
        }
        val jsTest by getting
        val nativeMain by getting
        val nativeTest by getting
        val androidMain by getting {
            dependencies {
                implementation("com.google.android.material:material:1.5.0")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 31
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

tasks.register("buildNodeWeakRef") {
    doFirst {
        exec {
            workingDir = File("$projectDir/build/js/node_modules/node-weak-ref")
            if (System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("windows")) {
                commandLine("cmd", "/c", "node-gyp", "rebuild")
            } else {
                commandLine("bash", "-c", "node-gyp", "rebuild")
            }
        }
    }
}

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("com.squareup.sqldelight")
    id("dev.icerock.mobile.multiplatform-resources")
}

multiplatformResources {
    multiplatformResourcesPackage = "yegor.cheprasov.kmmapp" // required
    iosBaseLocalizationRegion = "en" // optional, default "en"
    multiplatformResourcesSourceSet = "commonMain"  // optional, default "commonMain"
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //Database
                implementation(Dependencies.SqlDelight.runtime)

                //Http
                implementation(Dependencies.Ktor.core)
                implementation(Dependencies.Ktor.serialization)
                implementation(Dependencies.Ktor.json)
                implementation(Dependencies.Ktor.negotiator)

                implementation(Dependencies.serialization)
                implementation(Dependencies.Coroutines.core)

                implementation(Dependencies.Koin.core)

                api("dev.icerock.moko:resources:0.20.0")
                api("dev.icerock.moko:parcelize:0.8.0")
                api("dev.icerock.moko:graphics:0.9.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                //Database
                implementation(Dependencies.SqlDelight.android)

                //Http
                implementation(Dependencies.Ktor.android)
                api("dev.icerock.moko:resources-compose:0.20.0")
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                //Database
                implementation(Dependencies.SqlDelight.native)

                //Http
                implementation(Dependencies.Ktor.ios)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

sqldelight {
    database("KmmDatabase") {
        packageName = "yegor.cheprasov.kmmapp.db"
        sourceFolders = listOf("sqldelight")
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}

object Versions {
    const val kotlinVersion = "1.6.10"
    const val sqlDelightVersion = "1.5.3"
    const val ktorVersion = "2.0.1"
    const val serializationVersion = "1.2.2"
    const val koinVersion = "3.2.0"
}

object Dependencies {

    object SqlDelight {
        const val runtime = "com.squareup.sqldelight:runtime:${Versions.sqlDelightVersion}"
        const val android = "com.squareup.sqldelight:android-driver:${Versions.sqlDelightVersion}"
        const val native = "com.squareup.sqldelight:native-driver:${Versions.sqlDelightVersion}"
    }

    object Ktor {
        const val core = "io.ktor:ktor-client-core:${Versions.ktorVersion}"
        const val android = "io.ktor:ktor-client-android:${Versions.ktorVersion}"
        const val ios = "io.ktor:ktor-client-ios:${Versions.ktorVersion}"
        const val serialization = "io.ktor:ktor-client-serialization:${Versions.ktorVersion}"
        const val json = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktorVersion}"
        const val negotiator = "io.ktor:ktor-client-content-negotiation:${Versions.ktorVersion}"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0-native-mt"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koinVersion}"
    }

    const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.serializationVersion}"
}
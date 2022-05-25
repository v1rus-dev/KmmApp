plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "yegor.cheprasov.kmmapp.android"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeVersion
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.6.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")

    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.materialIconsCore)
    implementation(Dependencies.Compose.materialIconsExtended)
    implementation(Dependencies.Compose.animation)
    debugImplementation(Dependencies.Compose.tooling)
    implementation(Dependencies.Compose.toolingPreview)
    implementation(Dependencies.Compose.activity)
    implementation(Dependencies.Compose.theme)
    implementation(Dependencies.Compose.liveData)
    implementation(Dependencies.Compose.rxJava)
    implementation(Dependencies.Compose.viewModel)
    implementation(Dependencies.Compose.accompanist)
    implementation(Dependencies.Compose.accompanistUi)
    implementation(Dependencies.Compose.accompanistSwipeRefresh)
    implementation(Dependencies.Compose.constraint)
    implementation(Dependencies.Compose.navigation)
    implementation(Dependencies.Compose.coil)

    implementation(Dependencies.Koin.android)
    implementation(Dependencies.Koin.workManager)
    implementation(Dependencies.Koin.compose)

    implementation(Dependencies.Paging.runtime)
    implementation(Dependencies.Paging.compose)

    implementation(Dependencies.androidDecompose)
    implementation(Dependencies.decomposeExtension)
}

object Versions {
    const val navigationVersion = "2.4.1"

    const val composeVersion = "1.1.1"
    const val composeActivityVersion = "1.4.0"
    const val composeViewModelVersion = "2.4.1"
    const val composeConstraint = "1.0.0-beta02"
    const val accompanistVersion = "0.23.1"
    const val coilVersion = "2.1.0"

    const val hiltVersion = "2.42"
    const val hiltNavigationComposeVersion = "1.0.0"

    const val nbvVersion = "1.0.0"

    const val koinVersion = "3.2.0"
    const val pagingVersion = "3.1.1"
    const val pagingComposeVersion = "1.0.0-alpha14"
    const val decomposeVersion = "0.6.0"
}

object Dependencies {

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.composeVersion}"
        const val material = "androidx.compose.material:material:${Versions.composeVersion}"
        const val materialIconsCore =
            "androidx.compose.material:material-icons-core:${Versions.composeVersion}"
        const val materialIconsExtended =
            "androidx.compose.material:material-icons-extended:${Versions.composeVersion}"
        const val animation = "androidx.compose.animation:animation:${Versions.composeVersion}"
        const val tooling = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
        const val toolingPreview =
            "androidx.compose.ui:ui-tooling-preview:${Versions.composeVersion}"
        const val activity = "androidx.activity:activity-compose:${Versions.composeActivityVersion}"
        const val theme =
            "com.google.android.material:compose-theme-adapter:${Versions.composeVersion}"
        const val liveData = "androidx.compose.runtime:runtime-livedata:${Versions.composeVersion}"
        const val rxJava = "androidx.compose.runtime:runtime-rxjava2:${Versions.composeVersion}"
        const val foundation = "androidx.compose.foundation:foundation:${Versions.composeVersion}"
        const val viewModel =
            "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModelVersion}"
        const val constraint =
            "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstraint}"
        const val navigation =
            "androidx.navigation:navigation-compose:${Versions.navigationVersion}"

        const val accompanist =
            "com.google.accompanist:accompanist-insets:${Versions.accompanistVersion}"
        const val accompanistUi =
            "com.google.accompanist:accompanist-insets-ui:${Versions.accompanistVersion}"
        const val accompanistSwipeRefresh =
            "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanistVersion}"

        const val image = "com.github.jrvansuita:PickImage:+"

        const val coil = "io.coil-kt:coil-compose:${Versions.coilVersion}"
    }

    object Koin {
        const val android = "io.insert-koin:koin-android:${Versions.koinVersion}"
        const val workManager = "io.insert-koin:koin-androidx-workmanager:${Versions.koinVersion}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koinVersion}"
    }

    object Paging {
        const val runtime = "androidx.paging:paging-runtime:${Versions.pagingVersion}"
        const val compose = "androidx.paging:paging-compose:${Versions.pagingComposeVersion}"
    }

    const val androidDecompose =
        "com.arkivanov.decompose:extensions-android:${Versions.decomposeVersion}"
    const val decomposeExtension =
        "com.arkivanov.decompose:extensions-compose-jetpack:${Versions.decomposeVersion}"

}
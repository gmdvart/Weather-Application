plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
    alias(libs.plugins.hilt.android)
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.example.weatherapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.weatherapplication"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}

dependencies {

    implementation(libs.bundles.androidx)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.androidx.compose)
    implementation(libs.navigation.compose)
    implementation(libs.play.services.location)
    implementation(libs.bundles.ktor.client)
    implementation(libs.coil)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    testImplementation(libs.junit)

    androidTestImplementation(libs.bundles.androidx.test)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test)

    debugImplementation(libs.bundles.androidx.compose.debug)
}

kapt {
    correctErrorTypes = true
}
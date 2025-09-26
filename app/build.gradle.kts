import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.FileInputStream
import java.util.Properties

val secretsPropertiesFile = rootProject.file("secrets.properties")
val secrets = Properties()

if (secretsPropertiesFile.exists()) {
    secrets.load(FileInputStream(secretsPropertiesFile))
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.cornellappdev.hustle"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.cornellappdev.hustle"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                "String",
                "GOOGLE_AUTH_CLIENT_ID", "\"${secrets.getProperty("GOOGLE_AUTH_CLIENT_ID")}\""
            )
            buildConfigField(
                "String",
                "BASE_API_URL", "\"${secrets.getProperty("BASE_API_URL_PROD")}\""
            )
        }

        debug {
            buildConfigField(
                "String",
                "GOOGLE_AUTH_CLIENT_ID", "\"${secrets.getProperty("GOOGLE_AUTH_CLIENT_ID")}\""
            )
            buildConfigField(
                "String",
                "BASE_API_URL", "\"${secrets.getProperty("BASE_API_URL_DEV")}\""
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // Hilt Dependencies
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.android.compiler)
    // Retrofit and OkHttp Dependencies
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    // Lint Checks
    lintChecks(libs.slack.compose.lint)
    // Navigation and Serialization
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.navigation.compose)
    // Firebase Google Sign In Dependencies
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.credential.manager)
    implementation(libs.credential.manager.play.services)
    implementation(libs.google.id)
    // Firebase Analytics
    implementation(libs.firebase.analytics)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
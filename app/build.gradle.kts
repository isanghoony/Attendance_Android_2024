plugins {
    alias(notation = libs.plugins.android.application)
    alias(notation = libs.plugins.jetbrains.kotlin.android)
    alias(notation = libs.plugins.ksp)
    alias(notation = libs.plugins.hilt.android)
    alias(notation = libs.plugins.kotlinx.serialization)
    alias(notation = libs.plugins.compose.compiler)
}

android {
    namespace = "com.ddd.attendance"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ddd.attendance"
        minSdk = 24
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
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    implementation(dependencyNotation = libs.androidx.navigation.compose)
    implementation(dependencyNotation = libs.hilt.android)
    implementation(dependencyNotation = libs.hilt.navigation.compose)
    ksp(dependencyNotation = libs.hilt.compiler)
    implementation(dependencyNotation = libs.retrofit2)
    implementation(dependencyNotation = libs.converter.serialization)
    implementation(dependencyNotation = libs.okhttp3)
    implementation(dependencyNotation = libs.kotlin.serialization)
}
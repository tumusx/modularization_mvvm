plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.catapi"
    compileSdk = 32
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    defaultConfig {
        applicationId = "com.example.catapi"
        minSdk = 27
        targetSdk = 32
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
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = ("1.8")
    }

}

dependencies {
    implementation(project(mapOf("path" to ":list")))
    val room_version = "2.4.1"
    implementation ("androidx.room:room-runtime:$room_version")
    kapt ("androidx.room:room-compiler:$room_version")
    implementation(libs.bundles.android.core)
    implementation(libs.bundles.lifecycle)
    implementation(libs.activity.ktx)
    implementation(libs.google.maps)
    implementation(libs.bundles.retrofit)
    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.junit.android)
    implementation(libs.viewmodel.ktx)
    implementation(libs.runtime.ktx)
    implementation ("com.github.bumptech.glide:glide:4.13.2")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.13.2")
    implementation ("io.insert-koin:koin-core:3.2.0")
    implementation ("io.insert-koin:koin-android:3.2.0")
}
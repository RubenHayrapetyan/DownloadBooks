plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.kotlinSerialization)
  alias(libs.plugins.ksp)
}

android {
  namespace = "com.project.downloadbooks"
  compileSdk = 35

  defaultConfig {
    applicationId = "com.project.downloadbooks"
    minSdk = 24
    targetSdk = 35
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
  buildFeatures {
    compose = true
  }
}

dependencies {
  implementation (libs.androidx.work.runtime.ktx)
  implementation(libs.room.runtime)
  implementation (libs.room.paging)
  ksp(libs.androidx.room.compiler)
  implementation (libs.retrofit2.converter.moshi)
  implementation (libs.androidx.paging.runtime)
  implementation (libs.androidx.paging.compose)
  implementation (libs.retrofit)
  implementation (libs.logging.interceptor)
  implementation (libs.androidx.navigation.compose)
  implementation(libs.kotlinx.serialization.json)
  implementation (libs.insert.koin.koin.android)
  implementation (libs.koin.androidx.compose)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.coil.compose)
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
}
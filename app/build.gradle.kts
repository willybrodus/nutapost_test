import com.nutapos.nutatest.Configuration

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose.compiler)
  alias(libs.plugins.kotlin.kapt)
  alias(libs.plugins.ksp)
  alias(libs.plugins.kotlin.parcelize)
  alias(libs.plugins.hilt.plugin)
}

android {
  namespace = "com.nutapos.nutatest"

  defaultConfig {
    applicationId = "com.nutapos.nutatest"
    versionCode = Configuration.versionCode
    versionName = Configuration.versionName

    multiDexEnabled = true
    testInstrumentationRunner = "com.nutapos.nutatest.AppTestRunner"
  }

  sourceSets {
    getByName("main") {
      java.srcDirs("src/main/kotlin")
    }
  }

  buildFeatures {
    viewBinding = true
    buildConfig = true
    compose = true
  }

  hilt {
    enableAggregatingTask = true
  }

  kotlin {
    sourceSets.configureEach {
      kotlin.srcDir(layout.buildDirectory.files("generated/ksp/$name/kotlin/"))
    }
    sourceSets.all {
      languageSettings {
        languageVersion = "2.0"
      }
    }
  }

  testOptions {
    unitTests {
      isIncludeAndroidResources = true
      isReturnDefaultValues = true
    }
  }

  buildTypes {
    create("benchmark") {
      isDebuggable = true
      signingConfig = getByName("debug").signingConfig
      matchingFallbacks += listOf("release")
    }
  }
}

dependencies {
  // modules
  implementation(projects.core.data)
  implementation(projects.core.ui)
  implementation(projects.core.utils)
  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.activity)
  implementation(libs.androidx.constraintlayout)

  // modules for unit test
  testImplementation(projects.core.local)
  testImplementation(projects.core.test)
  androidTestImplementation(projects.core.test)

  // androidx
  implementation(libs.material)
  implementation(libs.androidx.fragment)
  implementation(libs.androidx.lifecycle)
  implementation(libs.androidx.startup)
  implementation(libs.androidx.palette)

  // Compose
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.compose.ui.tooling)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation(libs.androidx.compose.foundation)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.activity.compose)

  // Navigation
  implementation(libs.androidx.navigation.fragment)
  implementation(libs.androidx.navigation.ui)

  // di
  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)
  androidTestImplementation(libs.hilt.testing)
  kspAndroidTest(libs.hilt.compiler)

  // coroutines
  implementation(libs.coroutines)

  // image loading
  implementation(libs.glide)

  // unit test
  testImplementation(libs.junit)
  testImplementation(libs.turbine)
  testImplementation(libs.androidx.test.core)
  testImplementation(libs.mockito.core)
  testImplementation(libs.mockito.kotlin)
  testImplementation(libs.coroutines.test)
  androidTestImplementation(libs.truth)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso)
  androidTestImplementation(libs.android.test.runner)

  implementation(libs.multidex)
}

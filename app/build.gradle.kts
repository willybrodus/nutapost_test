import com.nutapos.nutatest.Configuration

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose.compiler)
  alias(libs.plugins.kotlin.kapt)
  alias(libs.plugins.ksp)
  alias(libs.plugins.kotlin.parcelize)
  alias(libs.plugins.hilt.plugin)
  alias(libs.plugins.navigation.safeargs.kotlin)
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
  implementation(projects.core.ui)
  implementation(projects.core.domain)
  implementation(projects.core.data)
  implementation(projects.core.local)
  implementation(projects.core.utils)
  implementation(projects.feature.cashIn)
  implementation(projects.feature.cashOut)
  implementation(projects.feature.customers)
  implementation(projects.feature.proof)
  implementation(projects.feature.user)
  implementation(projects.feature.home)

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.androidx.constraintlayout)
  implementation(libs.androidx.navigation.fragment)
  implementation(libs.androidx.navigation.ui)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso)

  // Hilt
  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)

  // Compose
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation(libs.androidx.compose.foundation)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.activity.compose)
}

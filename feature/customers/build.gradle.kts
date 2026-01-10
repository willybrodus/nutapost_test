plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose.compiler)
  alias(libs.plugins.kotlin.kapt)
  alias(libs.plugins.ksp)
  alias(libs.plugins.kotlin.parcelize)
  alias(libs.plugins.hilt.plugin)
  alias(libs.plugins.navigation.safeargs.kotlin)
}

android {
  namespace = "com.nutapos.nutatest.feature.customers"
  sourceSets {
    getByName("main") {
      java.srcDirs("src/main/kotlin")
    }
  }
  buildFeatures {
    compose = true
  }
}

dependencies {
  implementation(projects.core.domain)
  implementation(projects.core.utils)
  implementation(projects.core.ui)

  // Compose
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.compose.ui.tooling.preview)
  debugImplementation(libs.androidx.compose.ui.tooling)
  implementation(libs.androidx.compose.foundation)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.activity.compose)

  implementation(libs.coroutines)
  implementation(libs.coroutines.test)
  // Hilt
  testImplementation(projects.core.test)

  // coroutines
  implementation(libs.coroutines)
  testImplementation(libs.coroutines)
  testImplementation(libs.coroutines.test)

  // di
  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)

  // unit test
  testImplementation(libs.junit)
  testImplementation(libs.turbine)
  testImplementation(libs.androidx.test.core)
  testImplementation(libs.mockito.core)
  testImplementation(libs.mockito.kotlin)

  // androidx
  implementation(libs.material)
  implementation(libs.androidx.fragment)
  implementation(libs.androidx.lifecycle)
  implementation(libs.androidx.startup)
  implementation(libs.androidx.palette)

  // Navigation
  implementation(libs.androidx.navigation.fragment)
  implementation(libs.androidx.navigation.ui)
}

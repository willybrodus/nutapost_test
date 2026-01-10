plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose.compiler)
  alias(libs.plugins.ksp)
}

android {
  namespace = "com.nutapos.nutatest.feature.proof"
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
  implementation(projects.feature.commonUi)

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
}

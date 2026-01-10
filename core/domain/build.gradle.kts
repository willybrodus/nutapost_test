plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.kapt)
  alias(libs.plugins.ksp)
  alias(libs.plugins.kotlin.parcelize)
  alias(libs.plugins.hilt.plugin)
}


android {
  namespace = "com.nutapos.nutatest.core.domain"
  sourceSets {
    getByName("main") {
      java.srcDirs("src/main/kotlin")
    }
  }
}

dependencies {
  implementation(projects.core.data)
  implementation(projects.core.local)
  implementation(projects.core.utils)

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

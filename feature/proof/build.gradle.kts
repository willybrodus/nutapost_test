plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
}

android {
  namespace = "com.nutapos.nutatest.feature.proof"
  sourceSets {
    getByName("main") {
      java.srcDirs("src/main/kotlin")
    }
  }
}

dependencies {
  implementation(projects.core.domain)
  implementation(libs.coroutines)
  implementation(libs.coroutines.test)
  implementation(libs.junit)
}

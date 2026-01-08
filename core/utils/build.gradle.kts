plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.parcelize)
  alias(libs.plugins.ksp)
}

android {
  namespace = "com.nutapos.nutatest.core.model"
  sourceSets {
    getByName("main") {
      java.srcDirs("src/main/kotlin")
    }
  }
}

dependencies {
  // logger
  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.core.ktx)
}

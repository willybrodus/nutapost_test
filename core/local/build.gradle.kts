plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.ksp)
}

android {
  namespace = "com.nutapos.nutatest.core.local"
  sourceSets {
    getByName("main") {
      java.srcDirs("src/main/kotlin")
    }
  }
}

dependencies {
  // room
  implementation(libs.room.runtime)
  implementation(libs.room.ktx)
  ksp(libs.room.compiler)

  // di
  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)
}

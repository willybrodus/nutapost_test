enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")



pluginManagement {
  repositories {
    // fetch plugins from google maven (https://maven.google.com)
    google() {
      content {
        includeGroupByRegex("androidx\\..*")
        includeGroupByRegex("com\\.android(\\..*|)")
        includeGroupByRegex("com\\.google\\.android\\..*")
        includeGroupByRegex("com\\.google\\.firebase(\\..*|)")
        includeGroupByRegex("com\\.google\\.gms(\\..*|)")
        includeGroupByRegex("com\\.google\\.mlkit")
        includeGroupByRegex("com\\.google\\.oboe")
        includeGroupByRegex("com\\.google\\.prefab")
        includeGroupByRegex("com\\.google\\.testing\\.platform")
      }
      mavenContent {
        releasesOnly()
      }
    }

    // fetch dagger plugin only
    mavenCentral() {
      content {
        includeGroup("com.google.dagger")
        includeGroup("com.google.dagger.hilt.android")
      }
      mavenContent {
        releasesOnly()
      }
    }

    // fetch plugins from gradle plugin portal (https://plugins.gradle.org)
    gradlePluginPortal()

    // fetch snapshot plugins from sonatype
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/") {
      mavenContent {
        snapshotsOnly()
      }
    }
  }
}

plugins {
  id("com.android.settings") version "8.10.1"
}

android {
  minSdk = 23
  compileSdk = 35
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    // fetch libraries from google maven (https://maven.google.com)
    google() {
      content {
        includeGroupByRegex("androidx\\..*")
        includeGroupByRegex("com\\.android(\\..*|)")
        includeGroupByRegex("com\\.google\\.android\\..*")
        includeGroupByRegex("com\\.google\\.firebase(\\..*|)")
        includeGroupByRegex("com\\.google\\.gms(\\..*|)")
        includeGroupByRegex("com\\.google\\.mlkit")
        includeGroupByRegex("com\\.google\\.oboe")
        includeGroupByRegex("com\\.google\\.prefab")
        includeGroupByRegex("com\\.google\\.testing\\.platform")
      }
      mavenContent {
        releasesOnly()
      }
    }

    // fetch libraries from maven central
    mavenCentral() {
      mavenContent {
        releasesOnly()
      }
    }

    // fetch snapshot libraries from sonatype
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/") {
      mavenContent {
        snapshotsOnly()
      }
    }
  }
}
rootProject.name = "NutaTest"
include(":app")
include(":core:domain")
include(":core:local")
include(":core:data")
include(":core:test")
include(":core:ui")
include(":core:utils")
include(":feature:cash_in")
include(":feature:cash_out")
include(":feature:customers")
include(":feature:proof")

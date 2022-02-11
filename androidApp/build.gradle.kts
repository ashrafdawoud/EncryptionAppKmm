plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version "1.6.10"
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.BFCAI.encryptionapp.android"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    packagingOptions {
        packagingOptions {
            resources.excludes.add("META-INF/gradle/incremental.annotation.processors")
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
        composeOptions.kotlinCompilerExtensionVersion = "1.2.0-alpha02"
        composeOptions.kotlinCompilerVersion = "1.6.10"
    }
    defaultConfig {
        multiDexEnabled = true
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation("com.google.accompanist:accompanist-coil:0.10.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    // Integration with observables
    val composeActivitiesVersion = "1.3.0-beta01"
    implementation("androidx.activity:activity-compose:${composeActivitiesVersion}")
    implementation("com.google.dagger:hilt-android:2.37")
    implementation("androidx.hilt:hilt-navigation:1.0.0-alpha03")
    configurations.getByName("kapt").dependencies.add(
        implementation("com.google.dagger:hilt-compiler:2.37")
    )
    implementation("io.ktor:ktor-client-android:1.5.2")
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.0-beta02")
/////////////////
    dependencies {
        implementation("androidx.compose.ui:ui:1.0.5")
        // Tooling support (Previews, etc.)
        implementation("androidx.compose.ui:ui-tooling:1.0.5")
        // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
        implementation("androidx.compose.foundation:foundation:1.0.5")
        // Material Design
        implementation("androidx.compose.material:material:1.0.5")
        // Material design icons
        implementation("androidx.compose.material:material-icons-core:1.0.5")
        implementation("androidx.compose.material:material-icons-extended:1.0.5")
        // Integration with observables
        implementation("androidx.compose.runtime:runtime-livedata:1.0.5")
        implementation("androidx.compose.runtime:runtime-rxjava2:1.0.5")
        implementation("androidx.navigation:navigation-compose:2.5.0-alpha01")

        // UI Tests
        androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.0.5")
    }

}
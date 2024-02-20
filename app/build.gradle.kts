import com.android.build.gradle.internal.api.ReadOnlyProductFlavor
import org.gradle.internal.extensibility.DefaultExtraPropertiesExtension

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.gradle.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.skeleton"
    compileSdk = 34
    flavorDimensions += "default"
    defaultConfig {
        applicationId = "com.example.skeleton"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        resourceConfigurations.addAll(listOf("en", "ar"))
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    productFlavors {
        create("prod") {
            extra.apply {
                set(
                    "server",
                    mapOf(
                        "debug" to "https://restful-booker.herokuapp.com/",
                        "release" to "https://restful-booker.herokuapp.com/"
                    )
                )
                set("isTest", mapOf("debug" to true, "release" to false))
            }

        }
        create("staging") {
            extra.apply {
                set(
                    "server",
                    mapOf(
                        "debug" to "https://restful-booker.herokuapp.com/",
                        "release" to "https://restful-booker.herokuapp.com/"
                    )
                )
                set("isTest", mapOf("debug" to true, "release" to false))
            }

        }
    }
    applicationVariants.configureEach {
        val flavor = productFlavors[0] as ReadOnlyProductFlavor
        val extra = flavor.getProperty("ext") as DefaultExtraPropertiesExtension
        buildConfigField(
            "String",
            "BASE_URL",
            "\"${(extra.get("server") as Map<*, *>)[buildType.name]}\""
        )
        buildConfigField(
            "boolean",
            "isTest",
            "${(extra.get("isTest") as Map<*, *>)[buildType.name]}"
        )
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    //Facebook text encryption
    implementation(libs.facebook.conceal)
    //Ktor
    implementation(libs.ktor.android)
    implementation(libs.ktor.core)
    implementation(libs.ktor.logging)
    implementation(libs.ktor.content)
    implementation(libs.ktor.serialization)
    //Dex
    implementation(libs.androidx.multidex)
    //Gson
    implementation(libs.google.gson)
    //android navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.splashscreen)
    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.activity)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.preview)
    implementation(libs.compose.material3)
    //room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    //Koin
    //Koin Core features
    implementation(libs.koin.android)
    implementation(libs.koin.core)

    // Koin Test features
    implementation(libs.koin.compose)
    testImplementation(libs.koin.test)
    testImplementation(libs.junit)
    androidTestImplementation(libs.android.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
}

plugins {
    id("com.android.application") version "8.2.2"
    id("org.jetbrains.kotlin.android") version "1.9.22"
}

android {
    namespace = "com.myanmar.warpvpn"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.myanmar.warpvpn"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"

        // Handle ABI filtering for different architectures
        val abiFilterList = (properties["ABI_FILTERS"] as? String)?.split(';')
        splits {
            abi {
                isEnable = true
                reset()
                if (!abiFilterList.isNullOrEmpty()) {
                    include(*abiFilterList.toTypedArray())
                } else {
                    include(
                        "arm64-v8a",
                        "armeabi-v7a",
                        "x86_64",
                        "x86"
                    )
                }
                // Generate a universal APK only if no specific filters are applied
                isUniversalApk = abiFilterList.isNullOrEmpty()
            }
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        externalNativeBuild {
            cmake {
                cppFlags("")
            }
        }
    }

    // Configure CMake for Native C++ (NDK) support
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }

    signingConfigs {
        if (System.getenv("SIGNING_KEYSTORE_PATH") != null) {
            create("release") {
                storeFile = file(System.getenv("SIGNING_KEYSTORE_PATH"))
                storePassword = System.getenv("SIGNING_STORE_PASSWORD")
                keyAlias = System.getenv("SIGNING_KEY_ALIAS")
                keyPassword = System.getenv("SIGNING_KEY_PASSWORD")
            }
        }
    }

    buildTypes {
        release {
            // SECURITY: Disable debugging in release builds to prevent reverse engineering
            isDebuggable = false
            
            // Enable code shrinking, obfuscation, and optimization
            isMinifyEnabled = true
            isShrinkResources = true
            
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            if (System.getenv("SIGNING_KEYSTORE_PATH") != null) {
                signingConfig = signingConfigs.getByName("release")
            }
        }
        
        debug {
            // Enable debugging for development builds
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // WireGuard Official Android SDK for VPN functionality
    implementation("com.wireguard.android:tunnel:1.0.20211029")

    // Network / Coroutines / JSON libraries
    implementation("com.squareup.okhttp3:okhttp:4.12.0" )
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("com.google.code.gson:gson:2.10.1")
}

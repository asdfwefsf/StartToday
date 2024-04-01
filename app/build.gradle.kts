plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.company.starttoday"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.company.starttoday"
        minSdk = 30
        targetSdk = 34
        versionCode = 5
        versionName = "1.0"


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(Deps.CORE)
    implementation(Deps.LIFECYCLEKTX)
    implementation(Deps.ACTIVITYCOMPOSE)
    implementation(platform(Deps.COMPOSEBOM))

    implementation(Deps.UI)
    implementation(Deps.UIGRAPHICS)
    implementation(Deps.UITOOLINGPREVIEW)
    implementation(Deps.MATERIAL3)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.hilt:hilt-common:1.2.0")
    implementation("androidx.hilt:hilt-work:1.2.0")

    testImplementation(Deps.JUNIT)

    androidTestImplementation(Deps.EXTJUNIT)
    androidTestImplementation(Deps.ESPRESSOCORE)
    androidTestImplementation(platform(Deps.PLATFORMCOMPOSEBOM))
    androidTestImplementation(Deps.UITESTJUNIT)
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    debugImplementation(Deps.UITOOLING)
    debugImplementation(Deps.UITESTMANIFEST)

    // Hilt
    implementation(Deps.HILTANDROID)
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    kapt(Deps.HILTANDROIDCOMPILER)

    // Retrofit
    implementation(Deps.RETROFIT)

    // GsonConverter
    implementation(Deps.GSON)

    // OkHttp
    implementation(Deps.OKHTTP)

    // Compose Navigation
    implementation(Deps.COMPOSENAVIGATION)
    implementation(Deps.HILTNAVIGATION)

    // ViewModel
    implementation(Deps.VIEWMODEL)

    // Compose_ViewModel
    implementation(Deps.COMPOSE_VIEWMODEL)

    // RoomDB
    implementation(Deps.ROOM_DB)
    kapt(Deps.ROOM_DB_COMPILER)

    // CoilComopose
    implementation(Deps.COILCOMPOSE)

    // ComposeLifecycle
    implementation(Deps.COMPOSELIFECYCLE)

    // ComposeConstraint
    implementation(Deps.COMPOSECONSTRAINT)

    // SplashScreen
    implementation(Deps.SPLASHSCREEN)


    implementation("androidx.work:work-runtime-ktx:2.9.0")
    kapt ("androidx.hilt:hilt-compiler:1.2.0")


    implementation("com.google.android.gms:play-services-location:21.2.0")



}

kapt {
    correctErrorTypes = true
}
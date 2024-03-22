object Version {
    const val CORE = "1.12.0"
    const val LIFECYCLEKTX = "2.6.2"
    const val ACTIVITYCOMPOSE = "1.8.2"
    const val COMPOSEBOM = "2023.03.00"
    const val JUNIT = "4.13.2"
    const val EXTJUNIT = "1.1.5"
    const val ESPRESSOCORE = "3.5.1"
    const val APPCOMPAT = "1.6.1"
    const val MATERIAL = "1.11.0"

    // Retrofit
    const val RETROFIT = "2.9.0"
    // GsonConverter
    const val GSON = "2.9.0"
    // OKHTTP
    const val OKHTTP = "4.11.0"
    // DaggerHilt
    const val HILTANDROID = "2.48"
    // HiltCompiler
    const val HILTANDROIDCOMPILER = "2.48"
    // ComposeNavigation
    const val COMPOSENAVIGATION = "2.5.3"
    // HiltNavigation
    const val HILTNAVIGATION = "1.2.0"
    // LifeCycle
    const val LIFECYCLE = "2.7.0"
    // RoomDB
    const val Room = "2.6.1"
    // CoilCompose
    const val COILCOMPOSE = "2.6.0"
    // ComposeLifeCycle
    const val COMPOSELIFECYCLE = "2.7.0"
    // ComposeConstraint
    const val COMPOSECONSTRAINT = "1.0.1"
    // SplashScreen
    const val SPLASHSCREEN = "1.0.1"
}




object Deps {
    // implementation
    const val CORE = "androidx.core:core-ktx:${Version.CORE}"
    const val LIFECYCLEKTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.LIFECYCLEKTX}"
    const val ACTIVITYCOMPOSE = "androidx.activity:activity-compose:${Version.ACTIVITYCOMPOSE}"
    const val COMPOSEBOM = "androidx.compose:compose-bom:${Version.COMPOSEBOM}"
    const val UI = "androidx.compose.ui:ui"
    const val UIGRAPHICS = "androidx.compose.ui:ui-graphics"
    const val UITOOLINGPREVIEW = "androidx.compose.ui:ui-tooling-preview"
    const val MATERIAL3 = "androidx.compose.material3:material3"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Version.APPCOMPAT}"
    const val MATERIAL = "com.google.android.material:material:${Version.MATERIAL}"

    // testImplementation
    const val JUNIT = "junit:junit:${Version.JUNIT}"

    // androidTestImplementation
    const val EXTJUNIT = "androidx.test.ext:junit:${Version.EXTJUNIT}"
    const val ESPRESSOCORE = "androidx.test.espresso:espresso-core:${Version.ESPRESSOCORE}"
    const val PLATFORMCOMPOSEBOM = "androidx.compose:compose-bom:${Version.COMPOSEBOM}"
    const val UITESTJUNIT = "androidx.compose.ui:ui-test-junit4"

    // debugImplementation
    const val UITOOLING = "androidx.compose.ui:ui-tooling"
    const val UITESTMANIFEST = "androidx.compose.ui:ui-test-manifest"

    // Retrofit
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Version.RETROFIT}"
    // Gson
    const val GSON = "com.squareup.retrofit2:converter-gson:${Version.GSON}"
    // OkHttp
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Version.OKHTTP}"
    // Hilt
    const val HILTANDROID = "com.google.dagger:hilt-android:${Version.HILTANDROID}"
    // HiltCompiler
    const val HILTANDROIDCOMPILER = "com.google.dagger:hilt-android-compiler:${Version.HILTANDROIDCOMPILER}"
    // Compose_Navigation
    const val COMPOSENAVIGATION = "androidx.navigation:navigation-compose:${Version.COMPOSENAVIGATION}"
    // Hilt_Navegation
    const val HILTNAVIGATION = "androidx.hilt:hilt-navigation-compose:${Version.HILTNAVIGATION}"
    // ViewModel
    const val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.LIFECYCLE}"
    // Compose_VIEWMODEL
    const val COMPOSE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-compose:${Version.LIFECYCLE}"
    // RoomDB
    const val ROOM_DB = "androidx.room:room-ktx:${Version.Room}"
    // RoomDB_Compiler
    const val ROOM_DB_COMPILER = "androidx.room:room-compiler:${Version.Room}"
    // CoilCompose
    const val COILCOMPOSE = "io.coil-kt:coil-compose:${Version.COILCOMPOSE}"
    // ComposeLifeCycle
    const val COMPOSELIFECYCLE = "androidx.lifecycle:lifecycle-runtime-compose:${Version.COMPOSELIFECYCLE}"
    // ComposeConstraint
    const val COMPOSECONSTRAINT = "androidx.constraintlayout:constraintlayout-compose:${Version.COMPOSECONSTRAINT}"
    // SplashScreen
    const val SPLASHSCREEN = "androidx.core:core-splashscreen:${Version.SPLASHSCREEN}"

}
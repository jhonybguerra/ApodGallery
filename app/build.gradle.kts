plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.apodgallery"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.apodgallery"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

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
    viewBinding {
        enable = true
    }
}

dependencies {

    val roomVersion = "2.6.1"
    val hiltVersion = "2.50"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    //Room
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")

    ksp("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    //Test Coroutines
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")

    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")

    //Hilt-KSP
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    ksp("com.google.dagger:dagger-compiler:$hiltVersion") // Dagger compiler
    ksp ("com.google.dagger:hilt-compiler:$hiltVersion")   // Hilt compiler

    // For instrumentation tests HILT
    androidTestImplementation("com.google.dagger:hilt-android-testing:$hiltVersion")
    androidTestAnnotationProcessor("com.google.dagger:hilt-compiler:$hiltVersion")

    // For local unit tests HILT
    testImplementation("com.google.dagger:hilt-android-testing:$hiltVersion")
    testAnnotationProcessor("com.google.dagger:hilt-compiler:$hiltVersion")

    //Test Mockito
    testImplementation("org.mockito:mockito-core:4.0.0") // Use a versão mais recente compatível
    testImplementation("org.mockito:mockito-inline:4.0.0") // Para suporte a mock de funções finais

    //Test MockK
    //testImplementation ("io.mockk:mockk:1.12.0")

    //Test JUnit
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")

    //Test Espresso
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.oscararbo.retrofitexample"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.oscararbo.retrofitexample"
        minSdk = 30
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
}

//com.google.code.gson:gson:2.11.0
//com.squareup.retrofit2:retrofit:2.11.0
//com.squareup.retrofit2:converter-gson:2.11.0
//implementation("com.squareup.okhttp3:okhttp:4.12.0")
//implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")


dependencies {
    implementation(libs.gson)
    implementation(libs.retrofit2.retrofit)
    implementation(libs.squareup.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation (libs.com.squareup.retrofit2.retrofit.v290.x3)
    implementation (libs.converter.gson.v290)
    implementation (libs.glide)
    annotationProcessor (libs.compiler)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.retrofit2.retrofit)
    implementation(libs.squareup.converter.gson)
    implementation(libs.gson)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
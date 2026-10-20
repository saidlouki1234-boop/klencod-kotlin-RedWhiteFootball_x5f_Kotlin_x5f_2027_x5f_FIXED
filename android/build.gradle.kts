
plugins { id("com.android.application"); kotlin("android") }
android {
    namespace = "com.redwhite.football2027"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.redwhite.football2027"
        minSdk = 24; targetSdk = 34; versionCode = 1; versionName = "1.0.0"
    }
    buildTypes {
        release { isMinifyEnabled = false }
        debug { isMinifyEnabled = false }
    }
}
dependencies {
    implementation(project(":core"))
    implementation("com.badlogicgames.gdx:gdx-backend-android:1.12.1")
    implementation("com.badlogicgames.gdx:gdx-platform:1.12.1:natives-armeabi-v7a")
    implementation("com.badlogicgames.gdx:gdx-platform:1.12.1:natives-arm64-v8a")
}

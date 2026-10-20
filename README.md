
# RedWhiteFootball 2027 - Android Kotlin + LibGDX

## هذه النسخة الثانية Native
بديل للنسخة Unity، مكتوبة بـ Kotlin 100% باستخدام LibGDX.

### المميزات
- حركة لاعبين + كرة بفيزياء مبسطة
- تحكم باللمس: يسار الشاشة Joystick افتراضي، يمين الشاشة تسديد مشحون
- نظام أهداف: مرميان علوي وسفلي (أحمر)
- ذكاء اصطناعي بسيط للخصم يطارد الكرة
- ألوان النادي الإفريقي #D10A1F / أبيض
- واجهة عربية
- TeamsRepo يحتوي 8 فرق

### التشغيل
1. افتح في Android Studio Hedgehog+
2. File > Open > مجلد RedWhiteFootball_Kotlin_2027
3. Sync Gradle
4. أضف ملف uiskin.json إلى android/assets (حمله من LibGDX skins) أو استخدم Skin افتراضي
5. Run على هاتف Android 8+

### البناء APK
./gradlew :android:assembleDebug  -> app/build/outputs/apk/debug/android-debug.apk
./gradlew :android:assembleRelease

### لماذا Kotlin وليس Java؟
Kotlin أحدث، Null Safety، Coroutines، و Google توصي به رسمياً.

### ملاحظة
لإضافة خط عربي: ضع Cairo-Regular.ttf في assets وضع FreeTypeFontGenerator لإنشاء BitmapFont يدعم العربية.

# OSiris
A reflection toolkit for Android applications

  - Automated patching after building
  - Installs to phone through USB after build
  - Magic

# How to use

  - Download Android studio if required from https://developer.android.com/studio
  - In the SDK manager, download API 23 and 29
  - Import the project like you would any other project
  - Place the latest OSRS apk under app/osrs.apk
  - To run from your phone, you must setup USB Debugging on your phone in Developer Settings
  - Otherwise you can just take the apk from build/autputs/apk/app-debug.apk and install that manually


Current functionality:
  - This is a framework, and has only proof of concept functionality
  - LocalPlayer is fetched, then the Display Name, and is printed every 5000ms to the error log

### Libraries

OSiris relies on the following tools, much love to the developers of them!

[Apktool] - APK decoding / encoding AIO  
[Apk Sign] - APK signing

   [Apktool]: <https://ibotpeaches.github.io/Apktool/>
   [Apk Sign]: <https://github.com/appium/sign>

### License

OSiris is licensed under the BSD-3-Clause License.

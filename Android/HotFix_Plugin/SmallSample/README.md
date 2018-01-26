1 抽离前
```
E:\code\studio\sample\SmallSample>aapt dump --values resources app/smallLibs/armeabi/libcom_ztiany_app_main.so
Package Groups (1)
Package Group 0 id=0x77 packageCount=1 name=com.ztiany.app.main
  DynamicRefTable entryCount=1:
    0x77 -> com.ztiany.app.main

  Package 0 id=0x77 name=com.ztiany.app.main
    type 1 configCount=1 entryCount=1
      spec resource 0x77020000 com.ztiany.app.main:layout/activity_main: flags=0x00000000
      config (default):
        resource 0x77020000 com.ztiany.app.main:layout/activity_main: t=0x03 d=0x00000000 (s=0x0008 r=0x00)
          (string8) "res/layout/activity_main.xml"
    type 2 configCount=1 entryCount=1
      spec resource 0x77030000 com.ztiany.app.main:style/AppTheme: flags=0x00000000
      config (default):
        resource 0x77030000 com.ztiany.app.main:style/AppTheme: <bag>
          Parent=0x7f080117(Resolved=0x7f080117), Count=3
          #0 (Key=0x7f0100b2): (reference) 0x77040001
          #1 (Key=0x7f0100b3): (reference) 0x77040002
          #2 (Key=0x7f0100b4): (reference) 0x77040000
    type 3 configCount=1 entryCount=3
      spec resource 0x77040000 com.ztiany.app.main:color/colorAccent: flags=0x00000000
      spec resource 0x77040001 com.ztiany.app.main:color/colorPrimary: flags=0x00000000
      spec resource 0x77040002 com.ztiany.app.main:color/colorPrimaryDark: flags=0x00000000
      config (default):
        resource 0x77040000 com.ztiany.app.main:color/colorAccent: t=0x1d d=0xffff4081 (s=0x0008 r=0x00)
          (color) #ffff4081
        resource 0x77040001 com.ztiany.app.main:color/colorPrimary: t=0x1d d=0xff3f51b5 (s=0x0008 r=0x00)
          (color) #ff3f51b5
        resource 0x77040002 com.ztiany.app.main:color/colorPrimaryDark: t=0x1d d=0xff303f9f (s=0x0008 r=0x00)
          (color) #ff303f9f
    type 4 configCount=1 entryCount=1
      spec resource 0x77050000 com.ztiany.app.main:id/tag_transition_group: flags=0x00000000
      config (default):
        resource 0x77050000 com.ztiany.app.main:id/tag_transition_group: t=0x03 d=0x00000001 (s=0x0008 r=0x00)
          (string8) ""
```
2  抽离后
```
E:\code\studio\sample\SmallSample>aapt dump --values resources app/smallLibs/armeabi/libcom_ztiany_app_main.so
Package Groups (1)
Package Group 0 id=0x77 packageCount=1 name=com.ztiany.app.main
  DynamicRefTable entryCount=1:
    0x77 -> com.ztiany.app.main

  Package 0 id=0x77 name=com.ztiany.app.main
    type 1 configCount=1 entryCount=1
      spec resource 0x77020000 com.ztiany.app.main:layout/activity_main: flags=0x00000000
      config (default):
        resource 0x77020000 com.ztiany.app.main:layout/activity_main: t=0x03 d=0x00000000 (s=0x0008 r=0x00)
          (string8) "res/layout/activity_main.xml"
    type 2 configCount=1 entryCount=1
      spec resource 0x77030000 com.ztiany.app.main:id/tag_transition_group: flags=0x00000000
      config (default):
        resource 0x77030000 com.ztiany.app.main:id/tag_transition_group: t=0x03 d=0x00000001 (s=0x0008 r=0x00)
          (string8) ""
```
3
```
E:\code\studio\sample\SmallSample>aapt dump --values xmltree  app/smallLibs/armeabi/libcom_ztiany_app_main.so AndroidManifest.xml
N: android=http://schemas.android.com/apk/res/android
  E: manifest (line=2)
    A: android:versionCode(0x0101021b)=(type 0x10)0x1
    A: android:versionName(0x0101021c)="1.0" (Raw: "1.0")
    A: package="com.ztiany.app.main" (Raw: "com.ztiany.app.main")
    A: platformBuildVersionCode=(type 0x10)0x1b (Raw: "27")
    A: platformBuildVersionName="8.1.0" (Raw: "8.1.0")
    E: uses-sdk (line=7)
      A: android:minSdkVersion(0x0101020c)=(type 0x10)0x13
      A: android:targetSdkVersion(0x01010270)=(type 0x10)0x1b
    E: application (line=11)
      A: android:theme(0x01010000)=@0x79020000
      A: android:roundIcon(0x0101052c)=@0x7f030001
      E: activity (line=14)
        A: android:name(0x01010003)="com.ztiany.app.main.MainActivity" (Raw: "com.ztiany.app.main.MainActivity")
        E: intent-filter (line=15)
          E: action (line=16)
            A: android:name(0x01010003)="android.intent.action.MAIN" (Raw: "android.intent.action.MAIN")
          E: category (line=18)
            A: android:name(0x01010003)="android.intent.category.LAUNCHER" (Raw: "android.intent.category.LAUNCHER")
```
4
```
E:\code\studio\sample\SmallSample>aapt dump --values resources app/smallLibs/armeabi/libcom_ztiany_lib_style.so
Package Groups (1)
Package Group 0 id=0x79 packageCount=1 name=com.ztiany.lib.style
  DynamicRefTable entryCount=1:
    0x79 -> com.ztiany.lib.style

  Package 0 id=0x79 name=com.ztiany.lib.style
    type 1 configCount=1 entryCount=1
      spec resource 0x79020000 com.ztiany.lib.style:style/AppTheme: flags=0x00000000
      config (default):
        resource 0x79020000 com.ztiany.lib.style:style/AppTheme: <bag>
          Parent=0x7f080117(Resolved=0x7f080117), Count=3
          #0 (Key=0x7f0100b2): (reference) 0x79030001
          #1 (Key=0x7f0100b3): (reference) 0x79030002
          #2 (Key=0x7f0100b4): (reference) 0x79030000
    type 2 configCount=1 entryCount=3
      spec resource 0x79030000 com.ztiany.lib.style:color/colorAccent: flags=0x00000000
      spec resource 0x79030001 com.ztiany.lib.style:color/colorPrimary: flags=0x00000000
      spec resource 0x79030002 com.ztiany.lib.style:color/colorPrimaryDark: flags=0x00000000
      config (default):
        resource 0x79030000 com.ztiany.lib.style:color/colorAccent: t=0x1d d=0xffff4081 (s=0x0008 r=0x00)
          (color) #ffff4081
        resource 0x79030001 com.ztiany.lib.style:color/colorPrimary: t=0x1d d=0xff992233 (s=0x0008 r=0x00)
          (color) #ff992233
        resource 0x79030002 com.ztiany.lib.style:color/colorPrimaryDark: t=0x1d d=0xff303f9f (s=0x0008 r=0x00)
          (color) #ff303f9f
```

保证系统在合并这些插件的时候可以正确的追溯到主题资源。
你可以进一步 dump 宿主包查看该资源的定义，该资源就是 AppCompat 包所带的资源，这也是之所以 Small 能支持 AppCompat 的本质原因。
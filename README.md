This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.



* `voyager-navigation` for navigation from once screen to another use voyager open source library.
* `viewmodel`  For viewmodel we used ScreenModel powered by Voyager
* `Koin` For adding dependency
* `Realm` for save todo task in db

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…



https://github.com/BeingCoder786/ToDoYojana/assets/60469750/a64bb501-1a2d-4282-96bc-330511744d50

https://github.com/BeingCoder786/ToDoYojana/assets/60469750/fd98d96b-2c58-42ca-858c-f4d3d3b31333






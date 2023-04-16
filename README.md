# Android template project

This is a template for any kind of Android project with UDF and Clean Architecture.

:warning: It's rely on XML views. :warning:

## Features & Dependencies

### Production

Core
- Material Design 3 and basic Android views (ConstraintLayout, RecycleView, CardView)
- Android navigation component, Lifecycle (with RX support) and ViewModel
- DI with [Koin](https://github.com/InsertKoinIO/koin)
- RxJava3 with Android and Kotlin support
- Logging by [Timber](https://github.com/JakeWharton/timber)
- Networking with Retrofit2 and OKHttp3 by [Square](https://github.com/square)
- JSON serialization with [Moashi](https://github.com/square/moshi)

Firebase (Only free to use)
- Authentication: Anonymous sign in
- Crashlytics
- Analytics
- Performance monitoring

### Testing

- Androidx fragment testing
- Androidx test orchestrator (setup needed)
- Mocking with [Mockk](https://mockk.io/)
- Mockwebserver by [Square](https://github.com/square)
- DSL for Espresso with [Kakao](https://github.com/KakaoCup/Kakao)

### Extras

- Shared preferences support (only for generic types)
- Setup for integration testing (with Koin, fake implementations and core mocks)
- Setup for UI testing with mockwebserver management, `ActivityScenarioRule` and `KoinTestRule`
- Exported run configuration for running all unit and UI tests (in the .run folder)
- Exported code style and editor config in the project root folder
- Some very basic extension functions

## Code conventions

### Base presentation components

The current version has the following core view components to handle Rx disposable easier and make implementation easier.

**BaseUdfViewModel:** Force UDF, Rx disposing and lifecycle logging

**BaseActivity:** Default `BaseViewModel`, lifecycle logging and LiveData support.

**BaseFragment:** Default `BaseUdfViewModel` (support UDF architecture), LiveData, lifecycle logging and viewmodel management

****

Exported cody style follow official Kotlin guidline by Intellij with blank lines and trailing comas.

Targeted single activity approach.

Following [Conventional Commits](https://cheatography.com/albelop/cheat-sheets/conventional-commits/)

## Additional information

Empty packages having .getkeep files

There are example integration and UI tests.

There is two Fragments to simulate navigation and user interaction.

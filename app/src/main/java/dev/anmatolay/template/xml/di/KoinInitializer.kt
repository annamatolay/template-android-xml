package dev.anmatolay.template.xml.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

object KoinInitializer {

    fun init(application: Application) {
        startKoin {
            allowOverride(true)
            androidContext(application)
            modules(
                appModule,
                viewModelModule,
            )
        }
    }
}

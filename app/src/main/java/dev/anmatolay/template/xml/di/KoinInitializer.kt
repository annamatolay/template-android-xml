package dev.anmatolay.template.xml.di

import android.app.Application
import dev.anmatolay.template.xml.di.module.dataSourceModule
import dev.anmatolay.template.xml.di.module.firebaseModule
import dev.anmatolay.template.xml.di.module.useCaseModule
import dev.anmatolay.template.xml.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

object KoinInitializer {

    fun init(application: Application) {
        startKoin {
            allowOverride(true)
            androidContext(application)
            modules(
                appModule,
                firebaseModule,
                viewModelModule,
                useCaseModule,
                dataSourceModule,
            )
        }
    }
}

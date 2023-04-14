package dev.anmatolay.template.xml.core.di

import android.app.Application
import dev.anmatolay.template.xml.core.di.module.appModule
import dev.anmatolay.template.xml.core.di.module.dataSourceModule
import dev.anmatolay.template.xml.core.di.module.firebaseModule
import dev.anmatolay.template.xml.core.di.module.useCaseModule
import dev.anmatolay.template.xml.core.di.module.viewModelModule
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

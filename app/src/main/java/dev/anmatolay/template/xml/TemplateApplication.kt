package dev.anmatolay.template.xml

import android.app.Application
import dev.anmatolay.template.xml.di.KoinInitializer
import dev.anmatolay.template.xml.util.logging.CrashReportingTree
import dev.anmatolay.template.xml.util.logging.DiamondDebugTree
import timber.log.Timber

class TemplateApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KoinInitializer.init(this)

        Timber.plant(
            if (BuildConfig.DEBUG)
                DiamondDebugTree()
            else
                CrashReportingTree()
        )
    }
}

package dev.anmatolay.template.xml

import android.app.Application
import dev.anmatolay.template.xml.di.KoinInitializer

class TemplateApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KoinInitializer.init(this)
    }
}

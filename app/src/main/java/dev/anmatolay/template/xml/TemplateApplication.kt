package dev.anmatolay.template.xml

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dev.anmatolay.template.xml.core.authentication.Authenticator
import dev.anmatolay.template.xml.di.KoinInitializer
import dev.anmatolay.template.xml.util.logging.CrashlyticsLogTree
import dev.anmatolay.template.xml.util.logging.DiamondDebugTree
import org.koin.android.ext.android.inject
import timber.log.Timber

class TemplateApplication : Application() {

    private val firebaseCrashlytics by inject<FirebaseCrashlytics>()
    private val authenticator by inject<Authenticator>()

    override fun onCreate() {
        super.onCreate()

        KoinInitializer.init(this)

        Timber.plant(
            if (BuildConfig.DEBUG)
                DiamondDebugTree()
            else
                CrashlyticsLogTree(firebaseCrashlytics)
        )

        authenticator.signInAnonymously()
            .subscribe()
            .dispose()
    }
}

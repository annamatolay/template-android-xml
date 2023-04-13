package dev.anmatolay.template.xml

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dev.anmatolay.template.xml.core.authentication.Authenticator
import dev.anmatolay.template.xml.di.KoinInitializer
import dev.anmatolay.template.xml.util.SharedPrefHandler
import dev.anmatolay.template.xml.util.logging.CrashlyticsLogTree
import dev.anmatolay.template.xml.util.logging.DiamondDebugTree
import org.koin.android.ext.android.inject
import timber.log.Timber

class TemplateApplication : Application() {

    private val firebaseCrashlytics by inject<FirebaseCrashlytics>()
    private val authenticator by inject<Authenticator>()
    private val sharedPrefHandler by inject<SharedPrefHandler>()

    override fun onCreate() {
        super.onCreate()

        KoinInitializer.init(this)

        val userId = getUserId()

        if (userId == null) {

            authenticator.signInAnonymously()
                .subscribe { getUserId()?.let { setUpLogging(it) } }
                .dispose()
        } else {
            setUpLogging(userId)
        }
    }

    private fun getUserId() = sharedPrefHandler.getString(Authenticator.KEY_USER_ID)

    private fun setUpLogging(userId: String?) {
        Timber.plant(
            if (BuildConfig.DEBUG)
                DiamondDebugTree()
            else
                CrashlyticsLogTree(firebaseCrashlytics, userId)
        )
    }
}

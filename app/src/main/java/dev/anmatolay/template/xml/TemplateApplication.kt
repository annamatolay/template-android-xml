package dev.anmatolay.template.xml

import android.app.Application
import android.os.Build
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dev.anmatolay.template.xml.core.analytic.Analytics
import dev.anmatolay.template.xml.core.authentication.Authenticator
import dev.anmatolay.template.xml.di.KoinInitializer
import dev.anmatolay.template.xml.util.SharedPrefHandler
import dev.anmatolay.template.xml.util.UserProperty
import dev.anmatolay.template.xml.util.logging.CrashlyticsLogTree
import dev.anmatolay.template.xml.util.logging.DiamondDebugTree
import org.koin.android.ext.android.inject
import timber.log.Timber

class TemplateApplication : Application() {

    private val crashlytics by inject<FirebaseCrashlytics>()
    private val analytics by inject<Analytics>()
    private val authenticator by inject<Authenticator>()
    private val sharedPrefHandler by inject<SharedPrefHandler>()

    override fun onCreate() {
        super.onCreate()


        KoinInitializer.init(this)

        val userId = getUserId()

        if (userId == null) {
            setUserProperties()
            authenticator.signInAnonymously()
                .subscribe { getUserId()?.let { setUpLogging(it) } }
                .dispose()
        } else {
            setUpLogging(userId)
        }
    }

    private fun getUserId() = sharedPrefHandler.getString(Authenticator.KEY_USER_ID)

    private fun setUserProperties() {
        analytics.setUserProperty(KEY_APP_VERSION, UserProperty.version)
        analytics.setUserProperty(KEY_API_LEVEL, UserProperty.version)
        analytics.setUserProperty(KEY_ANDROID_VERSION, UserProperty.version)
    }

    private fun setUpLogging(userId: String?) {
        analytics.setUserId(userId)
        Timber.plant(
            if (BuildConfig.DEBUG)
                DiamondDebugTree()
            else
                CrashlyticsLogTree(crashlytics, userId)
        )
    }

    companion object {
        private const val KEY_APP_VERSION ="version_name"
        private const val KEY_API_LEVEL ="api_level"
        private const val KEY_ANDROID_VERSION ="android_version"
    }
}

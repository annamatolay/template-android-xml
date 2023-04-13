package dev.anmatolay.template.xml

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dev.anmatolay.template.xml.core.analytic.Analytics
import dev.anmatolay.template.xml.core.authentication.Authenticator
import dev.anmatolay.template.xml.di.KoinInitializer
import dev.anmatolay.template.xml.domain.model.User
import dev.anmatolay.template.xml.domain.usecase.UserCacheUseCase
import dev.anmatolay.template.xml.util.UserProperty
import dev.anmatolay.template.xml.util.logging.CrashlyticsLogTree
import dev.anmatolay.template.xml.util.logging.DiamondDebugTree
import org.koin.android.ext.android.inject
import timber.log.Timber

class TemplateApplication : Application() {

    private val crashlytics by inject<FirebaseCrashlytics>()
    private val analytics by inject<Analytics>()
    private val authenticator by inject<Authenticator>()
    private val userCacheUseCase by inject<UserCacheUseCase>()

    private var user: User? = null

    init {
        userCacheUseCase.getCachedOrDefaultUser()
            .doOnSuccess { user }
            .subscribe()
            .dispose()
    }

    override fun onCreate() {
        super.onCreate()

        KoinInitializer.init(this)

        setUpAnalyticsAndLogging(user?.id)

        if (user?.id == null) {
            setUserProperties()
            authenticator.signInAnonymously()
                .doOnError { Timber.tag("App init - signInAnonymously").e(it) }
                .subscribe()
                .dispose()
        }
    }

    private fun setUserProperties() {
        analytics.setUserProperty(KEY_APP_VERSION, UserProperty.version)
        analytics.setUserProperty(KEY_API_LEVEL, UserProperty.version)
        analytics.setUserProperty(KEY_ANDROID_VERSION, UserProperty.version)
    }

    private fun setUpAnalyticsAndLogging(userId: String?) {
        analytics.setUserId(userId)
        Timber.plant(
            if (BuildConfig.DEBUG)
                DiamondDebugTree()
            else
                CrashlyticsLogTree(crashlytics, userId)
        )
    }

    companion object {
        private const val KEY_APP_VERSION = "version_name"
        private const val KEY_API_LEVEL = "api_level"
        private const val KEY_ANDROID_VERSION = "android_version"
    }
}

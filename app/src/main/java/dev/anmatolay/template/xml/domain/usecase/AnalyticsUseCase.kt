package dev.anmatolay.template.xml.domain.usecase

import com.google.firebase.crashlytics.FirebaseCrashlytics
import dev.anmatolay.template.xml.BuildConfig
import dev.anmatolay.template.xml.core.analytic.AnalyticsWrapper
import dev.anmatolay.template.xml.util.UserProperty
import dev.anmatolay.template.xml.util.logging.CrashlyticsLogTree
import dev.anmatolay.template.xml.util.logging.DiamondDebugTree
import timber.log.Timber

class MonitoringUseCase(
    private val analyticsWrapper: AnalyticsWrapper,
    private val crashlytics: FirebaseCrashlytics,
) {
    fun setUserProperties() {
        analyticsWrapper.setUserProperty(KEY_APP_VERSION, UserProperty.version)
        analyticsWrapper.setUserProperty(KEY_API_LEVEL, UserProperty.sdkVersion.toString())
        analyticsWrapper.setUserProperty(KEY_ANDROID_VERSION, UserProperty.osVersion)
    }

    fun setUpAnalyticsAndLogging(userId: String?) {
        analyticsWrapper.setUserId(userId)
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
package dev.anmatolay.template.xml.domain.usecase

import com.google.firebase.crashlytics.FirebaseCrashlytics
import dev.anmatolay.template.xml.BuildConfig
import dev.anmatolay.template.xml.core.analytic.AnalyticsWrapper
import dev.anmatolay.template.xml.core.logging.CrashlyticsLogTree
import dev.anmatolay.template.xml.core.logging.DiamondDebugTree
import dev.anmatolay.template.xml.util.UserProperty
import dev.anmatolay.template.xml.util.UserProperty.Companion.KEY_ANDROID_VERSION
import dev.anmatolay.template.xml.util.UserProperty.Companion.KEY_API_LEVEL
import dev.anmatolay.template.xml.util.UserProperty.Companion.KEY_APP_VERSION
import timber.log.Timber

class MonitoringUseCase(
    private val analyticsWrapper: AnalyticsWrapper,
    private val crashlytics: FirebaseCrashlytics,
    private val userProperty: UserProperty,
) {
    fun setUserProperties() {
        analyticsWrapper.setUserProperty(KEY_APP_VERSION, userProperty.version)
        analyticsWrapper.setUserProperty(KEY_ANDROID_VERSION, userProperty.osVersion)
        analyticsWrapper.setUserProperty(KEY_API_LEVEL, userProperty.sdkVersion.toString())
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
}

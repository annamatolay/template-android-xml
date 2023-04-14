package dev.anmatolay.template.xml.core.analytic.impl

import android.os.Bundle
import dev.anmatolay.template.xml.BuildConfig
import dev.anmatolay.template.xml.core.analytic.AnalyticsWrapper
import dev.anmatolay.template.xml.core.logging.CrashlyticsLogTree
import dev.anmatolay.template.xml.core.logging.DiamondDebugTree
import timber.log.Timber

class FakeAnalyticsImpl() : AnalyticsWrapper {

    override fun setUserId(userId: String?) {

    }

    override fun setUserProperty(name: String, value: String) {
        // Do nothing
    }


    override fun logEven(name: String, bundle: Bundle) {
        // Do nothing
    }

}

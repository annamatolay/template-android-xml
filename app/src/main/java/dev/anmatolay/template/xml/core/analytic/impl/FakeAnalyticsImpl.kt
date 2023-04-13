package dev.anmatolay.template.xml.core.analytic.impl

import android.os.Bundle
import dev.anmatolay.template.xml.core.analytic.AnalyticsWrapper

class FakeAnalyticsImpl() : AnalyticsWrapper {

    override fun setUserId(userId: String?) {

    }

    override fun setUserProperty(name: String, value: String) {

    }


    override fun logEven(name: String, bundle: Bundle) {

    }

}

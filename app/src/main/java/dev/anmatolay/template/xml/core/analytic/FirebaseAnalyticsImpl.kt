package dev.anmatolay.template.xml.core.analytic

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseAnalyticsImpl(private val firebaseAnalytics: FirebaseAnalytics) : Analytics {

    override fun setUserId(userId: String?) {
        firebaseAnalytics.setUserId(userId)
    }

    override fun setUserProperty(name: String, value: String) {
        firebaseAnalytics.setUserProperty(name, value)
    }


    override fun logEven(name: String, bundle: Bundle) =
        firebaseAnalytics.logEvent(name, bundle)

}

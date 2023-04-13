package dev.anmatolay.template.xml.core.analytic

import android.os.Bundle

interface Analytics {

  fun setUserId(userId: String?)

  fun setUserProperty(name: String, value: String)

  fun logEven(name: String, bundle: Bundle)
}

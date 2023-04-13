package dev.anmatolay.template.xml.util

import android.os.Build
import dev.anmatolay.template.xml.BuildConfig

object UserProperty {
    const val version = BuildConfig.VERSION_NAME
    val osVersion: String = Build.VERSION.RELEASE
    val sdkVersion = Build.VERSION.SDK_INT
}

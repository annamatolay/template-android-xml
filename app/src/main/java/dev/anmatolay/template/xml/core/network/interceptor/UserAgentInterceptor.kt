package dev.anmatolay.template.xml.core.network.interceptor

import dev.anmatolay.template.xml.BuildConfig
import dev.anmatolay.template.xml.util.UserProperty.osVersion
import dev.anmatolay.template.xml.util.UserProperty.sdkVersion
import dev.anmatolay.template.xml.util.UserProperty.version
import okhttp3.Interceptor

val userAgentInterceptor = Interceptor { chain ->

    val name = BuildConfig.APPLICATION_ID.split(".").last()

    val request = chain.request().newBuilder()
        .header("User-Agent", "$name v$version - Android $osVersion (API $sdkVersion)")
        .build()

    chain.proceed(request)
}
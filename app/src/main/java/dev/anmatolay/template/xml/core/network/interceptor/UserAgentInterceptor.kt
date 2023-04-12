package dev.anmatolay.template.xml.core.network.interceptor

import android.os.Build
import dev.anmatolay.template.xml.BuildConfig
import okhttp3.Interceptor

val userAgentInterceptor = Interceptor { chain ->

    val name = BuildConfig.APPLICATION_ID.split(".").last()
    val version = BuildConfig.VERSION_NAME
    val osVersion = Build.VERSION.RELEASE
    val sdkVersion = Build.VERSION.SDK_INT

    val request = chain.request().newBuilder()
        .header("User-Agent", "$name v$version - Android $osVersion (API $sdkVersion)")
        .build()

    chain.proceed(request)
}
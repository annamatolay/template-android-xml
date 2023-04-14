package dev.anmatolay.template.xml.core.network.interceptor

import dev.anmatolay.template.xml.BuildConfig
import dev.anmatolay.template.xml.util.UserProperty
import okhttp3.Interceptor
import org.koin.java.KoinJavaComponent.inject

val userAgentInterceptor = Interceptor { chain ->

    val userProperty by inject<UserProperty>(UserProperty::class.java)

    val name = BuildConfig.APPLICATION_ID.split(".").last()

    val request = chain.request().newBuilder()
        .header(
            "User-Agent",
            "$name v${userProperty.version} " +
                    "- Android ${userProperty.osVersion} (API ${userProperty.sdkVersion})",
        )
        .build()

    chain.proceed(request)
}
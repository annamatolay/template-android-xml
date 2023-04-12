package dev.anmatolay.template.xml.core.network.interceptor

import okhttp3.Interceptor

val interceptorList: List<Interceptor> = listOf(
    userAgentInterceptor,
)
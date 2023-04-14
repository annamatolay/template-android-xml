package dev.anmatolay.template.xml.di

import dev.anmatolay.template.xml.core.analytic.AnalyticsWrapper
import dev.anmatolay.template.xml.core.analytic.impl.FirebaseAnalyticsImpl
import dev.anmatolay.template.xml.core.authentication.Authenticator
import dev.anmatolay.template.xml.core.authentication.impl.FirebaseAuthenticatorImpl
import dev.anmatolay.template.xml.core.network.ApiClientFactory
import dev.anmatolay.template.xml.core.network.MoshiFactory
import dev.anmatolay.template.xml.core.threading.SchedulerProvider
import dev.anmatolay.template.xml.core.threading.impl.SchedulerProviderImpl
import dev.anmatolay.template.xml.core.SharedPrefHandler
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    factory<Authenticator> { FirebaseAuthenticatorImpl(get()) }
    factory<AnalyticsWrapper> { FirebaseAnalyticsImpl(get()) }
    factory<SchedulerProvider> { SchedulerProviderImpl() }
    single { MoshiFactory.create() }
    single { ApiClientFactory.createRetrofit() }
    factory { SharedPrefHandler(androidContext()) }
}

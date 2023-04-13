package dev.anmatolay.template.xml.di

import dev.anmatolay.template.xml.core.analytic.Analytics
import dev.anmatolay.template.xml.core.analytic.FirebaseAnalyticsImpl
import dev.anmatolay.template.xml.core.authentication.Authenticator
import dev.anmatolay.template.xml.core.authentication.impl.AuthenticatorImpl
import dev.anmatolay.template.xml.core.network.ApiClientFactory
import dev.anmatolay.template.xml.core.network.MoshiFactory
import dev.anmatolay.template.xml.core.threading.SchedulerProvider
import dev.anmatolay.template.xml.core.threading.impl.SchedulerProviderImpl
import dev.anmatolay.template.xml.util.SharedPrefHandler
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    factory<Authenticator> { AuthenticatorImpl(get(), get()) }
    factory<Analytics> { FirebaseAnalyticsImpl(get()) }
    factory<SchedulerProvider> { SchedulerProviderImpl() }
    single { MoshiFactory.create() }
    single { ApiClientFactory.createRetrofit() }
    factory { SharedPrefHandler(androidContext()) }
}

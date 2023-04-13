package dev.anmatolay.template.xml.di

import dev.anmatolay.template.xml.core.authentication.Authenticator
import dev.anmatolay.template.xml.core.authentication.impl.AuthenticatorImpl
import dev.anmatolay.template.xml.core.network.ApiClientFactory
import dev.anmatolay.template.xml.core.network.MoshiFactory
import dev.anmatolay.template.xml.core.threading.SchedulerProvider
import dev.anmatolay.template.xml.core.threading.impl.SchedulerProviderImpl
import org.koin.dsl.module

val appModule = module {
    factory<SchedulerProvider> { SchedulerProviderImpl() }
    single { MoshiFactory.create() }
    single { ApiClientFactory.createRetrofit() }
    factory<Authenticator> { AuthenticatorImpl(get()) }
}

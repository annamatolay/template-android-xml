package dev.anmatolay.template.xml.di

import dev.anmatolay.template.xml.core.threading.SchedulerProvider
import dev.anmatolay.template.xml.core.threading.impl.SchedulerProviderImpl
import org.koin.dsl.module

val appModule = module {
    factory<SchedulerProvider> { SchedulerProviderImpl() }
}

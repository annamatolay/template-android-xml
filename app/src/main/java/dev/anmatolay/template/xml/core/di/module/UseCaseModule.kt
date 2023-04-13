package dev.anmatolay.template.xml.core.di.module

import dev.anmatolay.template.xml.domain.usecase.MonitoringUseCase
import dev.anmatolay.template.xml.domain.usecase.UserCacheUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { UserCacheUseCase(get()) }
    factory { MonitoringUseCase(get(), get()) }
}

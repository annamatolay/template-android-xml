package dev.anmatolay.template.xml.core.di.module

import dev.anmatolay.template.xml.domain.usecase.CacheUserIdUseCase
import dev.anmatolay.template.xml.domain.usecase.GetUserUseCase
import dev.anmatolay.template.xml.domain.usecase.MonitoringUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetUserUseCase(get()) }
    factory { CacheUserIdUseCase(get()) }
    factory { MonitoringUseCase(get(), get(), get()) }
}

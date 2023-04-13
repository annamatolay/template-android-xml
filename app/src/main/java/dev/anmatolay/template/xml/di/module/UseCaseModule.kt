package dev.anmatolay.template.xml.di.module

import dev.anmatolay.template.xml.domain.usecase.UserCacheUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { UserCacheUseCase(get()) }
}

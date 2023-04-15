package dev.anmatolay.template.xml.core.di.module

import dev.anmatolay.template.xml.data.repository.UserCacheRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { UserCacheRepository(get()) }
}

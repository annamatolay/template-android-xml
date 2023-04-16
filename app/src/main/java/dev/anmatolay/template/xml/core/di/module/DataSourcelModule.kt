package dev.anmatolay.template.xml.core.di.module

import dev.anmatolay.template.xml.data.local.UserIdDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    factory { UserIdDataSource(get()) }
}

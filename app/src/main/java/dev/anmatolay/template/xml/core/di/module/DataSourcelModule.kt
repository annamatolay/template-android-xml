package dev.anmatolay.template.xml.core.di.module

import dev.anmatolay.template.xml.data.local.UserIdDataSource
import dev.anmatolay.template.xml.presentation.MainActivityViewModel
import dev.anmatolay.template.xml.presentation.home.HomeViewModel
import dev.anmatolay.template.xml.presentation.splash.SplashViewModel
import org.koin.dsl.module

val dataSourceModule = module {
    factory { UserIdDataSource(get()) }
}

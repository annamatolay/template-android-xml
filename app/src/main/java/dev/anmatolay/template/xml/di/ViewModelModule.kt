package dev.anmatolay.template.xml.di

import dev.anmatolay.template.xml.presentation.splash.SplashViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { SplashViewModel(get()) }
}

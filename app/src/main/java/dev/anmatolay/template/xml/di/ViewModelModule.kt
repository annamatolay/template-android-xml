package dev.anmatolay.template.xml.di

import dev.anmatolay.template.xml.presentation.MainActivityViewModel
import dev.anmatolay.template.xml.presentation.home.HomeViewModel
import dev.anmatolay.template.xml.presentation.splash.SplashViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { MainActivityViewModel() }
    factory { SplashViewModel(get()) }
    factory { HomeViewModel() }
}

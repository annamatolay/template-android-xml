package dev.anmatolay.template.xml.presentation.splash

import dev.anmatolay.template.xml.core.NavigationEvent

sealed class SplashNavigationEvent {
    object Home : NavigationEvent
}

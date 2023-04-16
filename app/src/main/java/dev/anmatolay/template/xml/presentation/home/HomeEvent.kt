package dev.anmatolay.template.xml.presentation.home

import dev.anmatolay.template.xml.core.presentation.UiEvent

sealed class HomeEvent: UiEvent {
    object ScreenOnClicked: HomeEvent()
}

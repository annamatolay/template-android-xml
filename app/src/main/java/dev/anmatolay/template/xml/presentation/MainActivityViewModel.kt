package dev.anmatolay.template.xml.presentation

import dev.anmatolay.template.xml.core.presentation.BaseUdfViewModel
import dev.anmatolay.template.xml.core.presentation.UiEvent
import dev.anmatolay.template.xml.core.presentation.UiState

class MainActivityViewModel : BaseUdfViewModel<State, Event>()

object Event: UiEvent
object State: UiState

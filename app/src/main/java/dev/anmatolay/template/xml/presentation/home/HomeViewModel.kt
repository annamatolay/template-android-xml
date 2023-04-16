package dev.anmatolay.template.xml.presentation.home

import dev.anmatolay.template.xml.core.presentation.BaseUdfViewModel

class HomeViewModel : BaseUdfViewModel<HomeState, HomeEvent>() {

    override fun onViewResumed() {
        super.onViewResumed()

        doOnUiEventReceived { uiEvent ->
            when (uiEvent) {
                HomeEvent.ScreenOnClicked -> {
                    val isTextVisible = uiState.value?.isTextVisible ?: false
                    triggerUiStateChange(HomeState(!isTextVisible))
                }
            }
        }.subscribe().disposeOnPause()
    }
}

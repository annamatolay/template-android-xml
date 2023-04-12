package dev.anmatolay.template.xml.presentation.splash

import dev.anmatolay.template.xml.core.presentation.BaseViewModel
import dev.anmatolay.template.xml.core.threading.SchedulerProvider
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class SplashViewModel(
    private val schedulerProvider: SchedulerProvider,
    ) : BaseViewModel() {

    override fun onViewCreated() {
        super.onViewCreated()

        navigateToHomeAfterThreeSecond()
    }

    private fun navigateToHomeAfterThreeSecond() {
        Observable.timer(3L, TimeUnit.SECONDS)
            .observeOn(schedulerProvider.mainThread())
            .subscribe { navigateTo(SplashNavigationEvent.Home) }
            .disposeOnDestroy()
    }
}

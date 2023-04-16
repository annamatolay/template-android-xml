package dev.anmatolay.template.xml.presentation.splash

import dev.anmatolay.template.xml.core.presentation.BaseUdfViewModel
import dev.anmatolay.template.xml.core.threading.SchedulerProvider
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class SplashViewModel(
    private val schedulerProvider: SchedulerProvider,
) : BaseUdfViewModel<SplashState, SplashEvent>() {

    override fun onViewCreated() {
        super.onViewCreated()

        navigateToHomeAfterThreeSecond()
    }

    private fun navigateToHomeAfterThreeSecond() {
        Observable.timer(3L, TimeUnit.SECONDS)
            .observeOn(schedulerProvider.mainThread())
            .subscribe { triggerUiStateChange(SplashState(isIdle = false)) }
            .disposeOnDestroy()
    }
}

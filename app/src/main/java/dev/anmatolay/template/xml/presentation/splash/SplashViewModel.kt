package dev.anmatolay.template.xml.presentation.splash

import dev.anmatolay.template.xml.core.presentation.BaseViewModel
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class SplashViewModel : BaseViewModel() {

    override fun onViewCreated() {
        super.onViewCreated()

        Observable.timer(3L, TimeUnit.SECONDS)
            .subscribe { navigateTo(SplashNavigationEvent.Home) }
            .disposeOnDestroy()
    }
}

package dev.anmatolay.template.xml.core.presentation

import androidx.annotation.CallSuper
import com.jakewharton.rxrelay3.PublishRelay
import dev.anmatolay.template.xml.core.NavigationEvent
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class BaseViewModel {

    private val navigator = PublishRelay.create<NavigationEvent>()
    val navigationEvents: Observable<NavigationEvent>
        get() = navigator

    private var aliveDisposables = CompositeDisposable()
    private var foregroundDisposables = CompositeDisposable()

    fun navigateTo(event: NavigationEvent) {
        navigator.accept(event)
    }

    protected fun Disposable.disposeOnPause() = foregroundDisposables.add(this)

    protected fun Disposable.disposeOnDestroy() = aliveDisposables.add(this)

    @CallSuper
    open fun onViewCreated() {
        aliveDisposables = CompositeDisposable()
    }

    @CallSuper
    open fun onViewResumed() {
        foregroundDisposables = CompositeDisposable()
    }

    @CallSuper
    open fun onViewPaused() {
        foregroundDisposables.clear()
    }

    @CallSuper
    open fun onDestroyView() {
        aliveDisposables.clear()
    }
}

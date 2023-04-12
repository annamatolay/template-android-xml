package dev.anmatolay.template.xml.core.presentation

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val viewModel: BaseViewModel
    private var aliveDisposables = CompositeDisposable()
    private var foregroundDisposables = CompositeDisposable()

    protected fun Disposable.disposeOnPause() = foregroundDisposables.add(this)

    protected fun Disposable.disposeOnDestroy() = aliveDisposables.add(this)

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aliveDisposables = CompositeDisposable()
        viewModel.onViewCreated()
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        foregroundDisposables = CompositeDisposable()
        viewModel.onViewResumed()
    }

    @CallSuper
    override fun onPause() {
        foregroundDisposables.clear()
        viewModel.onViewPaused()
        super.onPause()
    }

    @CallSuper
    override fun onDestroy() {
        aliveDisposables.clear()
        viewModel.onDestroyView()
        super.onDestroy()
    }
}

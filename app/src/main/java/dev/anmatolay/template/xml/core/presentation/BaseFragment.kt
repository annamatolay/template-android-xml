package dev.anmatolay.template.xml.core.presentation

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseFragment : Fragment() {

    protected abstract val viewModel: BaseViewModel
    private var aliveDisposables = CompositeDisposable()
    private var foregroundDisposables = CompositeDisposable()

    protected fun Disposable.disposeOnPause() = foregroundDisposables.add(this)

    protected fun Disposable.disposeOnDestroy() = aliveDisposables.add(this)

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
    override fun onDestroyView() {
        aliveDisposables.clear()
        viewModel.onDestroyView()
        super.onDestroyView()
    }
}

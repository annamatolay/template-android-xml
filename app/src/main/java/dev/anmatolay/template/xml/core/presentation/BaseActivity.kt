package dev.anmatolay.template.xml.core.presentation

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val viewModel: BaseUdfViewModel<out UiState, out UiEvent>

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onViewCreated()
        Timber.d("${this.javaClass.simpleName} - onCreate")
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        viewModel.onViewResumed()
        Timber.d("${this.javaClass.simpleName} - onResume")
    }

    @CallSuper
    override fun onPause() {
        viewModel.onViewPaused()
        super.onPause()
        Timber.d("${this.javaClass.simpleName} - onPause")
    }

    @CallSuper
    override fun onDestroy() {
        viewModel.onDestroyView()
        super.onDestroy()
        Timber.d("${this.javaClass.simpleName} - onDestroy")
    }

    protected fun <T> LiveData<T>.observe(observer: (T) -> Unit) {
        observe(this@BaseActivity, observer)
    }
}

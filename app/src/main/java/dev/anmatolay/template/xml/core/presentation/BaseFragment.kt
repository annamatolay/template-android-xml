package dev.anmatolay.template.xml.core.presentation

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import dev.anmatolay.template.xml.core.NavigationEvent
import timber.log.Timber

abstract class BaseFragment : Fragment() {

    protected abstract val viewModel: BaseViewModel

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated()
        Timber.d("${this.javaClass.simpleName} - onViewCreated")
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
    override fun onDestroyView() {
        viewModel.onDestroyView()
        super.onDestroyView()
        Timber.d("${this.javaClass.simpleName} - onDestroyView")
    }

    protected fun onNavigationEventReceived(function: (NavigationEvent) -> Unit) {
        viewModel.navigationEvents.observe(viewLifecycleOwner) { event ->
            function(event)
        }
    }
}

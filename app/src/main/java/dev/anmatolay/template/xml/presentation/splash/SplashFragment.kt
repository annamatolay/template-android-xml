package dev.anmatolay.template.xml.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import dev.anmatolay.template.xml.core.presentation.BaseFragment
import dev.anmatolay.template.xml.databinding.FragmentSplashBinding
import dev.anmatolay.template.xml.util.extension.navigateTo
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment() {
    override val viewModel by viewModel<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentSplashBinding.inflate(inflater, container, false)
            .apply { binding = this }
            .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getAppActionBar()?.hide()

        onNavigationEventReceived { event ->
            when (event) {
                SplashNavigationEvent.Home ->
                    navigateTo(SplashFragmentDirections.actionToHomeFragment())
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        getAppActionBar()?.show()
    }

    private fun getAppActionBar() = (activity as? AppCompatActivity)?.supportActionBar
}

package dev.anmatolay.template.xml.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.google.android.material.appbar.AppBarLayout
import dev.anmatolay.template.xml.R
import dev.anmatolay.template.xml.core.presentation.BaseFragment
import dev.anmatolay.template.xml.databinding.FragmentSplashBinding
import dev.anmatolay.template.xml.util.extension.navigateTo
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment() {

    override val viewModel by viewModel<SplashViewModel>()
    private lateinit var binding: FragmentSplashBinding

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

        setAppBarLayoutVisibility(INVISIBLE)

        viewModel.uiState.observe { state ->
            if (!state.isIdle) {
                navigateTo(SplashFragmentDirections.actionToHomeFragment())
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        setAppBarLayoutVisibility(VISIBLE)
    }

    private fun setAppBarLayoutVisibility(visibility: Int) {
        requireActivity().findViewById<AppBarLayout>(R.id.appBar).visibility = visibility
    }
}

package dev.anmatolay.template.xml.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import dev.anmatolay.template.xml.core.presentation.BaseFragment
import dev.anmatolay.template.xml.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    override val viewModel by viewModel<HomeViewModel>()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentHomeBinding.inflate(inflater, container, false)
            .apply { binding = this }
            .root

    override fun onResume() {
        super.onResume()

        viewModel.uiState.observe { state ->
            binding.text.isVisible = state.isTextVisible
        }

        binding.layout.setOnClickListener {
            viewModel.triggerEvent(HomeEvent.ScreenOnClicked)
        }
    }
}

package dev.anmatolay.template.xml.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.anmatolay.template.xml.R
import dev.anmatolay.template.xml.core.presentation.BaseFragment

class HomeFragment : BaseFragment() {
    override val viewModel = HomeViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)
}

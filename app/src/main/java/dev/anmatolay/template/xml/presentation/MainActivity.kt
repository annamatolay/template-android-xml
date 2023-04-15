package dev.anmatolay.template.xml.presentation

import android.os.Bundle
import dev.anmatolay.template.xml.core.presentation.BaseActivity
import dev.anmatolay.template.xml.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {
    override val viewModel by inject<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

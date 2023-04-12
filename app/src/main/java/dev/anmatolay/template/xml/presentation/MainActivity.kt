package dev.anmatolay.template.xml.presentation

import android.os.Bundle
import dev.anmatolay.template.xml.R
import dev.anmatolay.template.xml.core.presentation.BaseActivity

class MainActivity : BaseActivity() {
    override val viewModel = MainActivityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

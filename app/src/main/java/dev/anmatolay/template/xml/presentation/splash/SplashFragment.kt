package dev.anmatolay.template.xml.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.anmatolay.template.xml.R
import dev.anmatolay.template.xml.core.presentation.BaseFragment
import dev.anmatolay.template.xml.util.navigateTo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class SplashFragment : BaseFragment() {
    override val viewModel = SplashViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_splash, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigationEvents
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { event ->
                when (event) {
                    SplashNavigationEvent.Home ->
                        navigateTo(SplashFragmentDirections.actionToHomeFragment())
                }
            }
            .disposeOnPause()
    }
}

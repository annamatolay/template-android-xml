package dev.anmatolay.template.xml.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import dev.anmatolay.template.xml.R
import dev.anmatolay.template.xml.core.presentation.BaseFragment
import dev.anmatolay.template.xml.util.extension.navigateTo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import org.koin.android.ext.android.inject

class SplashFragment : BaseFragment() {
    override val viewModel by inject<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_splash, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getAppActionBar()?.hide()

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

    override fun onDetach() {
        super.onDetach()
        getAppActionBar()?.show()
    }

    private fun getAppActionBar() = (activity as? AppCompatActivity)?.supportActionBar
}

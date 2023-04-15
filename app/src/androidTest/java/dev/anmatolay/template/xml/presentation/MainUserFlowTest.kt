package dev.anmatolay.template.xml.presentation

import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.anmatolay.template.xml.R
import dev.anmatolay.template.xml.core.InstrumentedTest
import dev.anmatolay.template.xml.presentation.home.HomeScreen
import dev.anmatolay.template.xml.presentation.splash.SplashScreen
import io.github.kakaocup.kakao.screen.Screen.Companion.onScreen
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainUserFlowTest : InstrumentedTest() {

    @Test
    fun userOpenApp_seeSlashAndHomeScreen_textsAreVisible() {
        activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        onScreen<SplashScreen> {
            title.run {
                hasText(R.string.app_name)
                isVisible()
            }
        }
        // Waiting for splash to navigate
        Thread.sleep(3000)
        onScreen<HomeScreen> {
            text.run {
                hasText(R.string.welcome)
                isVisible()
            }
        }
    }
}

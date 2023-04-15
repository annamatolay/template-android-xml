package dev.anmatolay.template.xml.presentation.splash

import dev.anmatolay.template.xml.R
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KTextView

class SplashScreen: Screen<SplashScreen>() {
    val title = KTextView { withId(R.id.appTitle) }
}
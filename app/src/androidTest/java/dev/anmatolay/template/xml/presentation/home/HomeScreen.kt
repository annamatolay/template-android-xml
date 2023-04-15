package dev.anmatolay.template.xml.presentation.home

import dev.anmatolay.template.xml.R
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KTextView

class HomeScreen: Screen<HomeScreen>() {
    val text = KTextView { withId(R.id.text) }
}
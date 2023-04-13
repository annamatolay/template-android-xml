package dev.anmatolay.template.xml.util.extension

import io.reactivex.rxjava3.core.Maybe


fun < T : Any> T?.toMaybe(): Maybe<T> {
    return if (this == null)
        Maybe.empty()
    else
        Maybe.just(this)
}
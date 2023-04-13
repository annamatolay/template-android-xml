package dev.anmatolay.template.xml.core.authentication

import io.reactivex.rxjava3.core.Completable
import timber.log.Timber
import kotlin.properties.Delegates

abstract class Authenticator {

    protected var currentUser:
            UserProvider? by Delegates.observable(null) { prop, old, new ->
        Timber.d("currentUser changed: $prop old: $old new $new")
    }
    abstract fun signInAnonymously(): Completable
}

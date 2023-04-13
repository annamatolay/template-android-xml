package dev.anmatolay.template.xml.core.authentication

import dev.anmatolay.template.xml.util.SharedPrefHandler
import io.reactivex.rxjava3.core.Completable
import kotlin.properties.Delegates

abstract class Authenticator(private val sharedPrefHandler: SharedPrefHandler) {

    protected var userProvider:
            UserProvider? by Delegates.observable(null) { _, _, userProvider ->
        userProvider.getUserId()?.let { sharedPrefHandler.setString(KEY_USER_ID, it) }
    }

    private fun UserProvider?.getUserId() =
        when (this) {
            is UserProvider.FakeUser -> this.userId
            is UserProvider.FirebaseUser -> this.user.uid
            null -> null
        }

    abstract fun signInAnonymously(): Completable

    companion object {
        const val KEY_USER_ID = "key_user_id"
    }
}

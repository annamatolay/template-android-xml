package dev.anmatolay.template.xml.core.authentication.impl

import dev.anmatolay.template.xml.core.authentication.Authenticator
import dev.anmatolay.template.xml.core.authentication.UnknownAuthErrorException
import dev.anmatolay.template.xml.core.authentication.UserProvider
import dev.anmatolay.template.xml.util.SharedPrefHandler
import io.reactivex.rxjava3.core.Completable

class FakeAuthenticatorImpl(
    sharedPrefHandler: SharedPrefHandler
) : Authenticator(sharedPrefHandler) {

    var isSuccessful: Boolean = false
    var userId = ""

    override fun signInAnonymously() = Completable.create { emitter ->
        if (isSuccessful) {
            userProvider = UserProvider.FakeUser(userId)
            emitter.onComplete()
        } else {
            emitter.onError(
                UnknownAuthErrorException(
                    isTaskSuccessful = false,
                    isCurrentUserNull = false,
                )
            )
        }
    }
}

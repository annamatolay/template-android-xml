package dev.anmatolay.template.xml.core.authentication.impl

import dev.anmatolay.template.xml.core.authentication.Authenticator
import dev.anmatolay.template.xml.core.authentication.UnknownAuthErrorException
import dev.anmatolay.template.xml.core.authentication.UserProvider
import dev.anmatolay.template.xml.domain.model.User
import io.reactivex.rxjava3.core.Completable

class FakeAuthenticatorImpl : Authenticator() {

    var isSuccessful: Boolean = false
    var userId = ""

    override fun signInAnonymously() = Completable.create { emitter ->
        if (isSuccessful) {
            currentUser = UserProvider.FakeUser(userId)
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

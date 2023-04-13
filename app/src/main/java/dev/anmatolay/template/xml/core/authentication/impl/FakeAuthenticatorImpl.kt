package dev.anmatolay.template.xml.core.authentication.impl

import dev.anmatolay.template.xml.core.authentication.AuthenticationResult
import dev.anmatolay.template.xml.core.authentication.Authenticator
import dev.anmatolay.template.xml.domain.model.User

class FakeAuthenticatorImpl : Authenticator {

    var isSuccessful: Boolean = false
    lateinit var user: User

    override fun signInAnonymously(): AuthenticationResult {
        return FakeAuthResultImpl(isSuccessful)
    }

    class FakeAuthResultImpl(private val isSuccessful: Boolean) :
        AuthenticationResult.FakeAuthResult() {
        override fun onComplete() {
            if (isSuccessful) {

            }
        }
    }
}
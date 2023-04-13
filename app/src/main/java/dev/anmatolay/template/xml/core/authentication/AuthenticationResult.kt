package dev.anmatolay.template.xml.core.authentication

sealed interface AuthenticationResult {
    abstract class FirebaseAuthResult : AuthenticationResult

    abstract class FakeAuthResult : AuthenticationResult

    fun onComplete()
}
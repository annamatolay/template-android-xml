package dev.anmatolay.template.xml.core.authentication

interface Authenticator {

    fun signInAnonymously(): AuthenticationResult
}
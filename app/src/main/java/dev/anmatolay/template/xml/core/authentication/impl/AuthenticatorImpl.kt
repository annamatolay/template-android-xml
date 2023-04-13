package dev.anmatolay.template.xml.core.authentication.impl

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dev.anmatolay.template.xml.core.authentication.AuthenticationResult
import dev.anmatolay.template.xml.core.authentication.Authenticator
import dev.anmatolay.template.xml.domain.model.User
import timber.log.Timber

class AuthenticatorImpl(private val firebaseAuth: FirebaseAuth) : Authenticator {
    override fun signInAnonymously(): AuthenticationResult {
        return FirebaseAuthResultImpl(firebaseAuth.signInAnonymously(), firebaseAuth)
    }

    class FirebaseAuthResultImpl(
        private val task: Task<AuthResult>,
        private val firebaseAuth: FirebaseAuth,
    ) : AuthenticationResult.FirebaseAuthResult() {
        override fun onComplete() {
            task.addOnCompleteListener { task ->
                val currentUser = firebaseAuth.currentUser
                if (task.isSuccessful && currentUser != null) {
                    User(currentUser.uid)
                } else {
                    Timber.tag("FirebaseAuth").e(task.exception)
                }
            }
        }
    }
}
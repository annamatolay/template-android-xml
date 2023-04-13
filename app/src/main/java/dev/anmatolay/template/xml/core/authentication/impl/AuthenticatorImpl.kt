package dev.anmatolay.template.xml.core.authentication.impl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dev.anmatolay.template.xml.core.authentication.Authenticator
import dev.anmatolay.template.xml.core.authentication.UnknownAuthErrorException
import dev.anmatolay.template.xml.core.authentication.UserProvider
import dev.anmatolay.template.xml.util.SharedPrefHandler
import io.reactivex.rxjava3.core.Completable
import timber.log.Timber

class AuthenticatorImpl(
    private val firebaseAuth: FirebaseAuth,
    sharedPrefHandler: SharedPrefHandler,
) : Authenticator(sharedPrefHandler) {

    override fun signInAnonymously() = Completable.create { emitter ->
        firebaseAuth.signInAnonymously()
            .addOnCompleteListener { task ->
                val currentUser = firebaseAuth.currentUser
                if (task.isSuccessful && currentUser != null) {
                    this.currentUser = UserProvider.FirebaseUser(currentUser)
                    Timber.d("User logged in")
                    emitter.onComplete()
                } else {
                    val exception = task.exception
                    Timber.tag("FirebaseAuth").e(exception)
                    emitter.onError(
                        exception ?: UnknownAuthErrorException(
                            task.isSuccessful,
                            currentUser.isNull(),
                        )
                    )
                }
            }
    }
}

private fun FirebaseUser?.isNull(): Boolean = this == null

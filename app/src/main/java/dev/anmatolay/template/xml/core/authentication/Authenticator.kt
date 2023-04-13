package dev.anmatolay.template.xml.core.authentication

import dev.anmatolay.template.xml.domain.usecase.UserCacheUseCase
import io.reactivex.rxjava3.core.Completable
import org.koin.java.KoinJavaComponent.inject
import kotlin.properties.Delegates

abstract class Authenticator {

    private val useCase by inject<UserCacheUseCase>(UserCacheUseCase::class.java)

    protected var userProvider:
            UserProvider? by Delegates.observable(null) { _, _, userProvider ->
        userProvider.getUserId()?.let { useCase.cacheUserId(it) }
    }

    abstract fun signInAnonymously(): Completable

    private fun UserProvider?.getUserId() =
        when (this) {
            is UserProvider.FakeUser -> this.userId
            is UserProvider.FirebaseUser -> this.user.uid
            null -> null
        }
}

package dev.anmatolay.template.xml.core.authentication

import dev.anmatolay.template.xml.domain.usecase.MonitoringUseCase
import dev.anmatolay.template.xml.domain.usecase.UserCacheUseCase
import io.reactivex.rxjava3.core.Completable
import org.koin.java.KoinJavaComponent.inject
import kotlin.properties.Delegates

abstract class Authenticator {

    private val userCacheUseCase by inject<UserCacheUseCase>(UserCacheUseCase::class.java)
    private val monitoringUseCase by inject<MonitoringUseCase>(MonitoringUseCase::class.java)

    protected var userProvider:
            UserProvider? by Delegates.observable(null) { _, _, userProvider ->
        userProvider.getUserId()?.let { userId ->
            userCacheUseCase.cacheUserId(userId)
            monitoringUseCase.setUpAnalyticsAndLogging(userId)
        }
    }

    abstract fun signInAnonymously(): Completable

    private fun UserProvider?.getUserId() =
        when (this) {
            is UserProvider.FakeUser -> this.userId
            is UserProvider.FirebaseUser -> this.user.uid
            null -> null
        }
}

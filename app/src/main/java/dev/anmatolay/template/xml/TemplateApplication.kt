package dev.anmatolay.template.xml

import android.app.Application
import dev.anmatolay.template.xml.core.authentication.Authenticator
import dev.anmatolay.template.xml.core.di.KoinInitializer
import dev.anmatolay.template.xml.data.local.GetUserUseCase
import dev.anmatolay.template.xml.domain.model.User
import dev.anmatolay.template.xml.domain.usecase.MonitoringUseCase
import org.koin.android.ext.android.inject
import timber.log.Timber

class TemplateApplication : Application() {

    private val authenticator by inject<Authenticator>()
    private val getUserUseCase by inject<GetUserUseCase>()
    private val monitoringUseCase by inject<MonitoringUseCase>()

    private var user: User? = null

    override fun onCreate() {
        super.onCreate()

        KoinInitializer.init(this)

        getUserUseCase()
            .doOnSuccess { user = it }
            .subscribe()
            .dispose()

        monitoringUseCase.setUpAnalyticsAndLogging(user?.id)

        if (user?.id == null) {
            monitoringUseCase.setUserProperties()
            authenticator.signInAnonymously()
                .doOnError { Timber.tag("App init - signInAnonymously").e(it) }
                .subscribe()
                .dispose()
        }
    }
}

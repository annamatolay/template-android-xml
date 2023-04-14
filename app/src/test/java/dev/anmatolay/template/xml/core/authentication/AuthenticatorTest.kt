package dev.anmatolay.template.xml.core.authentication

import dev.anmatolay.template.xml.BaseTest
import dev.anmatolay.template.xml.domain.usecase.MonitoringUseCase
import dev.anmatolay.template.xml.domain.usecase.UserCacheUseCase
import dev.anmatolay.template.xml.util.TestContains.TEST_USER_ID
import io.mockk.*
import io.reactivex.rxjava3.core.Completable
import org.junit.Before
import org.junit.Test

class AuthenticatorTest : BaseTest() {

    private val userCacheUseCase = mockk<UserCacheUseCase>()
    private val monitoringUseCase = mockk<MonitoringUseCase>()

    @Before
    override fun setUp() {
        startTestKoin {
            factory { userCacheUseCase }
            factory { monitoringUseCase }
        }
    }

    @Test
    fun `When userProvider changed Then cacheUserId and setUpAnalyticsAndLogging`() {
        every { userCacheUseCase.cacheUserId(TEST_USER_ID) } returns Completable.complete()
        justRun { monitoringUseCase.setUpAnalyticsAndLogging(TEST_USER_ID) }
        fakeAuthenticator.userId = TEST_USER_ID
        fakeAuthenticator.isSuccessful = true

        // Then
        val result = fakeAuthenticator.signInAnonymously().test()

        // When
        result.run {
            assertNoErrors()
            assertComplete()
        }
        verifyAll {
            userCacheUseCase.cacheUserId(TEST_USER_ID)
            monitoringUseCase.setUpAnalyticsAndLogging(TEST_USER_ID)
        }
    }
}
}
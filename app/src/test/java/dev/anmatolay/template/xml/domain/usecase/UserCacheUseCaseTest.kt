package dev.anmatolay.template.xml.domain.usecase

import dev.anmatolay.template.xml.BaseTest
import dev.anmatolay.template.xml.data.local.UserIdDataSource
import dev.anmatolay.template.xml.domain.model.User
import dev.anmatolay.template.xml.util.TestContains.TEST_USER_ID
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.reactivex.rxjava3.core.Maybe
import org.junit.Test

class UserCacheUseCaseTest : BaseTest() {

    private val dataSource = mockk<UserIdDataSource>()
    private val useCase = UserCacheUseCase(dataSource)

    @Test
    fun `Given user id cached When getCachedOrDefaultUser Then return with User`() {
        // Given
        every { dataSource.getUserId() } returns Maybe.just(TEST_USER_ID)

        // Then
        val result = useCase.getCachedOrDefaultUser().test()

        // When
        result.run {
            assertNoErrors()
            assertValue(User(TEST_USER_ID))
            assertComplete()
        }
    }

    @Test
    fun `Given user id NOT cached When getCachedOrDefaultUser Then return with default User`() {
        // Given
        every { dataSource.getUserId() } returns Maybe.empty()

        // Then
        val result = useCase.getCachedOrDefaultUser().test()

        // When
        result.run {
            assertNoErrors()
            assertValue(User("null"))
            assertComplete()
        }
    }

    @Test
    fun `When cacheUserId called Then update User`() {
        justRun { dataSource.putUserId(TEST_USER_ID) }

        // Then
        val result = useCase.cacheUserId(TEST_USER_ID).test()

        // When
        result.run {
            assertNoErrors()
            assertComplete()
        }
    }
}

package dev.anmatolay.template.xml.domain.usecase

import dev.anmatolay.template.xml.data.repository.UserCacheRepository

class GetUserUseCase(private val repository: UserCacheRepository) {

    operator fun invoke() = repository.getCachedOrDefaultUser()
}

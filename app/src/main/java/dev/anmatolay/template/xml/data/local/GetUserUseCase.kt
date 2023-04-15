package dev.anmatolay.template.xml.data.local

import dev.anmatolay.template.xml.data.repository.UserCacheRepository

class GetUserUseCase(private val repository: UserCacheRepository) {

    operator fun invoke() = repository.getCachedOrDefaultUser()
}

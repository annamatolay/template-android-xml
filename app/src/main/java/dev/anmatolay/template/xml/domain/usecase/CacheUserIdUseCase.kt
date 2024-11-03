package dev.anmatolay.template.xml.domain.usecase

import dev.anmatolay.template.xml.data.repository.UserCacheRepository

class CacheUserIdUseCase(private val repository: UserCacheRepository) {

    operator fun invoke(id: String) = repository.cacheUserId(id)
}

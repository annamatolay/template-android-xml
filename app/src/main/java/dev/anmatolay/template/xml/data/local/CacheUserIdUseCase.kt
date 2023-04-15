package dev.anmatolay.template.xml.data.local

import dev.anmatolay.template.xml.data.repository.UserCacheRepository

class CacheUserIdUseCase(private val repository: UserCacheRepository) {

    operator fun invoke(id: String) = repository.cacheUserId(id)
}

package dev.anmatolay.template.xml.domain.usecase

import dev.anmatolay.template.xml.data.local.UserIdDataSource
import dev.anmatolay.template.xml.domain.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class UserCacheUseCase(private val dataSource: UserIdDataSource) {

    fun getCachedOrDefaultUser(): Single<User> =
        dataSource.getUserId()
            .defaultIfEmpty("null")
            .map { User(it) }

    fun cacheUserId(id: String): Completable =
        Completable.fromAction { dataSource.putUserId(id) }
}
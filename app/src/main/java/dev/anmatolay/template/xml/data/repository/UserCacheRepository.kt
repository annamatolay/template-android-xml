package dev.anmatolay.template.xml.data.repository

import dev.anmatolay.template.xml.data.local.UserIdDataSource
import dev.anmatolay.template.xml.domain.model.User
import dev.anmatolay.template.xml.util.Constants
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class UserCacheRepository(private val dataSource: UserIdDataSource) {

    fun getCachedOrDefaultUser(): Single<User> =
        dataSource.getUserId()
            .defaultIfEmpty(Constants.USER_DEFAULT_ID)
            .map { User(it) }

    fun cacheUserId(id: String): Completable =
        Completable.fromAction { dataSource.putUserId(id) }
}
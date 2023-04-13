package dev.anmatolay.template.xml.data.local

import dev.anmatolay.template.xml.core.authentication.Authenticator
import dev.anmatolay.template.xml.util.SharedPrefHandler
import dev.anmatolay.template.xml.util.toMaybe
import io.reactivex.rxjava3.core.Maybe

class UserIdDataSource(private val sharedPrefHandler: SharedPrefHandler) {

    fun getUserId(): Maybe<String> =
        sharedPrefHandler
            .getString(KEY_USER_ID)
            .toMaybe()

    fun putUserId(id: String) =
        sharedPrefHandler.putString(KEY_USER_ID, id)

    companion object {
        private const val KEY_USER_ID = "key_user_id"
    }
}

package dev.anmatolay.template.xml.data.local

import dev.anmatolay.template.xml.util.Constants.KEY_USER_ID
import dev.anmatolay.template.xml.core.SharedPrefHandler
import dev.anmatolay.template.xml.util.extension.toMaybe
import io.reactivex.rxjava3.core.Maybe

class UserIdDataSource(private val sharedPrefHandler: SharedPrefHandler) {

    fun getUserId(): Maybe<String> =
        sharedPrefHandler
            .getString(KEY_USER_ID)
            .toMaybe()

    fun putUserId(id: String) =
        sharedPrefHandler.putString(KEY_USER_ID, id)
}

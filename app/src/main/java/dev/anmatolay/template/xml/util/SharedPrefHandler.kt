package dev.anmatolay.template.xml.util

import android.content.Context
import android.content.SharedPreferences.Editor
import com.squareup.moshi.Moshi
import dev.anmatolay.template.xml.BuildConfig
import dev.anmatolay.template.xml.core.network.MoshiFactory

class SharedPrefHandler<T>(private val context: Context, private val moshi: Moshi) {

    val sharedPref = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)

    fun getString(key: String, defaultValue: String = "") =
        sharedPref.getString(key, defaultValue)

    fun setString(key: String, value: String) =
        edit { it.putString(key, value) }

    fun getBoolean(key: String, defaultValue: Boolean = false) =
        sharedPref.getBoolean(key, defaultValue)

    fun setString(key: String, value: Boolean) =
        edit { it.putBoolean(key, value) }

    fun getInt(key: String, defaultValue: Int = -1) =
        sharedPref.getInt(key, defaultValue)

    fun setString(key: String, value: Int) =
        edit { it.putInt(key, value) }

    inline fun <reified T> getObject(key: String): T? {
        val jsonString = getString(key)
        val adapter = MoshiFactory.create().adapter(T::class.java)
        return if (jsonString != null) {
            adapter.fromJson(jsonString)
        } else null
    }

    inline fun <reified T> getObject(key: String, value: T) {
        val adapter = MoshiFactory.create().adapter(T::class.java)
        val jsonString = adapter.toJson(value)
        with(sharedPref.edit()) {
            setString(key, jsonString)
            commit()
        }
    }

    private fun edit(action: (Editor) -> Editor) =
        with(sharedPref.edit()) {
            action.invoke(this)
            commit()
        }
}
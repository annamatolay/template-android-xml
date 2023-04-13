package dev.anmatolay.template.xml.util

import android.content.Context
import android.content.SharedPreferences.Editor
import dev.anmatolay.template.xml.BuildConfig
import dev.anmatolay.template.xml.core.network.MoshiFactory
import timber.log.Timber

class SharedPrefHandler(context: Context) {

    val sharedPref = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)

    fun getString(key: String, defaultValue: String? = null): String? =
        sharedPref.getString(key, defaultValue).logResult(key)

    fun setString(key: String, value: String) =
        edit { it.putString(key, value) }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean =
        sharedPref.getBoolean(key, defaultValue).logResult(key)

    fun setString(key: String, value: Boolean) =
        edit { it.putBoolean(key, value) }

    fun getInt(key: String, defaultValue: Int = -1) =
        sharedPref.getInt(key, defaultValue).logResult(key)

    fun setInt(key: String, value: Int) =
        edit { it.putInt(key, value) }

    inline fun <reified T> getObject(key: String): T? {
        val jsonString = getString(key)
        val adapter = MoshiFactory.create().adapter(T::class.java)
        return if (jsonString != null) {
            adapter.fromJson(jsonString)
        } else null
    }

    inline fun <reified T> setObject(key: String, value: T) {
        val adapter = MoshiFactory.create().adapter(T::class.java)
        val jsonString = adapter.toJson(value)
        with(sharedPref.edit()) {
            setString(key, jsonString)
            commit()
        }
        Timber.d("${T::class.java.typeName} data retrieved. KEY: $key VALUE: $this")
    }

    private inline fun <reified T>T.logResult(key: String): T =
        this.apply { Timber.d("${T::class.java.typeName} data retrieved. KEY: $key VALUE: $this") }

    private fun edit(action: (Editor) -> Editor) =
        with(sharedPref.edit()) {
            action.invoke(this)
            Timber.d("Data saved. $this")
            commit()
        }
}
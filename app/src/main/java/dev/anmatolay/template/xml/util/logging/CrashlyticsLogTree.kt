package dev.anmatolay.template.xml.util.logging

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.ktx.setCustomKeys
import timber.log.Timber

class CrashlyticsLogTree(
    private val firebaseCrashlytics: FirebaseCrashlytics,
    private val userId: String?,
) : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority < Log.ERROR) return

        firebaseCrashlytics.setUserId(userId ?: "null")
        firebaseCrashlytics.setCustomKeys {
            tag?.let { key(CRASHLYTICS_KEY_TAG, it) }
            key(CRASHLYTICS_KEY_MESSAGE, message)
        }

        if (t != null) {
            firebaseCrashlytics.recordException(t)
        } else {
            firebaseCrashlytics.log("$priority/$tag: $message")
        }
    }

    companion object {
        private const val CRASHLYTICS_KEY_TAG = "tag"
        private const val CRASHLYTICS_KEY_MESSAGE = "message"
    }
}

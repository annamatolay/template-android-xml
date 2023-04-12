package dev.anmatolay.template.xml.util.logging

import timber.log.Timber.DebugTree

class DiamondDebugTree : DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, tag, "<> $message", t)
    }
}

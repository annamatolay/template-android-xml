package dev.anmatolay.template.xml.core.threading.impl

import dev.anmatolay.template.xml.core.threading.SchedulerProvider
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.TestScheduler

class TestSchedulerProvider : SchedulerProvider {
    override fun io() = Schedulers.trampoline()
    override fun computation() = Schedulers.trampoline()
    override fun mainThread() = Schedulers.trampoline()
}

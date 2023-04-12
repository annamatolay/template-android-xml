package dev.anmatolay.template.xml.core.threading.impl

import dev.anmatolay.template.xml.core.threading.SchedulerProvider
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.TestScheduler

class TestSchedulerProvider(val testScheduler: TestScheduler? = null) : SchedulerProvider {
    override fun io() = testScheduler ?: Schedulers.trampoline()
    override fun computation() = testScheduler ?: Schedulers.trampoline()
    override fun mainThread() = testScheduler ?: Schedulers.trampoline()
}

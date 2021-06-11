package com.moviecatalogue.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.junit.Assert.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

fun <T> LiveData<T>.getValueOrAwait(): T {
    val data = arrayOfNulls<Any>(1)
    val latch = CountDownLatch(1)

    val observer = object : Observer<T> {
        override fun onChanged(o: T) {
            data[0] = o
            latch.countDown()
            this@getValueOrAwait.removeObserver(this)
        }
    }

    this.observeForever(observer)

    try {
        latch.await(2, TimeUnit.SECONDS)
    } catch (e: InterruptedException) {
        e.printStackTrace()
    }

    return data[0] as T

}
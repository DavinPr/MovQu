package com.app.moviecatalogue.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.Assert.*
import java.nio.charset.StandardCharsets
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

internal fun MockWebServer.enqueueResponse(fileName: String, code: Int) {
    val inputStream = javaClass.classLoader?.getResourceAsStream("api-response/$fileName")

    val source = inputStream?.let { inputStream.source().buffer() }
    source?.let {
        enqueue(
            MockResponse()
                .setResponseCode(code)
                .setBody(source.readString(StandardCharsets.UTF_8))
        )
    }
}

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
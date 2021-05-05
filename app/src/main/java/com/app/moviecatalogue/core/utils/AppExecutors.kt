package com.app.moviecatalogue.core.utils

import android.os.Handler
import android.os.Looper
import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors @VisibleForTesting constructor(private val diskIO: Executor) {
    constructor() : this(Executors.newSingleThreadExecutor())
    fun diskIO(): Executor = diskIO
}
package com.lcardoso.fastshoptest.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.nonNullObserve(owner: LifecycleOwner, observer: (data: T) -> Unit) {
    observe(owner, Observer {
        it?.let(observer)
    })
}
package com.lcardoso.fastshoptest.api

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> doRequest(
    hasChainedRequest: Boolean = false,
    doOnError: (() -> Unit)? = null,
    backendCall: () -> Observable<T>
): Observable<T> {
    return backendCall()
        .defaultSchedulers()
        .requestIt(hasChainedRequest, doOnError)
}

private fun <T> Observable<T>.requestIt(
    hasChainedRequest: Boolean = false,
    doOnError: (() -> Unit)?
): Observable<T> {
    return doOnSubscribe { }.doFinally { }.doOnError { doOnError?.invoke() }
}

fun ui(): Scheduler = AndroidSchedulers.mainThread()

fun <T> Observable<T>.defaultSchedulers(): Observable<T> =
    subscribeOn(Schedulers.io()).observeOn(ui())
package com.lcardoso.fastshoptest.usecase

import io.reactivex.Observable

interface UseCase<T, in Param> {
    fun execute(params: Param): Observable<T>
}
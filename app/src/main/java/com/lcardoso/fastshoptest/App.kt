package com.lcardoso.fastshoptest

import android.app.Application
import com.lcardoso.fastshoptest.api.RetrofitProvider
import com.lcardoso.fastshoptest.di.AppComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal open class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initRetrofit()
        startKoin {
            androidContext(this@App)
            modules(
                AppComponent.apiModule,
                AppComponent.repository,
                AppComponent.useCaseModule,
                AppComponent.viewModelModule
            )
        }
    }

    protected open fun initRetrofit() = RetrofitProvider.initRetrofit()
}
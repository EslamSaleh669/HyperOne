package com.HyperOne.util

import android.app.Application
import android.content.Context
import com.HyperOne.di.*
import com.airbnb.lottie.BuildConfig
import com.yariksoffice.lingver.Lingver
import timber.log.Timber

class MyApplication() : Application() {

    var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .networkModule(NetworkModule((baseContext)))
            .viewModelFactoryModule(ViewModelFactoryModule()).build()


        Lingver.init(
            this,
            getSharedPreferences(
                Constants.SHARED_NAME,
                Context.MODE_PRIVATE
            ).getString(Constants.LANG_KEY, "en") ?: "en"
        )
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}
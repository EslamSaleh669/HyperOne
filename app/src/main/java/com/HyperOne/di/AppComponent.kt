package com.HyperOne.di


import com.HyperOne.ui.activity.home.HomeActivity
import dagger.Component


@Component(modules = [
    NetworkModule::class,
    ViewModelFactoryModule::class
])
interface AppComponent {

    fun inject(homeActivity: HomeActivity)

}
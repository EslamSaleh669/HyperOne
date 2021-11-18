package com.HyperOne.di

import androidx.lifecycle.ViewModel
import dagger.Module
import javax.inject.Named
import androidx.lifecycle.ViewModelProvider.Factory
import com.HyperOne.data.repo.UserRepo
import com.HyperOne.ui.activity.home.MainViewModel

import dagger.Provides


@Module
class ViewModelFactoryModule()  {

    @Provides
    @Named("main")
    fun provideLoginViewModel(userRepo: UserRepo): Factory {
        return object : Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(userRepo) as T
            }
        }
    }


}
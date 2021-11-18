package com.HyperOne.ui.activity.home

import androidx.lifecycle.ViewModel
import com.HyperOne.data.model.DataResponseItem
import com.HyperOne.data.repo.UserRepo

import io.reactivex.Observable

class MainViewModel( private val userRepo: UserRepo) : ViewModel() {


    fun getData(): Observable<ArrayList<DataResponseItem>> = userRepo.getOurData()



}
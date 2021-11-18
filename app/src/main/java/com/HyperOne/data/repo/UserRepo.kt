package com.HyperOne.data.repo

import com.HyperOne.data.model.DataResponseItem
import com.HyperOne.data.network.ApiClient
import com.HyperOne.di.room.dao.ItemDao
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class UserRepo  @Inject constructor(
    private val apiClient: ApiClient,
) {



    fun getOurData() : Observable<ArrayList<DataResponseItem>>{

        return  apiClient.getData().subscribeOn(Schedulers.io())

    }




}
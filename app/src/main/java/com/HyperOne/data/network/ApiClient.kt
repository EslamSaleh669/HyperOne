package com.HyperOne.data.network

import com.HyperOne.data.model.DataResponseItem
import io.reactivex.Observable
import retrofit2.http.*

interface ApiClient {


    @GET("HyperoneWebservice/getListOfFilesResponse.json")
    fun getData(): Observable<ArrayList<DataResponseItem>>

}

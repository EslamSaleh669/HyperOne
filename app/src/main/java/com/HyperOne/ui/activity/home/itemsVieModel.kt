package com.HyperOne.ui.activity.home

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import android.app.Application
import com.HyperOne.data.repo.ItemRepo
import com.HyperOne.di.room.database.AppDateBase
import com.HyperOne.di.room.entity.ItemModel

class itemsViewModel(application: Application) : AndroidViewModel(application) {
     val db: AppDateBase = AppDateBase.getInstance(application)

    val allItems: LiveData<List<ItemModel>>

    val repository: ItemRepo

    init {
        val dao = db.itemDao()
        repository = ItemRepo(dao)
        allItems = repository.allItems
    }

    fun insert(item: ItemModel) {
        repository.insert(item)
    }


}
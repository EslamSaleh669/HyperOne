package com.HyperOne.data.repo

import androidx.lifecycle.LiveData
import com.HyperOne.di.room.dao.ItemDao
import com.HyperOne.di.room.entity.ItemModel

class ItemRepo(private val itemDao: ItemDao) {

    internal val allItems: LiveData<List<ItemModel>> = itemDao.allItems()

    fun insert(item: ItemModel) {
        itemDao.insertItem(item)
    }


}
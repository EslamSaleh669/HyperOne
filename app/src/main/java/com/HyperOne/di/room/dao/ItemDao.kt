package com.HyperOne.di.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.HyperOne.di.room.entity.ItemModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides


@Dao
interface ItemDao {

    @Query("SELECT * FROM Items")
    fun allItems(): LiveData<List<ItemModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(itemModel: ItemModel)

}
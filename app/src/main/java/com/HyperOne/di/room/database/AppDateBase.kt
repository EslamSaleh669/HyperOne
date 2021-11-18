package com.HyperOne.di.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.HyperOne.di.room.dao.ItemDao
import com.HyperOne.di.room.entity.ItemModel


@Database(entities = [ItemModel::class], version = 1, exportSchema = false)
abstract class AppDateBase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        var INSTANCE: AppDateBase? = null
        fun getInstance(context: Context): AppDateBase {
            if (INSTANCE == null) {
                INSTANCE = databaseBuilder(
                    context,
                    AppDateBase::class.java,
                    "roomdb"
                ).build()

             }

            return INSTANCE as AppDateBase
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
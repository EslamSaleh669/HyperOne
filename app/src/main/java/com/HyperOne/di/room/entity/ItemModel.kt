package com.HyperOne.di.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Items")
data class ItemModel(

    @PrimaryKey
    @ColumnInfo(name = "itemid")
    var itemid:Int




    )
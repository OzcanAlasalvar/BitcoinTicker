package com.ozcanalasalvar.bitcointicker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ozcanalasalvar.bitcointicker.data.model.SimpleModel

@Database(entities = [SimpleModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao

}
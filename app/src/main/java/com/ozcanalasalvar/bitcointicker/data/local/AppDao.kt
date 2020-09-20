package com.ozcanalasalvar.bitcointicker.data.local

import androidx.room.*
import com.ozcanalasalvar.bitcointicker.data.model.SimpleModel

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(cities: List<SimpleModel>)

    @Query("SELECT *FROM coinModel")
    fun fetchCoins(): List<SimpleModel>

    @Query("Select *FROM coinModel WHERE name LIKE :query or symbol LIKE :query")
    fun fetchCoinByQuery(query: String): List<SimpleModel>

    @Query("DELETE FROM coinModel")
    fun deleteDB()

}
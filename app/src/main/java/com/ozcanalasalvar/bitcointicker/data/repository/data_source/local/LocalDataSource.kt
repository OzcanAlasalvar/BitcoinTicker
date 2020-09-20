package com.ozcanalasalvar.bitcointicker.data.repository.data_source.local

import com.ozcanalasalvar.bitcointicker.data.local.db.AppDao
import com.ozcanalasalvar.bitcointicker.data.model.SimpleModel
import io.reactivex.Observable
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val dao: AppDao) :
    LocalSource {

    override fun saveAll(cities: List<SimpleModel>) = dao.insertAll(cities)

    override fun fetchCoins(): Observable<List<SimpleModel>> {
        return Observable.fromCallable {
            dao.fetchCoins()
        }

    }

    override fun fetchCoinByQuery(query: String): Observable<List<SimpleModel>> =
        Observable.fromCallable {
            dao.fetchCoinByQuery("%$query%")
        }


    override fun deleteDB() = dao.deleteDB()


}
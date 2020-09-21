package com.ozcanalasalvar.bitcointicker.data.model

object FavouriteDataHolder {

    private val arrayList: ArrayList<String> = ArrayList()

    fun saveFavourites(list: List<String>) {
        arrayList.clear()
        arrayList.addAll(list)
    }

    fun addFavourite(id: String) {
        arrayList.add(id)
    }

    fun remove(id: String) {
        if (arrayList.contains(id))
            arrayList.remove(id)
    }

    fun getList(): ArrayList<String> = arrayList

    fun getIds(): String? {
        var ids: String? = ""
        for (x in 0 until arrayList.size) {
            ids += arrayList[x]
            if (x != arrayList.size - 1)
                ids += ","
        }
        return ids
    }
}
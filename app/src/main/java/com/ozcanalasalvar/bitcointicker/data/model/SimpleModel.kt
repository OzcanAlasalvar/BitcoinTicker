package com.ozcanalasalvar.bitcointicker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coinModel")
class SimpleModel(
    @SerializedName("id") @PrimaryKey var id: String,
    @SerializedName("symbol") var symbol: String?,
    @SerializedName("name") var name: String?
)
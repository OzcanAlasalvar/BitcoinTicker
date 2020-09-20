package com.ozcanalasalvar.bitcointicker.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    companion object {
        @SuppressLint("SimpleDateFormat")
        fun formatDateString(dateString: String): String {
            val sdf =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val out =
                SimpleDateFormat("dd.MM.yyyy | HH:mm")

            val date: Date = sdf.parse(dateString)

            return out.format(date)
        }
    }
}
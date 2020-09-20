package com.ozcanalasalvar.bitcointicker.data.local.shared

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(mContext: Context) {
    private val mUserPreferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    companion object {
        const val PREFS_NAME = "UserPrefsFile"
        private var sharedPreference: SharedPreference? = null
        var pageDetector: Int = 0

        fun get(): SharedPreference? {
            return sharedPreference
        }

        fun getInstanceForDagger(mContext: Context): SharedPreferences {
            if (sharedPreference == null) {
                sharedPreference =
                    SharedPreference(
                        mContext
                    )
            }
            return sharedPreference!!.mUserPreferences
        }
    }

    init {
        mUserPreferences = mContext.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )
        editor = mUserPreferences.edit()
        pageDetector = 1
    }
}
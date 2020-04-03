package com.bigbang.booksearch.util

import android.util.Log
import com.bigbang.booksearch.util.Constants.Companion.ERROR_PREFIX
import com.bigbang.booksearch.util.Constants.Companion.TAG

class DebugLogger {

    fun logError(throwable: Throwable){
        Log.d(TAG, ERROR_PREFIX + throwable.localizedMessage)
    }
    fun logDebug(message: String){
        Log.d(TAG, message)
    }
}
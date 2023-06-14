package com.travelee.signing.utilities

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.travelee.signing.utilities.Constants.TAG

class Utils {
    companion object {
        fun print(e: Exception) = Log.e(TAG, e.stackTraceToString())

        fun showMessage(
            context: Context,
            message: String?
        ) = Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}
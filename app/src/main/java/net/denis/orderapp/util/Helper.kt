package net.denis.orderapp.util

import android.content.Context
import android.text.TextUtils
import android.widget.Toast

interface Helper {

    @SafeVarargs
    fun inputCheck(vararg params: String): Boolean {
        return !(TextUtils.isEmpty(params.toString()))
    }

    fun makeToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

}
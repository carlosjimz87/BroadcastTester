package com.carlosjimz.broadcasttester

import android.content.Intent

object Constants {
    const val ACTION = "com.test.action"
    const val FLAG = Intent.FLAG_INCLUDE_STOPPED_PACKAGES
    val ExtraString = Pair("string", "stringVal")
    val ExtraBoolean = Pair("boolean", true)
    val ExtraInt = Pair("int", 1)
    val ExtraDouble = Pair("double", 1.5)
    val ExtraFloat = Pair("float", 1f)
    val ExtraLong = Pair("long", 1L)
    val ExtraChar = Pair("char", 'C')
}
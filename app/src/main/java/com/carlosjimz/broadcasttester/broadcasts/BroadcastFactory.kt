package com.carlosjimz.broadcasttester.broadcasts

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

object BroadcastFactory {

    private lateinit var intent: Intent

    fun <T> build(
        action: String,
        flag: Int? = null,
        extras: Map<String, T> = emptyMap()
    ): BroadcastFactory {
        intent = Intent().apply {
            this.action = action
            this.flags = flag ?: 0
            putExtras(this, extras)
        }
        return this
    }

    fun send(
        context: Context
    ): Intent {
        context.sendBroadcast(intent)
        return intent
    }

    private fun <T> putExtras(intent: Intent, extras: Map<String, T>?) {
        extras?.forEach { e ->
            when (e.value) {
                is String -> intent.putExtra(e.key, e.value as String)
                is Boolean -> intent.putExtra(e.key, e.value as Boolean)
                is Int -> intent.putExtra(e.key, e.value as Int)
                is IntArray -> intent.putExtra(e.key, e.value as IntArray)
                is Float -> intent.putExtra(e.key, e.value as Float)
                is FloatArray -> intent.putExtra(e.key, e.value as FloatArray)
                is Double -> intent.putExtra(e.key, e.value as Double)
                is DoubleArray -> intent.putExtra(e.key, e.value as DoubleArray)
                is Long -> intent.putExtra(e.key, e.value as Long)
                is LongArray -> intent.putExtra(e.key, e.value as LongArray)
                is Short -> intent.putExtra(e.key, e.value as Short)
                is ShortArray -> intent.putExtra(e.key, e.value as ShortArray)
                is Byte -> intent.putExtra(e.key, e.value as Byte)
                is ByteArray -> intent.putExtra(e.key, e.value as ByteArray)
                is Char -> intent.putExtra(e.key, e.value as Char)
                is CharArray -> intent.putExtra(e.key, e.value as CharArray)
                is CharSequence -> intent.putExtra(e.key, e.value as CharSequence)
                is Bundle -> intent.putExtra(e.key, e.value as Bundle)
                is Parcelable -> intent.putExtra(e.key, e.value as Parcelable)
                is Serializable -> intent.putExtra(e.key, e.value as Serializable)
            }
        }
    }


}
package com.superwindcloud.blackmango.js

import android.util.Log
import com.whl.quickjs.android.QuickJSLoader
import com.whl.quickjs.wrapper.JSObject
import com.whl.quickjs.wrapper.QuickJSContext
import java.io.Closeable

data class JsExecutionResult(val renderedOutput: String)

class QuickJsConsoleEngine : Closeable {
    companion object {
        private const val TAG = "QuickJsConsole"
    }

    private val logs = mutableListOf<String>()
    private val context =
        QuickJSContext.create().apply {
            setEnableStackTrace(true)
            QuickJSLoader.initConsoleLog(
                this,
                object : QuickJSContext.Console {
                    override fun log(value: String) {
                        logs += value
                        Log.d(TAG, value)
                    }

                    override fun info(value: String) {
                        logs += "info: $value"
                        Log.i(TAG, value)
                    }

                    override fun warn(value: String) {
                        logs += "warn: $value"
                        Log.w(TAG, value)
                    }

                    override fun error(value: String) {
                        logs += "error: $value"
                        Log.e(TAG, value)
                    }
                },
            )
        }

    fun execute(script: String): JsExecutionResult {
        logs.clear()
        Log.d(TAG, "Executing script:\n$script")
        val result = context.evaluate(script)
        val renderedResult = renderValue(result)
        Log.d(TAG, "Execution result: $renderedResult")
        val rendered =
            buildList {
                addAll(logs)
                add("=> $renderedResult")
            }.joinToString(separator = "\n")
        return JsExecutionResult(rendered)
    }

    private fun renderValue(value: Any?): String =
        when (value) {
            null -> "undefined"
            is JSObject -> {
                try {
                    value.stringify()
                } finally {
                    value.release()
                }
            }
            else -> value.toString()
        }

    override fun close() {
        context.close()
    }
}

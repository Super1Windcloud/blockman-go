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
        private const val PREFIX_SCRIPT = "[JS-SCRIPT]"
        private const val PREFIX_LOG = "[JS-LOG]"
        private const val PREFIX_INFO = "[JS-INFO]"
        private const val PREFIX_WARN = "[JS-WARN]"
        private const val PREFIX_ERROR = "[JS-ERROR]"
        private const val PREFIX_OUTPUT = "[JS-OUTPUT]"
    }

    private val logs = mutableListOf<String>()

    fun execute(script: String): JsExecutionResult {
        logs.clear()
        Log.d(TAG, "$PREFIX_SCRIPT\n$script")
        val rendered =
            createContext().use { context ->
                val result = context.evaluate(script)
                val renderedResult = renderValue(result)
                val outputLine = "$PREFIX_OUTPUT $renderedResult"
                Log.d(TAG, outputLine)
                buildList {
                    addAll(logs)
                    add(outputLine)
                }.joinToString(separator = "\n")
            }
        return JsExecutionResult(rendered)
    }

    private fun createContext(): QuickJSContext =
        QuickJSContext.create().apply {
            setEnableStackTrace(true)
            QuickJSLoader.initConsoleLog(
                this,
                object : QuickJSContext.Console {
                    override fun log(value: String) {
                        logs += "$PREFIX_LOG $value"
                        Log.d(TAG, "$PREFIX_LOG $value")
                    }

                    override fun info(value: String) {
                        logs += "$PREFIX_INFO $value"
                        Log.i(TAG, "$PREFIX_INFO $value")
                    }

                    override fun warn(value: String) {
                        logs += "$PREFIX_WARN $value"
                        Log.w(TAG, "$PREFIX_WARN $value")
                    }

                    override fun error(value: String) {
                        logs += "$PREFIX_ERROR $value"
                        Log.e(TAG, "$PREFIX_ERROR $value")
                    }
                },
            )
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
        logs.clear()
    }
}

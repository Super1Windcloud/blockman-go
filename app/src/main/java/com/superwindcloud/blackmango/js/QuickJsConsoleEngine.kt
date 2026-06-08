package com.superwindcloud.blackmango.js

import com.whl.quickjs.android.QuickJSLoader
import com.whl.quickjs.wrapper.JSObject
import com.whl.quickjs.wrapper.QuickJSContext
import java.io.Closeable

data class JsExecutionResult(val renderedOutput: String)

class QuickJsConsoleEngine : Closeable {
    private val logs = mutableListOf<String>()
    private val context =
        QuickJSContext.create().apply {
            setEnableStackTrace(true)
            QuickJSLoader.initConsoleLog(
                this,
                object : QuickJSContext.Console {
                    override fun log(value: String) {
                        logs += value
                    }

                    override fun info(value: String) {
                        logs += "info: $value"
                    }

                    override fun warn(value: String) {
                        logs += "warn: $value"
                    }

                    override fun error(value: String) {
                        logs += "error: $value"
                    }
                },
            )
        }

    fun execute(script: String): JsExecutionResult {
        logs.clear()
        val result = context.evaluate(script)
        val rendered =
            buildList {
                addAll(logs)
                add("=> ${renderValue(result)}")
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

package com.superwindcloud.blackmango.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.superwindcloud.blackmango.js.QuickJsConsoleEngine
import com.superwindcloud.blackmango.ui.navigation.ScreenBackground

private val starterScript =
    """
    console.log("QuickJS ready");
    const total = [1, 2, 3, 4].reduce((sum, item) => sum + item, 0);
    """
        .trimIndent()

@Composable
fun JsConsoleTabPage(modifier: Modifier = Modifier) {
    val engine = remember { QuickJsConsoleEngine() }
    var script by rememberSaveable { mutableStateOf(starterScript) }
    var output by rememberSaveable { mutableStateOf("点击执行后，这里会显示 console.log 和返回值。") }

    DisposableEffect(engine) { onDispose { engine.close() } }

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(ScreenBackground)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Text(
            text = "JS Run Console",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Black,
            color = Color(0xFF171A20),
        )
        Text(
            text = "界面测试方式 1：输入框 + 执行按钮 + 输出区",
            color = Color(0xFF6D7380),
            fontSize = 14.sp,
        )
        OutlinedTextField(
            value = script,
            onValueChange = { script = it },
            modifier = Modifier.fillMaxWidth().height(220.dp),
            label = { Text("输入 JS") },
            shape = RoundedCornerShape(16.dp),
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily.Monospace),
            keyboardOptions =
                KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrectEnabled = false,
                ),
        )
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                onClick = {
                    output =
                        runCatching { engine.execute(script).renderedOutput }
                            .getOrElse { error -> "执行失败\n${error.message ?: error.javaClass.simpleName}" }
                }
            ) {
                Text("执行")
            }
            TextButton(
                onClick = {
                    script = starterScript
                    output = "已重置示例脚本。"
                }
            ) {
                Text("重置示例")
            }
        }
        Text(
            text = "输出 log",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF202124),
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF12151C)),
        ) {
            Text(
                text = output,
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                color = Color(0xFFE8EDF6),
                fontFamily = FontFamily.Monospace,
                fontSize = 13.sp,
                lineHeight = 20.sp,
            )
        }
        Spacer(Modifier.height(88.dp))
    }
}

package com.superwindcloud.blackmango

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.superwindcloud.blackmango.ui.screens.BlackMangoApp
import com.superwindcloud.blackmango.ui.theme.BlackmangoTheme
import com.whl.quickjs.android.QuickJSLoader

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QuickJSLoader.init()
        enableEdgeToEdge()
        setContent { BlackmangoTheme(dynamicColor = false) { BlackMangoApp() } }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 720)
@Composable
private fun BlackMangoAppPreview() {
    BlackmangoTheme(dynamicColor = false) { BlackMangoApp() }
}

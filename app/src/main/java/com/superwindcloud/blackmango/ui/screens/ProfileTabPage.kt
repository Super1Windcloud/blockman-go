package com.superwindcloud.blackmango.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Redeem
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ViewInAr
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.superwindcloud.blackmango.R
import com.superwindcloud.blackmango.ui.navigation.ScreenBackground

@Composable
fun ProfileTabPage(modifier: Modifier = Modifier) {
    Box(modifier = modifier.background(ScreenBackground)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding =
                PaddingValues(start = 22.dp, top = 18.dp, end = 22.dp, bottom = 104.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp),
        ) {
            item { ProfileHero() }
            item { GuestNotice() }
            item { ShortcutPanel() }
            item { SettingsPanel() }
        }
    }
}

@Composable
private fun ProfileHero() {
    Box(Modifier.fillMaxWidth()) {
        Icon(
            imageVector = Icons.Filled.Edit,
            contentDescription = "编辑资料",
            modifier = Modifier.align(Alignment.TopEnd).size(34.dp),
            tint = Color(0xFF15191F),
        )
        PinkDot(modifier = Modifier.align(Alignment.TopEnd).padding(top = 1.dp, end = 1.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 62.dp, end = 44.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(R.drawable.profile_avatar),
                contentDescription = "用户头像",
                modifier =
                    Modifier.size(92.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(5.dp, Color(0xFFDAB176), RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )
            Spacer(Modifier.width(28.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "guestuser6110448",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Black,
                        color = Color.Black,
                    )
                    Spacer(Modifier.width(8.dp))
                    VipBadge()
                }
                Text(
                    "用户名: 未设置",
                    color = Color(0xFF8B8B90),
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    "ID: 7301598686",
                    color = Color(0xFF8B8B90),
                    style = MaterialTheme.typography.titleMedium,
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TinyCurrency(Icons.Filled.Paid, Color(0xFFFF47A8), Color(0xFFFFCC40))
                    Text(
                        "0",
                        modifier = Modifier.padding(horizontal = 18.dp),
                        color = Color(0xFF333333),
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Box(Modifier.height(28.dp).width(1.dp).background(Color(0xFFD6D6D8)))
                    TinyCurrency(Icons.Filled.ViewInAr, Color(0xFFFFD548), Color(0xFF9B7419))
                    Text(
                        "0",
                        modifier = Modifier.padding(start = 18.dp),
                        color = Color(0xFF333333),
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
            }
        }
    }
}

@Composable
private fun VipBadge() {
    Box(
        modifier =
            Modifier.width(62.dp)
                .height(27.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Brush.horizontalGradient(listOf(Color(0xFFEFF7FF), Color(0xFFBBC9D5))))
                .border(1.dp, Color(0xFF9DAEBB), RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.Center,
    ) {
        Text("VIP0", color = Color(0xFF27313B), fontWeight = FontWeight.Black)
    }
}

@Composable
private fun GuestNotice() {
    Row(
        modifier =
            Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(22.dp))
                .background(Color(0xFFECE8FF))
                .padding(start = 28.dp, top = 28.dp, end = 22.dp, bottom = 28.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        Text(
            text = "当前账号为游客账号，无法在其他设备登录，请完成注册以确保账号的安全性",
            modifier = Modifier.weight(1f),
            color = Color(0xFF111318),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            lineHeight = MaterialTheme.typography.titleLarge.lineHeight,
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("立刻注册", color = Color(0xFF8D62EA), style = MaterialTheme.typography.titleMedium)
            Box(
                modifier =
                    Modifier.padding(start = 8.dp)
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFDAD0FF)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = Color(0xFF8E62EE))
            }
        }
    }
}

@Composable
private fun ShortcutPanel() {
    Row(
        modifier =
            Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        ShortcutItem("月卡", Icons.Filled.CreditCard, Color(0xFFFF45AF), Color(0xFF7F5BFF))
        ShortcutItem("VIP", Icons.Filled.WorkspacePremium, Color(0xFFFFC629), Color(0xFFFFE77A))
        ShortcutItem("充值", Icons.Filled.Paid, Color(0xFFFFD33E), Color(0xFFF0A50D))
        ShortcutItem("订阅", Icons.Filled.Diamond, Color(0xFFFF34B5), Color(0xFFFF8FD9))
        ShortcutItem("礼包", Icons.Filled.Redeem, Color(0xFFFFC032), Color(0xFFFF4FB0))
    }
}

@Composable
private fun ShortcutItem(label: String, icon: ImageVector, colorA: Color, colorB: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(42.dp),
            tint = colorA,
        )
        Text(
            label,
            color = Color(0xFF424242),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun SettingsPanel() {
    Column(
        modifier =
            Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(Color.White)
                .padding(top = 8.dp)
    ) {
        MenuItem("活动中心", Icons.Filled.Flag, Color(0xFFF24F6D))
        MenuItem("背包", Icons.Filled.ShoppingBag, Color(0xFFFF864B))
        MenuItem("夜间模式", Icons.Filled.DarkMode, Color(0xFF8C61D8))
        MenuItem("视频", Icons.Filled.PlayCircle, Color(0xFF6696F0))
        MenuItem("官方社媒", Icons.Filled.MusicNote, Color(0xFF9B5AEF))
        MenuItem("官网", Icons.Filled.Public, Color(0xFF5C8FF1), showDot = true)
        MenuItem("收件箱", Icons.Filled.Email, Color(0xFF42CFE0))
        MenuItem("设置", Icons.Filled.Settings, Color(0xFF8EA3CC), showDot = true)
        MenuItem("帮助", Icons.AutoMirrored.Filled.Help, Color(0xFF5FC58E))
    }
}

@Composable
private fun MenuItem(label: String, icon: ImageVector, iconColor: Color, showDot: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth().height(82.dp).padding(horizontal = 34.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(icon, contentDescription = label, modifier = Modifier.size(34.dp), tint = iconColor)
        Spacer(Modifier.width(34.dp))
        Text(label, color = Color(0xFF55575C), style = MaterialTheme.typography.headlineSmall)
        if (showDot) {
            PinkDot(modifier = Modifier.padding(start = 4.dp))
        }
        Spacer(Modifier.weight(1f))
        Icon(
            Icons.Filled.ChevronRight,
            contentDescription = null,
            modifier = Modifier.size(34.dp),
            tint = Color(0xFFD2D2D2),
        )
    }
}

@Composable
private fun TinyCurrency(icon: ImageVector, colorA: Color, colorB: Color) {
    Box(
        modifier =
            Modifier.size(30.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Brush.linearGradient(listOf(colorA, colorB))),
        contentAlignment = Alignment.Center,
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(20.dp), tint = Color.White)
    }
}

@Composable
private fun PinkDot(modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(14.dp).clip(CircleShape).background(Color(0xFFFF2C93)))
}

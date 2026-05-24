package com.superwindcloud.blackmango

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.superwindcloud.blackmango.ui.theme.BlackmangoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlackmangoTheme(dynamicColor = false) {
                ProfileScreen()
            }
        }
    }
}

@Composable
private fun ProfileScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF5F6F8)
    ) {
        Box(Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 88.dp)
            ) {
                FakeStatusBar()
                ProfileHeader()
                RegisterBanner()
                ShortcutPanel()
                MenuPanel()
            }
            BottomNavigationBar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .navigationBarsPadding()
            )
        }
    }
}

@Composable
private fun FakeStatusBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(48.dp)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "20:03 | 5.4K/s",
            color = Color(0xFF30323A),
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(Modifier.weight(1f))
        StatusIconSet()
    }
}

@Composable
private fun StatusIconSet() {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(7.dp)) {
        Text("⌁", color = Color(0xFF2C3036), fontSize = 21.sp, fontWeight = FontWeight.Bold)
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.offset(y = (-1).dp)) {
            Text("HD", color = Color(0xFF2C3036), fontSize = 7.sp, fontWeight = FontWeight.Bold)
            Canvas(Modifier.size(width = 19.dp, height = 13.dp)) {
                val barWidth = size.width / 6f
                repeat(4) { index ->
                    val h = size.height * (0.35f + index * 0.16f)
                    drawRoundRect(
                        Color(0xFF2C3036),
                        topLeft = Offset(index * barWidth * 1.35f, size.height - h),
                        size = Size(barWidth, h),
                        cornerRadius = CornerRadius(1.6f, 1.6f)
                    )
                }
            }
        }
        Canvas(Modifier.size(23.dp)) {
            drawArc(Color(0xFF2C3036), 215f, 110f, false, style = Stroke(3.5f, cap = StrokeCap.Round))
            drawArc(Color(0xFF2C3036), 225f, 90f, false, topLeft = Offset(4f, 5f), size = Size(size.width - 8f, size.height - 8f), style = Stroke(3f, cap = StrokeCap.Round))
            drawCircle(Color(0xFF2C3036), radius = 2.4f, center = Offset(size.width / 2f, size.height * .68f))
        }
        Box(
            modifier = Modifier
                .height(16.dp)
                .width(30.dp)
                .border(1.dp, Color(0xFF2C3036), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("97", color = Color(0xFF2C3036), fontSize = 9.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun ProfileHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(209.dp)
            .padding(horizontal = 24.dp)
    ) {
        ComposeEditIcon(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 20.dp)
                .size(34.dp)
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .shadow(0.dp, RoundedCornerShape(6.dp))
                    .background(Color(0xFFC99B61), RoundedCornerShape(6.dp))
                    .padding(5.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(2.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(Modifier.width(22.dp))
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "guestuser6110448",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    VipBadge()
                }
                Spacer(Modifier.height(10.dp))
                Text("用户名: 未设置", color = Color(0xFF86888E), fontSize = 14.sp, fontWeight = FontWeight.Medium)
                Spacer(Modifier.height(7.dp))
                Text("ID: 7301598686", color = Color(0xFF8E9097), fontSize = 13.sp, fontWeight = FontWeight.Medium)
                Spacer(Modifier.height(13.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    MiniTicketIcon()
                    Text("0", modifier = Modifier.padding(start = 18.dp), color = Color(0xFF454850), fontSize = 16.sp)
                    Text("|", modifier = Modifier.padding(horizontal = 24.dp), color = Color(0xFFC2C3C8), fontSize = 20.sp)
                    MiniGoldIcon()
                    Text("0", modifier = Modifier.padding(start = 18.dp), color = Color(0xFF454850), fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
private fun VipBadge() {
    Box(
        modifier = Modifier
            .padding(start = 8.dp)
            .width(42.dp)
            .height(18.dp)
            .clip(RoundedCornerShape(3.dp))
            .background(
                Brush.linearGradient(
                    listOf(Color(0xFFBCD6E2), Color(0xFFEAF3F4), Color(0xFF8399A6))
                )
            )
            .border(1.dp, Color(0xFFB5C4C9), RoundedCornerShape(3.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text("VIP0", color = Color(0xFF1F2C3A), fontSize = 11.sp, fontWeight = FontWeight.Black)
    }
}

@Composable
private fun ComposeEditIcon(modifier: Modifier = Modifier) {
    Box(modifier) {
        Canvas(Modifier.fillMaxSize()) {
            val stroke = Stroke(width = 4.2f, cap = StrokeCap.Round)
            drawRoundRect(
                Color(0xFF20252C),
                topLeft = Offset(size.width * .15f, size.height * .33f),
                size = Size(size.width * .48f, size.height * .48f),
                cornerRadius = CornerRadius(5f, 5f),
                style = stroke
            )
            drawLine(Color(0xFF20252C), Offset(size.width * .48f, size.height * .56f), Offset(size.width * .84f, size.height * .20f), strokeWidth = 4.5f, cap = StrokeCap.Round)
            drawCircle(Color(0xFFFF2E90), radius = size.width * .14f, center = Offset(size.width * .90f, size.height * .13f))
        }
    }
}

@Composable
private fun RegisterBanner() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 9.dp)
            .height(86.dp)
            .background(Color(0xFFE6E1FA), RoundedCornerShape(9.dp))
            .padding(start = 16.dp, end = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "当前账号为游客账号，无法在其他设备登录，请完成注册以确保账号的安全性",
            modifier = Modifier.weight(1f),
            color = Color(0xFF15171C),
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 22.sp
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("立刻注册", color = Color(0xFF8A5CE9), fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Spacer(Modifier.width(7.dp))
            CircleArrow(Color(0xFFD3C4F8), Color(0xFF8658EB), Modifier.size(17.dp))
        }
    }
}

@Composable
private fun ShortcutPanel() {
    Row(
        modifier = Modifier
            .padding(horizontal = 9.dp)
            .padding(top = 12.dp)
            .fillMaxWidth()
            .height(70.dp)
            .background(Color.White, RoundedCornerShape(10.dp))
            .padding(horizontal = 22.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ShortcutItem("月卡", IconKind.Card)
        ShortcutItem("VIP", IconKind.Crown)
        ShortcutItem("充值", IconKind.Gold)
        ShortcutItem("订阅", IconKind.Gem)
        ShortcutItem("礼包", IconKind.Gift)
    }
}

@Composable
private fun ShortcutItem(label: String, kind: IconKind) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        ColorIcon(kind = kind, modifier = Modifier.size(27.dp))
        Spacer(Modifier.height(8.dp))
        Text(label, color = Color(0xFF46464A), fontSize = 13.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun MenuPanel() {
    Column(
        modifier = Modifier
            .padding(horizontal = 9.dp)
            .padding(top = 12.dp)
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .padding(top = 14.dp)
    ) {
        val rows = listOf(
            MenuItemData("活动中心", IconKind.Flag, true),
            MenuItemData("背包", IconKind.Bag, false),
            MenuItemData("夜间模式", IconKind.Moon, false),
            MenuItemData("视频", IconKind.Video, false),
            MenuItemData("官方社媒", IconKind.Social, false),
            MenuItemData("官网", IconKind.Globe, true),
            MenuItemData("收件箱", IconKind.Mail, false),
            MenuItemData("设置", IconKind.Gear, true),
            MenuItemData("帮助", IconKind.Help, false)
        )
        rows.forEach { item ->
            MenuRow(item)
        }
    }
}

@Composable
private fun MenuRow(item: MenuItemData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 25.dp, end = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ColorIcon(kind = item.kind, modifier = Modifier.size(27.dp))
        Spacer(Modifier.width(22.dp))
        Box(contentAlignment = Alignment.CenterStart) {
            Text(item.title, color = Color(0xFF686868), fontSize = 16.sp, fontWeight = FontWeight.Medium)
            if (item.hasDot) {
                Box(
                    modifier = Modifier
                        .padding(start = if (item.title.length <= 2) 52.dp else 70.dp)
                        .size(9.dp)
                        .background(Color(0xFFFF3293), CircleShape)
                )
            }
        }
        Spacer(Modifier.weight(1f))
        Chevron(Modifier.size(24.dp), color = Color(0xFFD3D3D3))
    }
}

@Composable
private fun BottomNavigationBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(62.dp)
            .background(Color.White.copy(alpha = 0.97f))
            .padding(horizontal = 25.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BottomIcon(BottomKind.Home, selected = false, dot = false)
        BottomIcon(BottomKind.Face, selected = false, dot = false)
        BottomIcon(BottomKind.Shirt, selected = false, dot = true)
        BottomIcon(BottomKind.Chat, selected = false, dot = false)
        BottomIcon(BottomKind.Profile, selected = true, dot = true)
    }
}

@Composable
private fun BottomIcon(kind: BottomKind, selected: Boolean, dot: Boolean) {
    Box(
        modifier = Modifier.size(width = 42.dp, height = 45.dp),
        contentAlignment = Alignment.Center
    ) {
        if (selected) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .size(width = 34.dp, height = 14.dp)
                    .background(Color(0xFF7E50F0).copy(alpha = .22f), CircleShape)
            )
        }
        Canvas(Modifier.size(33.dp)) {
            val icon = if (selected) Color(0xFF874EF0) else Color(0xFFB1B2B6)
            val stroke = Stroke(width = 4.2f, cap = StrokeCap.Round)
            when (kind) {
                BottomKind.Home -> {
                    val path = Path().apply {
                        moveTo(size.width * .18f, size.height * .52f)
                        lineTo(size.width * .50f, size.height * .24f)
                        lineTo(size.width * .82f, size.height * .52f)
                        lineTo(size.width * .82f, size.height * .82f)
                        lineTo(size.width * .21f, size.height * .82f)
                        close()
                    }
                    drawPath(path, icon, style = stroke)
                    drawLine(icon, Offset(size.width * .50f, size.height * .64f), Offset(size.width * .50f, size.height * .82f), strokeWidth = 4.2f, cap = StrokeCap.Round)
                }
                BottomKind.Face -> {
                    drawRoundRect(icon, topLeft = Offset(size.width * .17f, size.height * .18f), size = Size(size.width * .66f, size.height * .66f), cornerRadius = CornerRadius(8f, 8f), style = stroke)
                    drawCircle(icon, 2.3f, Offset(size.width * .38f, size.height * .48f))
                    drawCircle(icon, 2.3f, Offset(size.width * .62f, size.height * .48f))
                    drawArc(icon, 205f, 130f, false, topLeft = Offset(size.width * .34f, size.height * .54f), size = Size(size.width * .32f, size.height * .22f), style = Stroke(3.2f, cap = StrokeCap.Round))
                }
                BottomKind.Shirt -> {
                    val path = Path().apply {
                        moveTo(size.width * .27f, size.height * .22f)
                        cubicTo(size.width * .36f, size.height * .35f, size.width * .64f, size.height * .35f, size.width * .73f, size.height * .22f)
                        lineTo(size.width * .90f, size.height * .38f)
                        lineTo(size.width * .76f, size.height * .54f)
                        lineTo(size.width * .72f, size.height * .83f)
                        lineTo(size.width * .28f, size.height * .83f)
                        lineTo(size.width * .24f, size.height * .54f)
                        lineTo(size.width * .10f, size.height * .38f)
                        close()
                    }
                    drawPath(path, icon, style = stroke)
                }
                BottomKind.Chat -> {
                    drawRoundRect(icon, topLeft = Offset(size.width * .16f, size.height * .20f), size = Size(size.width * .68f, size.height * .55f), cornerRadius = CornerRadius(18f, 18f), style = stroke)
                    drawLine(icon, Offset(size.width * .55f, size.height * .74f), Offset(size.width * .70f, size.height * .90f), strokeWidth = 4.2f, cap = StrokeCap.Round)
                    drawLine(icon, Offset(size.width * .38f, size.height * .43f), Offset(size.width * .38f, size.height * .56f), strokeWidth = 3f, cap = StrokeCap.Round)
                    drawLine(icon, Offset(size.width * .58f, size.height * .38f), Offset(size.width * .58f, size.height * .58f), strokeWidth = 3f, cap = StrokeCap.Round)
                }
                BottomKind.Profile -> {
                    drawRoundRect(icon, topLeft = Offset(size.width * .15f, size.height * .14f), size = Size(size.width * .70f, size.height * .70f), cornerRadius = CornerRadius(12f, 12f))
                    drawCircle(Color.White, 2.4f, Offset(size.width * .36f, size.height * .45f))
                    drawCircle(Color.White, 2.4f, Offset(size.width * .62f, size.height * .45f))
                    drawLine(Color.White, Offset(size.width * .43f, size.height * .63f), Offset(size.width * .57f, size.height * .58f), strokeWidth = 3.3f, cap = StrokeCap.Round)
                    drawCircle(Color(0xFFFFF15C), 2.2f, Offset(size.width * .82f, size.height * .25f))
                    drawLine(Color(0xFFFFF15C), Offset(size.width * .82f, size.height * .15f), Offset(size.width * .82f, size.height * .35f), strokeWidth = 2.5f, cap = StrokeCap.Round)
                    drawLine(Color(0xFFFFF15C), Offset(size.width * .72f, size.height * .25f), Offset(size.width * .92f, size.height * .25f), strokeWidth = 2.5f, cap = StrokeCap.Round)
                }
            }
        }
        if (dot) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(9.dp)
                    .background(Color(0xFFFF3293), CircleShape)
            )
        }
    }
}

@Composable
private fun ColorIcon(kind: IconKind, modifier: Modifier = Modifier) {
    Canvas(modifier) {
        when (kind) {
            IconKind.Card -> {
                drawRoundRect(Color(0xFFB65CF1), size = Size(size.width * .82f, size.height * .56f), topLeft = Offset(size.width * .08f, size.height * .25f), cornerRadius = CornerRadius(5f, 5f))
                drawRoundRect(Color(0xFFF44CA6), size = Size(size.width * .64f, size.height * .16f), topLeft = Offset(size.width * .16f, size.height * .36f), cornerRadius = CornerRadius(3f, 3f))
                drawCircle(Color(0xFFFFC433), size.width * .15f, Offset(size.width * .78f, size.height * .68f))
            }
            IconKind.Crown -> {
                val path = Path().apply {
                    moveTo(size.width * .14f, size.height * .72f)
                    lineTo(size.width * .22f, size.height * .36f)
                    lineTo(size.width * .42f, size.height * .58f)
                    lineTo(size.width * .50f, size.height * .20f)
                    lineTo(size.width * .60f, size.height * .58f)
                    lineTo(size.width * .80f, size.height * .36f)
                    lineTo(size.width * .88f, size.height * .72f)
                    close()
                }
                drawPath(path, Brush.verticalGradient(listOf(Color(0xFFFFF247), Color(0xFFFFB300))))
                drawRoundRect(Color(0xFFFFC51E), topLeft = Offset(size.width * .18f, size.height * .69f), size = Size(size.width * .64f, size.height * .16f), cornerRadius = CornerRadius(5f, 5f))
            }
            IconKind.Gold -> {
                repeat(3) { index ->
                    drawRoundRect(Color(0xFFFFD342), topLeft = Offset(size.width * (.18f + index * .18f), size.height * .50f), size = Size(size.width * .22f, size.height * .22f), cornerRadius = CornerRadius(3f, 3f))
                }
                drawRoundRect(Color(0xFFFFE268), topLeft = Offset(size.width * .34f, size.height * .26f), size = Size(size.width * .25f, size.height * .24f), cornerRadius = CornerRadius(3f, 3f))
                drawLine(Color(0xFFFFB000), Offset(size.width * .24f, size.height * .55f), Offset(size.width * .70f, size.height * .76f), strokeWidth = 2.3f)
            }
            IconKind.Gem -> {
                val path = Path().apply {
                    moveTo(size.width * .17f, size.height * .40f)
                    lineTo(size.width * .36f, size.height * .18f)
                    lineTo(size.width * .72f, size.height * .20f)
                    lineTo(size.width * .88f, size.height * .42f)
                    lineTo(size.width * .50f, size.height * .86f)
                    close()
                }
                drawPath(path, Brush.linearGradient(listOf(Color(0xFFFF76D3), Color(0xFFD629BC), Color(0xFFFF95EA))))
                drawLine(Color.White.copy(alpha = .75f), Offset(size.width * .56f, size.height * .28f), Offset(size.width * .70f, size.height * .43f), strokeWidth = 3f, cap = StrokeCap.Round)
            }
            IconKind.Gift -> {
                drawRoundRect(Color(0xFFFF64BD), topLeft = Offset(size.width * .18f, size.height * .42f), size = Size(size.width * .64f, size.height * .40f), cornerRadius = CornerRadius(5f, 5f))
                drawRoundRect(Color(0xFFFFC334), topLeft = Offset(size.width * .43f, size.height * .38f), size = Size(size.width * .15f, size.height * .45f), cornerRadius = CornerRadius(3f, 3f))
                drawCircle(Color(0xFFB665F2), size.width * .13f, Offset(size.width * .36f, size.height * .32f))
                drawCircle(Color(0xFFB665F2), size.width * .13f, Offset(size.width * .64f, size.height * .32f))
            }
            IconKind.Flag -> {
                drawRoundRect(Color(0xFFFF5D70), topLeft = Offset(size.width * .18f, size.height * .12f), size = Size(size.width * .22f, size.height * .74f), cornerRadius = CornerRadius(3f, 3f))
                val path = Path().apply {
                    moveTo(size.width * .37f, size.height * .16f)
                    lineTo(size.width * .83f, size.height * .16f)
                    lineTo(size.width * .67f, size.height * .38f)
                    lineTo(size.width * .83f, size.height * .58f)
                    lineTo(size.width * .37f, size.height * .58f)
                    close()
                }
                drawPath(path, Color(0xFFF35462))
            }
            IconKind.Bag -> {
                drawRoundRect(Color(0xFFF47B43), topLeft = Offset(size.width * .18f, size.height * .30f), size = Size(size.width * .64f, size.height * .52f), cornerRadius = CornerRadius(9f, 9f))
                drawArc(Color(0xFFFFC49D), 200f, 140f, false, topLeft = Offset(size.width * .33f, size.height * .12f), size = Size(size.width * .34f, size.height * .35f), style = Stroke(4f, cap = StrokeCap.Round))
                drawLine(Color.White.copy(.65f), Offset(size.width * .30f, size.height * .50f), Offset(size.width * .70f, size.height * .50f), strokeWidth = 3f, cap = StrokeCap.Round)
            }
            IconKind.Moon -> {
                drawCircle(Color(0xFF8059CF), size.width * .34f, Offset(size.width * .48f, size.height * .52f))
                drawCircle(Color.White, size.width * .33f, Offset(size.width * .35f, size.height * .37f))
                drawCircle(Color(0xFF8059CF), size.width * .06f, Offset(size.width * .20f, size.height * .26f))
            }
            IconKind.Video -> {
                drawRoundRect(Color(0xFF5A8FF8), topLeft = Offset(size.width * .16f, size.height * .24f), size = Size(size.width * .52f, size.height * .52f), cornerRadius = CornerRadius(6f, 6f))
                val path = Path().apply {
                    moveTo(size.width * .42f, size.height * .38f)
                    lineTo(size.width * .42f, size.height * .62f)
                    lineTo(size.width * .58f, size.height * .50f)
                    close()
                }
                drawPath(path, Color.White)
                drawRoundRect(Color(0xFF6CA4FF), topLeft = Offset(size.width * .66f, size.height * .36f), size = Size(size.width * .22f, size.height * .28f), cornerRadius = CornerRadius(6f, 6f))
            }
            IconKind.Social -> {
                drawCircle(Color(0xFF9C55F1), size.width * .23f, Offset(size.width * .35f, size.height * .55f))
                drawCircle(Color(0xFF9C55F1), size.width * .18f, Offset(size.width * .62f, size.height * .45f))
                drawCircle(Color.White, size.width * .045f, Offset(size.width * .29f, size.height * .55f))
                drawCircle(Color.White, size.width * .045f, Offset(size.width * .43f, size.height * .55f))
                drawLine(Color(0xFFFF5BB5), Offset(size.width * .77f, size.height * .25f), Offset(size.width * .77f, size.height * .70f), strokeWidth = 3.4f, cap = StrokeCap.Round)
                drawCircle(Color(0xFFFF5BB5), size.width * .07f, Offset(size.width * .86f, size.height * .69f))
            }
            IconKind.Globe -> {
                drawCircle(Color(0xFF5D91F5), size.width * .37f, Offset(size.width * .50f, size.height * .50f))
                drawLine(Color.White.copy(.75f), Offset(size.width * .17f, size.height * .50f), Offset(size.width * .83f, size.height * .50f), strokeWidth = 2.6f)
                drawLine(Color.White.copy(.75f), Offset(size.width * .50f, size.height * .15f), Offset(size.width * .50f, size.height * .85f), strokeWidth = 2.6f)
                drawArc(Color.White.copy(.75f), 75f, 210f, false, topLeft = Offset(size.width * .28f, size.height * .15f), size = Size(size.width * .44f, size.height * .70f), style = Stroke(2.4f))
            }
            IconKind.Mail -> {
                drawRoundRect(Color(0xFF4EC7DD), topLeft = Offset(size.width * .16f, size.height * .24f), size = Size(size.width * .68f, size.height * .54f), cornerRadius = CornerRadius(7f, 7f))
                drawLine(Color.White, Offset(size.width * .28f, size.height * .43f), Offset(size.width * .50f, size.height * .58f), strokeWidth = 3.3f, cap = StrokeCap.Round)
                drawLine(Color.White, Offset(size.width * .72f, size.height * .43f), Offset(size.width * .50f, size.height * .58f), strokeWidth = 3.3f, cap = StrokeCap.Round)
            }
            IconKind.Gear -> {
                drawCircle(Color(0xFF8798BF), size.width * .32f, Offset(size.width * .50f, size.height * .50f))
                repeat(8) { index ->
                    val angle = Math.toRadians((index * 45).toDouble())
                    val cx = size.width * .50f + kotlin.math.cos(angle).toFloat() * size.width * .32f
                    val cy = size.height * .50f + kotlin.math.sin(angle).toFloat() * size.height * .32f
                    drawRoundRect(Color(0xFF8798BF), Offset(cx - 3.5f, cy - 3.5f), Size(7f, 7f), CornerRadius(2f, 2f))
                }
                drawCircle(Color.White, size.width * .12f, Offset(size.width * .50f, size.height * .50f))
            }
            IconKind.Help -> {
                drawCircle(Color(0xFF5BC58D), size.width * .37f, Offset(size.width * .50f, size.height * .50f))
                drawContext.canvas.nativeCanvas.drawText("?", size.width * .40f, size.height * .66f, android.graphics.Paint().apply {
                    color = android.graphics.Color.WHITE
                    textSize = size.width * .70f
                    isFakeBoldText = true
                })
            }
        }
    }
}

@Composable
private fun MiniTicketIcon() {
    ColorIcon(IconKind.Card, Modifier.size(22.dp))
}

@Composable
private fun MiniGoldIcon() {
    ColorIcon(IconKind.Gold, Modifier.size(22.dp))
}

@Composable
private fun CircleArrow(background: Color, arrow: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(background, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Chevron(Modifier.size(13.dp), arrow)
    }
}

@Composable
private fun Chevron(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier) {
        drawLine(color, Offset(size.width * .35f, size.height * .25f), Offset(size.width * .65f, size.height * .50f), strokeWidth = 4f, cap = StrokeCap.Round)
        drawLine(color, Offset(size.width * .65f, size.height * .50f), Offset(size.width * .35f, size.height * .75f), strokeWidth = 4f, cap = StrokeCap.Round)
    }
}

private data class MenuItemData(
    val title: String,
    val kind: IconKind,
    val hasDot: Boolean
)

private enum class IconKind {
    Card,
    Crown,
    Gold,
    Gem,
    Gift,
    Flag,
    Bag,
    Moon,
    Video,
    Social,
    Globe,
    Mail,
    Gear,
    Help
}

private enum class BottomKind {
    Home,
    Face,
    Shirt,
    Chat,
    Profile
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun ProfileScreenPreview() {
    BlackmangoTheme(dynamicColor = false) {
        ProfileScreen()
    }
}

package com.superwindcloud.blackmango.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChangeHistory
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.superwindcloud.blackmango.ui.navigation.ScreenBackground

@Composable
fun SocialTabPage(modifier: Modifier = Modifier, onNavigate: (String) -> Unit = {}) {
    Column(modifier = modifier.fillMaxSize().background(ScreenBackground)) {
        SocialTopTabs(onNavigate = onNavigate)
        FeaturedActivity(onClick = { onNavigate("精彩活动") })
        Box(
            modifier =
                Modifier.fillMaxWidth()
                    .weight(1f)
                    .background(Color(0xFFF6F7F9))
                    .padding(bottom = 80.dp),
            contentAlignment = Alignment.Center,
        ) {
            EmptyChatState()
        }
    }
}

@Composable
private fun SocialTopTabs(onNavigate: (String) -> Unit) {
    var selectedLabel by remember { mutableStateOf("聊天") }

    Row(
        modifier =
            Modifier.fillMaxWidth()
                .height(72.dp)
                .background(Color(0xFFF2F2F4))
                .padding(horizontal = 18.dp),
        horizontalArrangement = Arrangement.spacedBy(26.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SocialTabItem(
            "聊天",
            Icons.Filled.ChatBubbleOutline,
            selected = selectedLabel == "聊天",
            onClick = {
                selectedLabel = "聊天"
                onNavigate("聊天")
            },
        )
        SocialTabItem(
            "好友",
            Icons.Filled.Groups,
            selected = selectedLabel == "好友",
            onClick = {
                selectedLabel = "好友"
                onNavigate("好友")
            },
        )
        SocialTabItem(
            "派对",
            Icons.Filled.Flag,
            selected = selectedLabel == "派对",
            onClick = {
                selectedLabel = "派对"
                onNavigate("派对")
            },
        )
        SocialTabItem(
            "部落",
            Icons.Filled.ChangeHistory,
            selected = selectedLabel == "部落",
            onClick = {
                selectedLabel = "部落"
                onNavigate("部落")
            },
        )
    }
}

@Composable
private fun SocialTabItem(
    label: String,
    icon: ImageVector,
    selected: Boolean = false,
    onClick: () -> Unit,
) {
    val color = if (selected) Color(0xFF8E45E9) else Color(0xFF9B9CA1)
    Column(
        modifier = Modifier.width(42.dp).clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(3.dp),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(28.dp),
            tint = color,
        )
        Text(
            text = label,
            color = color,
            fontSize = 13.sp,
            lineHeight = 16.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
        )
    }
}

@Composable
private fun FeaturedActivity(onClick: () -> Unit) {
    Row(
        modifier =
            Modifier.fillMaxWidth()
                .height(102.dp)
                .background(Color.White)
                .border(width = 0.5.dp, color = Color(0xFFE5E5E7))
                .clickable(onClick = onClick)
                .padding(start = 18.dp, end = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier =
                Modifier.size(58.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(Color(0xFFFFD5F4), Color(0xFFF1B9FF), Color(0xFFFFECF8))
                        )
                    ),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Filled.Flag,
                contentDescription = null,
                modifier = Modifier.size(34.dp),
                tint = Color(0xFFB84CFA),
            )
        }
        Spacer(Modifier.width(18.dp))
        Text(
            text = "精彩活动",
            color = Color(0xFF24262B),
            fontSize = 18.sp,
            lineHeight = 22.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
private fun EmptyChatState() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.size(132.dp), contentAlignment = Alignment.Center) {
            Box(modifier = Modifier.size(110.dp).clip(CircleShape).background(Color(0xFFFFF36C)))
            Box(
                modifier =
                    Modifier.align(Alignment.TopStart)
                        .offset(x = 8.dp, y = 12.dp)
                        .width(54.dp)
                        .height(38.dp)
                        .clip(RoundedCornerShape(11.dp))
                        .background(Color.White),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color(0xFF9B9B9D),
                )
            }
            BlockAvatar(modifier = Modifier.align(Alignment.Center).offset(x = 14.dp, y = 18.dp))
        }
        Text(
            text = "暂无聊天信息",
            color = Color(0xFFA9A9AD),
            fontSize = 16.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
private fun BlockAvatar(modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(width = 88.dp, height = 82.dp)) {
        Box(
            modifier =
                Modifier.align(Alignment.BottomCenter)
                    .width(72.dp)
                    .height(52.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFFC8978E))
        )
        Box(
            modifier =
                Modifier.align(Alignment.TopCenter)
                    .width(82.dp)
                    .height(32.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFFFFC331))
        )
        Box(
            modifier =
                Modifier.align(Alignment.TopStart)
                    .offset(x = 8.dp, y = 22.dp)
                    .size(width = 30.dp, height = 22.dp)
                    .background(Color(0xFFEFA923))
        )
        Box(
            modifier =
                Modifier.align(Alignment.TopEnd)
                    .offset(x = (-2).dp, y = 24.dp)
                    .size(width = 28.dp, height = 20.dp)
                    .background(Color(0xFFEFA923))
        )
        Box(
            modifier =
                Modifier.align(Alignment.CenterStart)
                    .offset(x = 18.dp, y = 14.dp)
                    .size(width = 16.dp, height = 9.dp)
                    .background(Color(0xFF64D8FF))
        )
        Box(
            modifier =
                Modifier.align(Alignment.CenterEnd)
                    .offset(x = (-16).dp, y = 14.dp)
                    .size(width = 16.dp, height = 9.dp)
                    .background(Color(0xFF64D8FF))
        )
        Box(
            modifier =
                Modifier.align(Alignment.BottomCenter)
                    .offset(y = (-16).dp)
                    .size(width = 18.dp, height = 3.dp)
                    .background(Color(0xFF6F4649))
        )
    }
}

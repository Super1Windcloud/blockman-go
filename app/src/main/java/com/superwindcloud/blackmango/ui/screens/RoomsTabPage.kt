package com.superwindcloud.blackmango.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.SignalCellularAlt
import androidx.compose.material.icons.filled.SportsKabaddi
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.superwindcloud.blackmango.R
import com.superwindcloud.blackmango.ui.navigation.ScreenBackground

@Composable
fun RoomsTabPage(modifier: Modifier = Modifier) {
    var section by remember { mutableStateOf(RoomsSection.Rooms) }

    Box(modifier = modifier.fillMaxSize().background(ScreenBackground)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 12.dp, top = 12.dp, end = 12.dp, bottom = 90.dp),
            verticalArrangement = Arrangement.spacedBy(7.dp),
        ) {
            item { RoomsHeader(selectedSection = section, onSectionSelected = { section = it }) }
            if (section == RoomsSection.Rooms) {
                item { RoomCategoryTabs() }
                items(roomCards) { room -> RoomListCard(room = room) }
            } else {
                item { WorkshopTabs() }
                workshopCards.chunked(2).forEach { rowCards ->
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            rowCards.forEach { card ->
                                WorkshopCard(card = card, modifier = Modifier.weight(1f))
                            }
                            if (rowCards.size == 1) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = {},
            modifier =
                Modifier.align(Alignment.BottomEnd)
                    .padding(end = 24.dp, bottom = 82.dp)
                    .size(52.dp),
            shape = CircleShape,
            containerColor = Purple,
            contentColor = Color.White,
        ) {
            Icon(Icons.Filled.Add, contentDescription = "创建", modifier = Modifier.size(30.dp))
        }
    }
}

@Composable
private fun RoomsHeader(selectedSection: RoomsSection, onSectionSelected: (RoomsSection) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RoomsHeaderTab(
                text = "房间",
                selected = selectedSection == RoomsSection.Rooms,
                onClick = { onSectionSelected(RoomsSection.Rooms) },
            )
            RoomsHeaderTab(
                text = "创意工坊",
                selected = selectedSection == RoomsSection.Workshop,
                onClick = { onSectionSelected(RoomsSection.Workshop) },
            )
        }
        Icon(
            Icons.Outlined.Inventory2,
            contentDescription = "收纳箱",
            tint = Color(0xFF202124),
            modifier = Modifier.size(22.dp),
        )
    }
}

@Composable
private fun RoomsHeaderTab(text: String, selected: Boolean, onClick: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier.clickable(onClick = onClick),
        color = if (selected) Color(0xFF17191D) else Color(0xFF85878D),
        fontSize = 17.sp,
        fontWeight = FontWeight.Black,
    )
}

@Composable
private fun RoomCategoryTabs() {
    val labels = listOf("全部", "UGC", "挖矿", "创造", "PVP", "社交", "游戏", "最近游玩")
    Row(
        modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(13.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        labels.forEachIndexed { index, label ->
            Text(
                text = label,
                modifier =
                    if (index == 0) {
                        Modifier.background(Color(0xFFE8E9ED), RoundedCornerShape(20.dp))
                            .padding(horizontal = 11.dp, vertical = 6.dp)
                    } else {
                        Modifier.padding(vertical = 6.dp)
                    },
                color = if (index == 0) Color(0xFF17191D) else Color(0xFF8B8D93),
                fontSize = 15.sp,
                fontWeight = if (index == 0) FontWeight.Bold else FontWeight.Medium,
                maxLines = 1,
            )
        }
    }
}

@Composable
private fun WorkshopTabs() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "创意征集",
            modifier =
                Modifier.background(Color(0xFFE8E9ED), RoundedCornerShape(20.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp),
            color = Color(0xFF17191D),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "热门作品",
            color = Color(0xFF8B8D93),
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
private fun RoomListCard(room: RoomCard) {
    Card(
        modifier = Modifier.fillMaxWidth().height(100.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(room.backgroundRes),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight().width(200.dp),
                contentScale = ContentScale.Crop,
                alpha = 0.2f,
            )
            Box(
                modifier =
                    Modifier.align(Alignment.CenterEnd)
                        .fillMaxHeight()
                        .width(200.dp)
                        .background(Color(0x88FFFFFF))
            )
            Row(
                modifier = Modifier.fillMaxSize().padding(horizontal = 9.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(room.avatarRes),
                    contentDescription = null,
                    modifier = Modifier.size(56.dp).clip(RoundedCornerShape(6.dp)),
                    contentScale = ContentScale.Crop,
                )
                Column(
                    modifier = Modifier.padding(start = 9.dp).weight(1f).fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column {
                        Text(
                            text = room.title,
                            color = Color(0xFF202124),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Text(
                            text = "房主:${room.host}",
                            color = Color(0xFF8D8F96),
                            fontSize = 13.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RoomMeta(icon = room.typeIcon, label = room.type)
                        RoomMeta(icon = Icons.Filled.Public, label = room.language)
                        Icon(
                            Icons.Filled.SignalCellularAlt,
                            contentDescription = "信号",
                            tint = Color(0xFFFFB300),
                            modifier = Modifier.size(16.dp),
                        )
                    }
                }
                CapacityMeter(current = room.current, total = room.total)
            }
        }
    }
}

@Composable
private fun RoomMeta(icon: ImageVector, label: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color(0xFF8A8D93),
            modifier = Modifier.size(13.dp),
        )
        Text(
            text = label,
            color = Color(0xFF8A8D93),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun CapacityMeter(current: Int, total: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.Bottom) {
            Text("$current", color = Purple, fontSize = 14.sp, fontWeight = FontWeight.Black)
            Text(
                " / $total",
                color = Color(0xFF666A72),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Box(
            modifier =
                Modifier.padding(top = 3.dp)
                    .width(52.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color(0xFFC0C4CA))
        ) {
            Box(
                modifier =
                    Modifier.fillMaxHeight()
                        .fillMaxWidth(current / total.toFloat())
                        .background(Purple, RoundedCornerShape(5.dp))
            )
        }
    }
}

@Composable
private fun WorkshopCard(card: WorkshopCard, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(7.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Column {
            Box {
                Image(
                    painter = painterResource(card.imageRes),
                    contentDescription = card.title,
                    modifier = Modifier.fillMaxWidth().aspectRatio(1f),
                    contentScale = ContentScale.Crop,
                )
                Row(
                    modifier = Modifier.align(Alignment.BottomEnd).padding(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(3.dp),
                ) {
                    card.badges.forEach { badge -> WorkshopBadge(badge) }
                }
            }
            Text(
                text = card.title,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 7.dp),
                color = Color(0xFF2D3035),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun WorkshopBadge(text: String) {
    Text(
        text = text,
        modifier =
            Modifier.background(Color(0x99000000), RoundedCornerShape(3.dp))
                .padding(horizontal = 5.dp, vertical = 2.dp),
        color = Color.White,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
    )
}

private enum class RoomsSection {
    Rooms,
    Workshop,
}

private data class RoomCard(
    val title: String,
    val host: String,
    @DrawableRes val avatarRes: Int,
    @DrawableRes val backgroundRes: Int,
    val type: String,
    val typeIcon: ImageVector,
    val language: String,
    val current: Int,
    val total: Int = 24,
)

private data class WorkshopCard(
    val title: String,
    @DrawableRes val imageRes: Int,
    val badges: List<String>,
)

private val Purple = Color(0xFF844AF8)

private val roomCards =
    listOf(
        RoomCard(
            title = "ONLY 1 SMP / APENAS 1 SMP",
            host = "IGOR261",
            avatarRes = R.drawable.room_avatar_dark_skin,
            backgroundRes = R.drawable.card_reco_sky_survival,
            type = "PVP",
            typeIcon = Icons.Filled.SportsKabaddi,
            language = "Português",
            current = 22,
        ),
        RoomCard(
            title = "Help make a city",
            host = "dxmovec",
            avatarRes = R.drawable.room_avatar_blond_angry,
            backgroundRes = R.drawable.card_friends_school,
            type = "UGC",
            typeIcon = Icons.Filled.Extension,
            language = "English",
            current = 22,
        ),
        RoomCard(
            title = "Zombie Survival",
            host = "AnneLuke9275",
            avatarRes = R.drawable.room_avatar_blond_portrait,
            backgroundRes = R.drawable.card_friends_school,
            type = "UGC",
            typeIcon = Icons.Filled.Extension,
            language = "English",
            current = 20,
        ),
        RoomCard(
            title = "Яытсщдц 👍 🇺🇦 2 💉 OFF",
            host = "LucentErrant6440",
            avatarRes = R.drawable.room_bg_poster,
            backgroundRes = R.drawable.card_classic_biohazard,
            type = "创造",
            typeIcon = Icons.Filled.Brush,
            language = "русский",
            current = 19,
        ),
        RoomCard(
            title = "Find Friends to Play 🔫🔫🔫🔫",
            host = "LukeDee5163",
            avatarRes = R.drawable.room_avatar_white_hair,
            backgroundRes = R.drawable.room_bg_sheep_square,
            type = "社交",
            typeIcon = Icons.Filled.CheckBox,
            language = "English",
            current = 18,
        ),
        RoomCard(
            title = "SQUID GAME // wining fly gun ✈",
            host = "-_-Jewop-kims..",
            avatarRes = R.drawable.room_avatar_girl_dark,
            backgroundRes = R.drawable.card_pvp_squad_battle,
            type = "PVP",
            typeIcon = Icons.Filled.SportsKabaddi,
            language = "Український",
            current = 18,
        ),
        RoomCard(
            title = "Find Friends to Play",
            host = "tyduforgot",
            avatarRes = R.drawable.room_avatar_red_jacket,
            backgroundRes = R.drawable.room_bg_sheep,
            type = "社交",
            typeIcon = Icons.Filled.CheckBox,
            language = "English",
            current = 17,
        ),
        RoomCard(
            title = "PKH 2019",
            host = "tyduforgot",
            avatarRes = R.drawable.room_avatar_red_jacket,
            backgroundRes = R.drawable.room_bg_sheep,
            type = "社交",
            typeIcon = Icons.Filled.CheckBox,
            language = "English",
            current = 12,
        ),
    )

private val workshopCards =
    listOf(
        WorkshopCard("类似床战风格的游戏", R.drawable.card_reco_bed_wars, listOf("游戏", "房间")),
        WorkshopCard("学校主题地图", R.drawable.card_friends_school, listOf("地图", "房间")),
        WorkshopCard("竞技场主题地图", R.drawable.card_pvp_royal_war, listOf("地图", "房间")),
        WorkshopCard("中世纪城堡主题地图", R.drawable.card_reco_dragon_egg, listOf("地图", "房间")),
    )

package com.superwindcloud.blackmango.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.superwindcloud.blackmango.R
import com.superwindcloud.blackmango.ui.navigation.ScreenBackground

private data class HomeGameUi(val title: String, @DrawableRes val imageRes: Int, val score: String, val players: String)

private data class HomeCardSectionUi(val title: String, val games: List<HomeGameUi>)

private val homeCardSections =
    listOf(
        HomeCardSectionUi(
            "推荐游戏",
            listOf(
                HomeGameUi("动漫对决", R.drawable.card_reco_anime_duel, "92%", "1955"),
                HomeGameUi("动漫格斗模拟器", R.drawable.card_reco_anime_fighter, "91%", "1557"),
                HomeGameUi("警匪大战", R.drawable.card_reco_cops_robbers, "87%", "4396"),
                HomeGameUi("空岛生存", R.drawable.card_reco_sky_survival, "85%", "17.8k"),
                HomeGameUi("龙蛋领主", R.drawable.card_reco_dragon_egg, "91%", "1494"),
                HomeGameUi("起床战争", R.drawable.card_reco_bed_wars, "89%", "31.9k"),
            ),
        ),
        HomeCardSectionUi(
            "交朋友",
            listOf(
                HomeGameUi("交友大厅", R.drawable.card_friends_lobby, "90%", "9.8k"),
                HomeGameUi("天堂岛", R.drawable.card_friends_paradise, "91%", "6.8k"),
                HomeGameUi("学校模拟器", R.drawable.card_friends_school, "88%", "7.6k"),
            ),
        ),
        HomeCardSectionUi(
            "角色扮演",
            listOf(
                HomeGameUi("海岛生存", R.drawable.card_rpg_island, "91%", "7.4k"),
                HomeGameUi("神秘杀手", R.drawable.card_rpg_assassin, "86%", "5.3k"),
                HomeGameUi("幸存者", R.drawable.card_rpg_survivor, "89%", "8.1k"),
                HomeGameUi("战争前线", R.drawable.card_rpg_war_front, "88%", "6.9k"),
            ),
        ),
        HomeCardSectionUi(
            "经典游戏",
            listOf(
                HomeGameUi("大农场主", R.drawable.card_classic_farmer, "90%", "4.9k"),
                HomeGameUi("躲猫猫", R.drawable.card_classic_hide_seek, "92%", "10.1k"),
                HomeGameUi("饥饿游戏", R.drawable.card_classic_hunger, "91%", "11.7k"),
                HomeGameUi("生化危机", R.drawable.card_classic_biohazard, "87%", "5.4k"),
            ),
        ),
        HomeCardSectionUi(
            "恐怖游戏",
            listOf(
                HomeGameUi("恐怖驱魔杀", R.drawable.card_horror_exorcist, "89%", "4.9k"),
                HomeGameUi("迷雾逃生", R.drawable.card_horror_fog_escape, "86%", "4.1k"),
                HomeGameUi("校园惊魂夜", R.drawable.card_horror_school_night, "85%", "3.5k"),
                HomeGameUi("行尸走肉", R.drawable.card_horror_walking_dead, "88%", "5.8k"),
            ),
        ),
        HomeCardSectionUi(
            "射击游戏",
            listOf(
                HomeGameUi("方块入侵", R.drawable.card_shooter_block_invasion, "87%", "5.1k"),
                HomeGameUi("建造与射击", R.drawable.card_shooter_build_shoot, "90%", "6.2k"),
                HomeGameUi("异星入侵", R.drawable.card_shooter_alien, "86%", "4.7k"),
                HomeGameUi("罪恶都市", R.drawable.card_shooter_crime_city, "91%", "12.6k"),
            ),
        ),
        HomeCardSectionUi(
            "最新上线",
            listOf(
                HomeGameUi("暴力摩托", R.drawable.card_new_moto, "88%", "4.6k"),
                HomeGameUi("极限跑酷", R.drawable.card_new_parkour, "90%", "3.8k"),
                HomeGameUi("巨人", R.drawable.card_new_giant, "86%", "2.7k"),
                HomeGameUi("幸存者", R.drawable.card_new_survivor, "87%", "6.3k"),
            ),
        ),
        HomeCardSectionUi(
            "PVP",
            listOf(
                HomeGameUi("躲猫猫", R.drawable.card_pvp_hide_seek, "92%", "10.8k"),
                HomeGameUi("皇室战争", R.drawable.card_pvp_royal_war, "88%", "8.5k"),
                HomeGameUi("武道大会", R.drawable.card_pvp_martial, "89%", "7.2k"),
                HomeGameUi("Squad Battle", R.drawable.card_pvp_squad_battle, "86%", "6.4k"),
            ),
        ),
    )

@Composable
fun HomeTabPage(modifier: Modifier = Modifier, onNavigate: (String) -> Unit = {}) {
    LazyColumn(
        modifier = modifier.fillMaxSize().background(ScreenBackground),
        contentPadding = PaddingValues(start = 12.dp, top = 12.dp, end = 12.dp, bottom = 92.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item { HomeTopBar(onNavigate = onNavigate) }
        item { PromoStrip(onNavigate = onNavigate) }
        homeCardSections.forEach { section ->
            homeGameSection(
                title = section.title,
                games = section.games,
                columns = 3,
                horizontal = section.title != "推荐游戏",
                onNavigate = onNavigate,
            )
        }
    }
}

@Composable
private fun HomeTopBar(onNavigate: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(22.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CurrencyPill(
                icon = Icons.Filled.SportsEsports,
                tint = Color(0xFFFF5AB0),
                onClick = { onNavigate("游戏币") },
            )
            Spacer(Modifier.width(14.dp))
            CurrencyPill(
                icon = Icons.Filled.WorkspacePremium,
                tint = Color(0xFFE8B52A),
                onClick = { onNavigate("奖章") },
            )
            Spacer(Modifier.weight(1f))
            AlertGlyph(onClick = { onNavigate("靓号提醒") })
            Spacer(Modifier.width(24.dp))
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                tint = Color(0xFF8B34E8),
                modifier = Modifier.size(24.dp).clickable { onNavigate("搜索") },
            )
            Spacer(Modifier.width(24.dp))
            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = null,
                tint = Color(0xFFFF8C6B),
                modifier = Modifier.size(24.dp).clickable { onNavigate("通知") },
            )
        }
    }
}

@Composable
private fun CurrencyPill(icon: ImageVector, tint: Color, onClick: () -> Unit) {
    Row(
        modifier =
            Modifier.height(24.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFD1D1D5))
                .clickable(onClick = onClick)
                .padding(start = 8.dp, end = 0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Icon(icon, contentDescription = null, tint = tint, modifier = Modifier.size(14.dp))
        Text("0", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Black)
        Box(
            modifier = Modifier.size(24.dp).clip(CircleShape).background(Color(0xFF7F35EA)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(12.dp),
            )
        }
    }
}

@Composable
private fun AlertGlyph(onClick: () -> Unit) {
    Box(
        modifier = Modifier.size(24.dp).clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            Icons.Filled.ConfirmationNumber,
            contentDescription = null,
            tint = Color(0xFFF2436F),
            modifier = Modifier.size(23.dp),
        )
    }
}

@Composable
private fun PromoStrip(onNavigate: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ShortcutTile(
            title = "G-Cube",
            icon = Icons.Filled.WorkspacePremium,
            onClick = { onNavigate("G-Cube") },
        )
        ShortcutTile(title = "S9", icon = Icons.Filled.Star, onClick = { onNavigate("S9") })
        Box(
            modifier =
                Modifier.weight(1f)
                    .height(82.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF356FAE))
                    .clickable { onNavigate("累计充值") }
        ) {
            Image(
                painter = painterResource(R.drawable.card_reco_sky_survival),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier =
                    Modifier.matchParentSize()
                        .background(
                            Brush.horizontalGradient(
                                0f to Color(0xCC2D160A),
                                0.6f to Color(0x443C200C),
                                1f to Color(0x00000000),
                            )
                        )
            )
            Text(
                "累计充值",
                modifier = Modifier.align(Alignment.CenterStart).padding(start = 22.dp),
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
            )
            Box(
                modifier =
                    Modifier.align(Alignment.TopEnd)
                        .padding(top = 0.dp, end = 2.dp)
                        .size(26.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFF3E70)),
                contentAlignment = Alignment.Center,
            ) {
                Text("!", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Black)
            }
        }
    }
}

@Composable
private fun ShortcutTile(title: String, icon: ImageVector, onClick: () -> Unit) {
    Box(
        modifier =
            Modifier.size(72.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFF8A55F4), Color(0xFFFF73C7), Color(0xFFFFD948))
                    )
                )
                .clickable(onClick = onClick)
    ) {
        Text(
            title,
            modifier = Modifier.padding(start = 9.dp, top = 8.dp),
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Black,
        )
        Icon(
            icon,
            contentDescription = null,
            tint = Color.White.copy(alpha = 0.9f),
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 9.dp).size(32.dp),
        )
        Box(
            modifier =
                Modifier.align(Alignment.TopEnd)
                    .offset(x = 10.dp, y = (-10).dp)
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFF3E70)),
            contentAlignment = Alignment.Center,
        ) {
            Text("!", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Black)
        }
    }
}

private fun LazyListScope.homeGameSection(
    title: String,
    games: List<HomeGameUi>,
    columns: Int,
    horizontal: Boolean,
    onNavigate: (String) -> Unit,
) {
    item {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    title,
                    color = Color(0xFF171A20),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Black,
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    Icons.Filled.ChevronRight,
                    contentDescription = null,
                    tint = Color(0xFFB7B7B9),
                    modifier = Modifier.size(38.dp).clickable { onNavigate(title) },
                )
            }

            if (horizontal) {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(games.size) { index ->
                        HomeGameCard(
                            game = games[index],
                            modifier = Modifier.width(104.dp),
                            onClick = { onNavigate(games[index].title) },
                        )
                    }
                }
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
                    games.chunked(columns).forEach { rowGames ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            rowGames.forEach { game ->
                                HomeGameCard(
                                    game = game,
                                    modifier = Modifier.weight(1f),
                                    onClick = { onNavigate(game.title) },
                                )
                            }
                            repeat(columns - rowGames.size) { Spacer(Modifier.weight(1f)) }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeGameCard(game: HomeGameUi, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val playersText = game.players.withPlayerUnit()

    Column(
        modifier = modifier.clickable(onClick = onClick),
        verticalArrangement = Arrangement.spacedBy(7.dp),
    ) {
        Box(
            modifier =
                Modifier.fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(9.dp))
                    .background(Color.White)
        ) {
            Image(
                painter = painterResource(game.imageRes),
                contentDescription = game.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }
        Text(
            text = game.title,
            color = Color(0xFF1E2025),
            fontSize = 15.sp,
            fontWeight = FontWeight.Black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 18.sp,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp),
        ) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = null,
                tint = Color(0xFFFF3E70),
                modifier = Modifier.size(14.dp),
            )
            Text(
                game.score,
                color = Color(0xFF777A81),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                lineHeight = 14.sp,
            )
            Icon(
                Icons.Filled.Person,
                contentDescription = null,
                tint = Color(0xFF7D43E8),
                modifier = Modifier.size(14.dp),
            )
            Text(
                playersText,
                color = Color(0xFF777A81),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                lineHeight = 14.sp,
            )
        }
    }
}

private fun String.withPlayerUnit(): String =
    if (contains(".") && !endsWith("k", ignoreCase = true)) "${this}k" else this

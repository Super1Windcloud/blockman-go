package com.superwindcloud.blackmango.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
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

private data class HomeGameUi(
    val title: String,
    @DrawableRes val imageRes: Int,
    val score: String,
    val players: String,
    val checked: Boolean = false,
)

private val recommendedGames =
    listOf(
        HomeGameUi("起床战争", R.drawable.card_reco_bed_wars, "89%", "31.9k"),
        HomeGameUi("空岛生存", R.drawable.card_reco_sky_survival, "85%", "17.8k"),
        HomeGameUi("动漫格斗模拟器", R.drawable.card_reco_anime_fighter, "91%", "1557", true),
        HomeGameUi("龙蛋领主", R.drawable.card_reco_dragon_egg, "91%", "1494"),
        HomeGameUi("FREE CITY RP", R.drawable.card_shooter_crime_city, "91%", "12.6k", true),
        HomeGameUi("警匪大战", R.drawable.card_reco_cops_robbers, "87%", "4396", true),
        HomeGameUi("动漫对决", R.drawable.card_reco_anime_duel, "92%", "1955", true),
        HomeGameUi("训练家联盟", R.drawable.card_rpg_island, "91%", "1566", true),
        HomeGameUi("WWE学校模拟器", R.drawable.card_pvp_martial, "89%", "1221", true),
    )

private val friendGames =
    listOf(
        HomeGameUi("交友大厅", R.drawable.card_friends_lobby, "90%", "9.8k"),
        HomeGameUi("学校模拟器", R.drawable.card_friends_school, "88%", "7.6k", true),
        HomeGameUi("天堂岛", R.drawable.card_friends_paradise, "91%", "6.8k"),
        HomeGameUi("幸存者", R.drawable.card_new_survivor, "87%", "6.3k", true),
    )

@Composable
fun HomeTabPage(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize().background(ScreenBackground),
        contentPadding = PaddingValues(start = 12.dp, top = 12.dp, end = 12.dp, bottom = 92.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        item { HomeTopBar() }
        item { PromoStrip() }
        homeGameSection(title = "推荐游戏", games = recommendedGames, columns = 3)
        homeGameSection(title = "交朋友", games = friendGames, columns = 4)
    }
}

@Composable
private fun HomeTopBar() {
    Column(verticalArrangement = Arrangement.spacedBy(22.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CurrencyPill(icon = Icons.Filled.SportsEsports, tint = Color(0xFFFF5AB0))
            Spacer(Modifier.width(14.dp))
            CurrencyPill(icon = Icons.Filled.WorkspacePremium, tint = Color(0xFFE8B52A))
            Spacer(Modifier.weight(1f))
            AlertGlyph()
            Spacer(Modifier.width(24.dp))
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                tint = Color(0xFF8B34E8),
                modifier = Modifier.size(42.dp),
            )
            Spacer(Modifier.width(24.dp))
            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = null,
                tint = Color(0xFFFF8C6B),
                modifier = Modifier.size(36.dp),
            )
        }
    }
}

@Composable
private fun CurrencyPill(icon: ImageVector, tint: Color) {
    Row(
        modifier =
            Modifier.height(36.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFD1D1D5))
                .padding(start = 10.dp, end = 0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Icon(icon, contentDescription = null, tint = tint, modifier = Modifier.size(24.dp))
        Text("0", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Black)
        Box(
            modifier = Modifier.size(40.dp).clip(CircleShape).background(Color(0xFF7F35EA)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(30.dp),
            )
        }
    }
}

@Composable
private fun AlertGlyph() {
    Box(modifier = Modifier.size(48.dp), contentAlignment = Alignment.Center) {
        Text("靓", color = Color(0xFFF2436F), fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Box(
            modifier =
                Modifier.align(Alignment.TopEnd)
                    .size(22.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFF3E70)),
            contentAlignment = Alignment.Center,
        ) {
            Text("!", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Black)
        }
    }
}

@Composable
private fun PromoStrip() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ShortcutTile(title = "G-Cube", icon = Icons.Filled.WorkspacePremium)
        ShortcutTile(title = "S9", icon = Icons.Filled.Star)
        Box(
            modifier =
                Modifier.weight(1f)
                    .height(82.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF356FAE))
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
private fun ShortcutTile(title: String, icon: ImageVector) {
    Box(
        modifier =
            Modifier.size(72.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFF8A55F4), Color(0xFFFF73C7), Color(0xFFFFD948))
                    )
                )
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

private fun LazyListScope.homeGameSection(title: String, games: List<HomeGameUi>, columns: Int) {
    item {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(title, color = Color(0xFF171A20), fontSize = 24.sp, fontWeight = FontWeight.Black)
            Spacer(Modifier.weight(1f))
            Icon(
                Icons.Filled.ChevronRight,
                contentDescription = null,
                tint = Color(0xFFB7B7B9),
                modifier = Modifier.size(38.dp),
            )
        }
    }
    games.chunked(columns).forEach { rowGames ->
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                rowGames.forEach { game ->
                    HomeGameCard(game = game, modifier = Modifier.weight(1f))
                }
                repeat(columns - rowGames.size) { Spacer(Modifier.weight(1f)) }
            }
        }
    }
}

@Composable
private fun HomeGameCard(game: HomeGameUi, modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(7.dp)) {
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
            if (game.checked) {
                Box(
                    modifier =
                        Modifier.align(Alignment.TopStart)
                            .padding(7.dp)
                            .size(26.dp)
                            .clip(RoundedCornerShape(9.dp))
                            .background(Color(0xFFFF45C7)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(19.dp),
                    )
                }
            }
        }
        Text(
            text = game.title,
            color = Color(0xFF1E2025),
            fontSize = 18.sp,
            fontWeight = FontWeight.Black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 21.sp,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = null,
                tint = Color(0xFFFF3E70),
                modifier = Modifier.size(17.dp),
            )
            Text(
                game.score,
                color = Color(0xFF777A81),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
            )
            Spacer(Modifier.width(6.dp))
            Icon(
                Icons.Filled.Person,
                contentDescription = null,
                tint = Color(0xFF7D43E8),
                modifier = Modifier.size(17.dp),
            )
            Text(
                game.players,
                color = Color(0xFF777A81),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
            )
        }
    }
}

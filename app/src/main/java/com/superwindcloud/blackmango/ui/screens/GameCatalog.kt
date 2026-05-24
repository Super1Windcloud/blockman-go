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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.superwindcloud.blackmango.R
import com.superwindcloud.blackmango.ui.navigation.ScreenBackground

data class GameCard(
    val title: String,
    val tag: String,
    @DrawableRes val imageRes: Int,
    val online: String,
)

data class GameSection(val title: String, val games: List<GameCard>)

val homeSections =
    listOf(
        GameSection(
            "推荐游戏",
            listOf(
                GameCard("龙蛋领主", "冒险", R.drawable.card_reco_dragon_egg, "9.8k"),
                GameCard("起床战争", "PVP", R.drawable.card_reco_bed_wars, "12.4k"),
                GameCard("警匪大战", "对抗", R.drawable.card_reco_cops_robbers, "7.1k"),
                GameCard("空岛生存", "生存", R.drawable.card_reco_sky_survival, "5.9k"),
                GameCard("动漫格斗模拟器", "格斗", R.drawable.card_reco_anime_fighter, "8.2k"),
                GameCard("动漫对决", "竞技", R.drawable.card_reco_anime_duel, "6.6k"),
            ),
        ),
        GameSection(
            "最新上线",
            listOf(
                GameCard("极限跑酷", "跑酷", R.drawable.card_new_parkour, "3.8k"),
                GameCard("暴力摩托", "竞速", R.drawable.card_new_moto, "4.6k"),
                GameCard("幸存者", "生存", R.drawable.card_new_survivor, "6.3k"),
                GameCard("巨人", "动作", R.drawable.card_new_giant, "2.7k"),
            ),
        ),
        GameSection(
            "经典游戏",
            listOf(
                GameCard("饥饿游戏", "经典", R.drawable.card_classic_hunger, "11.7k"),
                GameCard("躲猫猫", "派对", R.drawable.card_classic_hide_seek, "10.1k"),
                GameCard("生化危机", "恐怖", R.drawable.card_classic_biohazard, "5.4k"),
                GameCard("大农场主", "经营", R.drawable.card_classic_farmer, "4.9k"),
            ),
        ),
    )

val categorySections =
    listOf(
        GameSection(
            "PVP",
            listOf(
                GameCard("躲猫猫", "派对", R.drawable.card_pvp_hide_seek, "10.8k"),
                GameCard("皇室战争", "策略", R.drawable.card_pvp_royal_war, "8.5k"),
                GameCard("武道大会", "格斗", R.drawable.card_pvp_martial, "7.2k"),
                GameCard("Squad Battle", "团队", R.drawable.card_pvp_squad_battle, "6.4k"),
            ),
        ),
        GameSection(
            "交朋友",
            listOf(
                GameCard("交友大厅", "社交", R.drawable.card_friends_lobby, "15.2k"),
                GameCard("天堂岛", "休闲", R.drawable.card_friends_paradise, "6.8k"),
                GameCard("学校模拟器", "模拟", R.drawable.card_friends_school, "7.6k"),
            ),
        ),
        GameSection(
            "射击游戏",
            listOf(
                GameCard("罪恶都市", "开放", R.drawable.card_shooter_crime_city, "9.5k"),
                GameCard("方块入侵", "射击", R.drawable.card_shooter_block_invasion, "5.1k"),
                GameCard("异星入侵", "科幻", R.drawable.card_shooter_alien, "4.7k"),
                GameCard("建造与射击", "沙盒", R.drawable.card_shooter_build_shoot, "6.2k"),
            ),
        ),
        GameSection(
            "角色扮演",
            listOf(
                GameCard("神秘杀手", "潜入", R.drawable.card_rpg_assassin, "5.3k"),
                GameCard("海岛生存", "冒险", R.drawable.card_rpg_island, "7.4k"),
                GameCard("战争前线", "战役", R.drawable.card_rpg_war_front, "6.9k"),
                GameCard("幸存者", "剧情", R.drawable.card_rpg_survivor, "8.1k"),
            ),
        ),
        GameSection(
            "恐怖游戏",
            listOf(
                GameCard("迷雾逃生", "解谜", R.drawable.card_horror_fog_escape, "4.1k"),
                GameCard("行尸走肉", "生存", R.drawable.card_horror_walking_dead, "5.8k"),
                GameCard("校园惊魂夜", "剧情", R.drawable.card_horror_school_night, "3.5k"),
                GameCard("恐怖驱魔杀", "合作", R.drawable.card_horror_exorcist, "4.9k"),
            ),
        ),
    )

@Composable
fun ScrollableTabPage(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    content: LazyListScope.() -> Unit,
) {
    LazyColumn(
        modifier = modifier.background(ScreenBackground),
        contentPadding = PaddingValues(start = 16.dp, top = 18.dp, end = 16.dp, bottom = 104.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item { PageHeader(title = title, subtitle = subtitle) }
        content()
    }
}

@Composable
fun PageHeader(title: String, subtitle: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Black,
                color = Color(0xFF14181F),
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF747B86),
            )
        }
        Box(
            modifier = Modifier.size(42.dp).clip(CircleShape).background(Color(0xFF111827)),
            contentAlignment = Alignment.Center,
        ) {
            Text("BM", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

fun LazyListScope.gameSection(section: GameSection) {
    item {
        Text(
            text = section.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242C),
        )
    }
    section.games.chunked(2).forEach { rowGames ->
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                rowGames.forEach { game ->
                    GameCardItem(game = game, modifier = Modifier.weight(1f))
                }
                if (rowGames.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun GameCardItem(game: GameCard, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Box {
            Image(
                painter = painterResource(game.imageRes),
                contentDescription = game.title,
                modifier = Modifier.fillMaxWidth().aspectRatio(1.45f),
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier =
                    Modifier.matchParentSize()
                        .background(
                            Brush.verticalGradient(
                                0.35f to Color.Transparent,
                                1f to Color(0x99000000),
                            )
                        )
            )
            Column(
                modifier = Modifier.align(Alignment.BottomStart).padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = game.title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = "${game.tag} · ${game.online} 在线",
                    color = Color(0xFFE6EDF6),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                )
            }
        }
    }
}

@Composable
fun HeroGameCard(game: GameCard, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF151A22)),
    ) {
        Box {
            Image(
                painter = painterResource(game.imageRes),
                contentDescription = game.title,
                modifier = Modifier.fillMaxWidth().height(188.dp),
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier =
                    Modifier.matchParentSize()
                        .background(
                            Brush.horizontalGradient(
                                0f to Color(0xDD000000),
                                0.72f to Color(0x22000000),
                            )
                        )
            )
            Column(
                modifier = Modifier.align(Alignment.BottomStart).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text("今日热门", color = Color(0xFFFFD166), fontWeight = FontWeight.Bold)
                Text(
                    text = game.title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Black,
                )
                Text(
                    text = "${game.tag} · ${game.online} 在线",
                    color = Color(0xFFE8EDF4),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

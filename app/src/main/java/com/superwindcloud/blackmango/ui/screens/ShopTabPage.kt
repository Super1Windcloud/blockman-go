package com.superwindcloud.blackmango.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Cached
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.Checkroom
import androidx.compose.material.icons.rounded.Expand
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material.icons.rounded.Pets
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.sceneview.SceneView
import io.github.sceneview.gesture.CameraGestureDetector
import io.github.sceneview.math.Transform
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberModelLoader

private val shopCategories = listOf("推荐", "服饰", "配饰", "人物", "脸部")
private val shopFilters = listOf("推荐物品", "新品上架")
private val modelPreviewBackground = Color(0xFFE8E4FF)

private val shopItems =
    listOf(
        ShopItem("女仆套装", 120, ShopItemType.Dress, Color(0xFFFCFCFF), Color(0xFF202126)),
        ShopItem("海岛短裤", 120, ShopItemType.Bottoms, Color(0xFF4CB3C6), Color(0xFF3D7A95)),
        ShopItem("夏日衬衫", 120, ShopItemType.Top, Color(0xFF113A52), Color(0xFFFFF8EB)),
        ShopItem("粉紫短发", 100, ShopItemType.Hair, Color(0xFFE7C5F1), Color(0xFF9E6EBD)),
        ShopItem("蓝色辫发", 120, ShopItemType.Hair, Color(0xFF19A7DE), Color(0xFF086FA2)),
        ShopItem("墨镜爆炸头", 120, ShopItemType.HairGlasses, Color(0xFFF7982F), Color(0xFF20232B)),
        ShopItem("午夜长发", 120, ShopItemType.Hair, Color(0xFF222348), Color(0xFF6A4D9D)),
        ShopItem("双马尾", 120, ShopItemType.Hair, Color(0xFF231D2B), Color(0xFFF08FA9)),
        ShopItem("深海刘海", 120, ShopItemType.Hair, Color(0xFF073F4D), Color(0xFF178AA2)),
        ShopItem("学院外套", 120, ShopItemType.Top, Color(0xFFFFFFDE), Color(0xFFFFD451)),
        ShopItem("怒气表情", 80, ShopItemType.Face, Color(0xFFC08B80), Color(0xFF4B1F2C)),
        ShopItem("冷酷眉眼", 80, ShopItemType.Face, Color(0xFFC89A8E), Color(0xFF5B3968)),
    )

@Composable
fun ShopTabPage(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize().background(Color(0xFFF2F2F5))) {
        AvatarPreview(modifier = Modifier.fillMaxWidth().weight(1.08f))
        WardrobePanel(modifier = Modifier.fillMaxWidth().weight(1f))
    }
}

@Composable
private fun AvatarPreview(modifier: Modifier = Modifier) {
    Box(
        modifier =
            modifier.background(
                Brush.verticalGradient(
                    0f to Color(0xFFECE9FF),
                    0.72f to modelPreviewBackground,
                    1f to Color(0xFFDDD8FF),
                )
            )
    ) {
        Row(
            modifier = Modifier.align(Alignment.TopStart).padding(start = 16.dp, top = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            CurrencyPill(symbol = "🎟", amount = "0")
            CurrencyPill(symbol = "▣", amount = "0")
        }
        AtlasButton(
            modifier = Modifier.align(Alignment.TopStart).padding(start = 18.dp, top = 86.dp)
        )
        PetBadge(modifier = Modifier.align(Alignment.TopEnd).padding(end = 28.dp, top = 18.dp))
        BlockmanModelPreview(modifier = Modifier.align(Alignment.Center).fillMaxSize())
        Column(
            modifier = Modifier.align(Alignment.CenterEnd).padding(end = 22.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            RoundIconButton(Icons.Rounded.Expand)
            RoundIconButton(Icons.Rounded.Cached)
        }
        CartPill(
            modifier = Modifier.align(Alignment.BottomEnd).padding(end = 14.dp, bottom = 16.dp)
        )
        WardrobeToggle(
            modifier = Modifier.align(Alignment.BottomStart).padding(start = 14.dp, bottom = 16.dp)
        )
    }
}

@Composable
private fun CurrencyPill(symbol: String, amount: String) {
    Row(
        modifier =
            Modifier.height(30.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(Color(0x88959AA5))
                .padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(symbol, style = MaterialTheme.typography.titleSmall)
        Text(
            amount,
            modifier = Modifier.padding(horizontal = 12.dp),
            color = Color.White,
            fontWeight = FontWeight.Black,
            style = MaterialTheme.typography.titleMedium,
        )
        Box(
            modifier = Modifier.size(32.dp).clip(CircleShape).background(Color(0xFF7D39F6)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(Icons.Rounded.Add, contentDescription = null, tint = Color.White)
        }
    }
}

@Composable
private fun AtlasButton(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier =
                Modifier.size(54.dp).clip(RoundedCornerShape(14.dp)).background(Color(0x99888A92)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                Icons.Rounded.Category,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(34.dp),
            )
        }
        Text(
            "图鉴",
            color = Color.White,
            fontWeight = FontWeight.Black,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
private fun PetBadge(modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(64.dp), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawOval(
                Color(0x993BE9FF),
                topLeft = Offset(size.width * 0.1f, size.height * 0.62f),
                size = Size(size.width * 0.8f, size.height * 0.2f),
            )
            drawRoundRect(
                Color(0xFF4F5361),
                topLeft = Offset(size.width * 0.28f, size.height * 0.22f),
                size = Size(size.width * 0.45f, size.height * 0.42f),
                cornerRadius = CornerRadius(9f, 9f),
            )
            drawCircle(
                Color(0xFF87F7FF),
                radius = size.width * 0.05f,
                center = Offset(size.width * 0.41f, size.height * 0.41f),
            )
            drawCircle(
                Color(0xFF87F7FF),
                radius = size.width * 0.05f,
                center = Offset(size.width * 0.60f, size.height * 0.41f),
            )
            drawLine(
                Color(0xFF19DBF2),
                Offset(size.width * 0.22f, size.height * 0.2f),
                Offset(size.width * 0.16f, size.height * 0.02f),
                strokeWidth = 5f,
            )
            drawLine(
                Color(0xFF19DBF2),
                Offset(size.width * 0.75f, size.height * 0.2f),
                Offset(size.width * 0.86f, size.height * 0.02f),
                strokeWidth = 5f,
            )
        }
        Icon(
            Icons.Rounded.Pets,
            contentDescription = null,
            tint = Color(0xFF10131B),
            modifier = Modifier.size(24.dp),
        )
    }
}

@Composable
private fun BlockmanModelPreview(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(modelPreviewBackground),
        contentAlignment = Alignment.Center,
    ) {
        val engine = rememberEngine()
        val modelLoader = rememberModelLoader(engine)
        val cameraManipulator = remember { HorizontalOnlyCameraManipulator() }
        val modelInstance =
            remember(modelLoader) {
                modelLoader.createModelInstance(
                    assetFileLocation = "models/blockman_go_player_model_textured.glb"
                )
            }
        SceneView(
            modifier = Modifier.fillMaxHeight(0.8f).fillMaxWidth(0.58f),
            engine = engine,
            modelLoader = modelLoader,
            isOpaque = false,
            cameraManipulator = cameraManipulator,
        ) {
            ModelNode(modelInstance = modelInstance, scaleToUnits = 0.75f)
        }
    }
}

private class HorizontalOnlyCameraManipulator(
    private val delegate: CameraGestureDetector.CameraManipulator =
        CameraGestureDetector.DefaultCameraManipulator()
) : CameraGestureDetector.CameraManipulator {
    private var orbitY = 0
    private var isPanning = false

    override fun setViewport(width: Int, height: Int) = delegate.setViewport(width, height)

    override fun getTransform(): Transform = delegate.getTransform()

    override fun grabBegin(x: Int, y: Int, strafe: Boolean) {
        isPanning = strafe
        orbitY = y
        if (!isPanning) {
            delegate.grabBegin(x, orbitY, false)
        }
    }

    override fun grabUpdate(x: Int, y: Int) {
        if (!isPanning) {
            delegate.grabUpdate(x, orbitY)
        }
    }

    override fun grabEnd() {
        if (!isPanning) {
            delegate.grabEnd()
        }
        isPanning = false
    }

    override fun scrollBegin(x: Int, y: Int, separation: Float) =
        delegate.scrollBegin(x, y, separation)

    override fun scrollUpdate(x: Int, y: Int, prevSeparation: Float, currSeparation: Float) =
        delegate.scrollUpdate(x, y, prevSeparation, currSeparation)

    override fun scrollEnd() = delegate.scrollEnd()

    override fun update(deltaTime: Float) = delegate.update(deltaTime)
}

@Composable
private fun RoundIconButton(icon: ImageVector) {
    Box(
        modifier = Modifier.size(58.dp).clip(CircleShape).background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = Color(0xFF7E3FF3),
            modifier = Modifier.size(30.dp),
        )
    }
}

@Composable
private fun CartPill(modifier: Modifier = Modifier) {
    Row(
        modifier =
            modifier
                .height(42.dp)
                .width(112.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Color(0xB38754EA)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Icon(Icons.Rounded.ShoppingCart, contentDescription = null, tint = Color.White)
        Spacer(Modifier.width(10.dp))
        Text(
            "0",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun WardrobeToggle(modifier: Modifier = Modifier) {
    Row(
        modifier =
            modifier
                .height(44.dp)
                .width(150.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier =
                Modifier.weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xFF7D39F6)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(Icons.Rounded.Checkroom, contentDescription = null, tint = Color.White)
        }
        Text(
            "My",
            modifier = Modifier.weight(1f),
            color = Color(0xFF96979D),
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@Composable
private fun WardrobePanel(modifier: Modifier = Modifier) {
    Column(modifier = modifier.background(Color(0xFFF2F2F5))) {
        Box(modifier = Modifier.fillMaxWidth().height(18.dp), contentAlignment = Alignment.Center) {
            Box(
                modifier =
                    Modifier.size(width = 54.dp, height = 7.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFFD8D8DD))
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            shopCategories.forEachIndexed { index, label ->
                CategoryTab(label = label, selected = index == 0)
            }
        }
        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0xFFE0E0E6)))
        Row(
            modifier = Modifier.padding(start = 16.dp, top = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            shopFilters.forEachIndexed { index, label ->
                FilterTab(label, selected = index == 0, hasDot = index == 1)
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding =
                androidx.compose.foundation.layout.PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 92.dp,
                ),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            shopItems.chunked(4).forEach { rowItems ->
                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        rowItems.forEach { item ->
                            ShopCard(item = item, modifier = Modifier.weight(1f))
                        }
                        repeat(4 - rowItems.size) { Spacer(Modifier.weight(1f)) }
                    }
                }
            }
        }
    }
}

@Composable
private fun CategoryTab(label: String, selected: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            label,
            color = if (selected) Color(0xFF7D39F6) else Color(0xFF54565D),
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(Modifier.height(9.dp))
        Box(
            modifier =
                Modifier.size(width = 36.dp, height = 3.dp)
                    .background(if (selected) Color(0xFF7D39F6) else Color.Transparent)
        )
    }
}

@Composable
private fun FilterTab(label: String, selected: Boolean, hasDot: Boolean) {
    Box {
        Text(
            label,
            modifier =
                if (selected)
                    Modifier.clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFE4E4EA))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                else Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
            color = if (selected) Color(0xFF5D5E66) else Color(0xFFA2A2A8),
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
            style = MaterialTheme.typography.titleMedium,
        )
        if (hasDot) {
            Box(
                modifier =
                    Modifier.align(Alignment.TopEnd)
                        .size(9.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFF2A77))
            )
        }
    }
}

@Composable
private fun ShopCard(item: ShopItem, modifier: Modifier = Modifier) {
    Column(
        modifier =
            modifier
                .aspectRatio(0.82f)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFFFB7F3)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier =
                Modifier.fillMaxWidth()
                    .weight(1f)
                    .background(Brush.radialGradient(listOf(Color(0xFFFFE6FB), Color(0xFFFFA9ED))))
        ) {
            Canvas(modifier = Modifier.align(Alignment.TopStart).padding(7.dp).size(24.dp)) {
                drawCircle(
                    Brush.sweepGradient(
                        listOf(
                            Color.Red,
                            Color.Yellow,
                            Color.Green,
                            Color.Cyan,
                            Color.Blue,
                            Color.Magenta,
                            Color.Red,
                        )
                    )
                )
                drawCircle(Color.White, radius = size.minDimension * 0.28f)
            }
            Box(
                modifier =
                    Modifier.align(Alignment.TopEnd)
                        .padding(5.dp)
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFF8A17)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    Icons.Rounded.LocalFireDepartment,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp),
                )
            }
            ShopItemArt(
                item,
                modifier = Modifier.align(Alignment.Center).fillMaxWidth(0.86f).fillMaxHeight(0.72f),
            )
        }
        Row(
            modifier =
                Modifier.fillMaxWidth().background(Color(0xFFFFE7F9)).padding(vertical = 6.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("🎟", style = MaterialTheme.typography.bodyMedium)
            Text(
                item.price.toString(),
                color = Color(0xFF202027),
                fontWeight = FontWeight.Black,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Composable
private fun ShopItemArt(item: ShopItem, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        when (item.type) {
            ShopItemType.Dress -> {
                drawRoundRect(
                    item.primary,
                    topLeft = Offset(w * 0.22f, h * 0.18f),
                    size = Size(w * 0.56f, h * 0.64f),
                    cornerRadius = CornerRadius(7f, 7f),
                )
                drawRect(
                    item.secondary,
                    topLeft = Offset(w * 0.28f, h * 0.2f),
                    size = Size(w * 0.44f, h * 0.12f),
                )
                drawLine(
                    item.secondary,
                    Offset(w * 0.28f, h * 0.4f),
                    Offset(w * 0.18f, h * 0.7f),
                    strokeWidth = 8f,
                )
                drawLine(
                    item.secondary,
                    Offset(w * 0.72f, h * 0.4f),
                    Offset(w * 0.82f, h * 0.7f),
                    strokeWidth = 8f,
                )
            }
            ShopItemType.Bottoms -> {
                drawRect(
                    Color(0xFFFFD3B7),
                    topLeft = Offset(w * 0.26f, h * 0.36f),
                    size = Size(w * 0.2f, h * 0.48f),
                )
                drawRect(
                    Color(0xFFFFD3B7),
                    topLeft = Offset(w * 0.54f, h * 0.36f),
                    size = Size(w * 0.2f, h * 0.48f),
                )
                drawRect(
                    item.primary,
                    topLeft = Offset(w * 0.22f, h * 0.12f),
                    size = Size(w * 0.56f, h * 0.33f),
                )
                drawRect(
                    item.secondary,
                    topLeft = Offset(w * 0.22f, h * 0.12f),
                    size = Size(w * 0.56f, h * 0.08f),
                )
            }
            ShopItemType.Top -> {
                drawRoundRect(
                    item.primary,
                    topLeft = Offset(w * 0.2f, h * 0.18f),
                    size = Size(w * 0.6f, h * 0.5f),
                    cornerRadius = CornerRadius(6f, 6f),
                )
                drawRoundRect(
                    item.secondary,
                    topLeft = Offset(w * 0.38f, h * 0.2f),
                    size = Size(w * 0.24f, h * 0.48f),
                    cornerRadius = CornerRadius(3f, 3f),
                )
                drawLine(
                    Color(0xFFFF8B38),
                    Offset(w * 0.28f, h * 0.28f),
                    Offset(w * 0.36f, h * 0.38f),
                    strokeWidth = 4f,
                )
            }
            ShopItemType.Hair,
            ShopItemType.HairGlasses -> {
                drawRect(
                    Color(0xFFFFD0B5),
                    topLeft = Offset(w * 0.35f, h * 0.38f),
                    size = Size(w * 0.35f, h * 0.36f),
                )
                drawOval(
                    item.primary,
                    topLeft = Offset(w * 0.18f, h * 0.12f),
                    size = Size(w * 0.62f, h * 0.42f),
                )
                drawOval(
                    item.secondary,
                    topLeft = Offset(w * 0.12f, h * 0.3f),
                    size = Size(w * 0.22f, h * 0.44f),
                )
                if (item.type == ShopItemType.HairGlasses) {
                    drawRoundRect(
                        Color(0xFF22242A),
                        topLeft = Offset(w * 0.36f, h * 0.35f),
                        size = Size(w * 0.36f, h * 0.11f),
                        cornerRadius = CornerRadius(5f, 5f),
                    )
                }
            }
            ShopItemType.Face -> {
                drawRect(
                    item.primary,
                    topLeft = Offset(w * 0.25f, h * 0.18f),
                    size = Size(w * 0.5f, h * 0.56f),
                )
                drawLine(
                    item.secondary,
                    Offset(w * 0.32f, h * 0.35f),
                    Offset(w * 0.45f, h * 0.42f),
                    strokeWidth = 5f,
                )
                drawLine(
                    item.secondary,
                    Offset(w * 0.68f, h * 0.35f),
                    Offset(w * 0.55f, h * 0.42f),
                    strokeWidth = 5f,
                )
            }
        }
    }
}

private data class ShopItem(
    val name: String,
    val price: Int,
    val type: ShopItemType,
    val primary: Color,
    val secondary: Color,
)

private enum class ShopItemType {
    Dress,
    Bottoms,
    Top,
    Hair,
    HairGlasses,
    Face,
}

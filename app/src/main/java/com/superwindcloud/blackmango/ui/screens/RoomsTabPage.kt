package com.superwindcloud.blackmango.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun RoomsTabPage(modifier: Modifier = Modifier) {
    var section by remember { mutableStateOf(RoomsSection.Rooms) }

    ScrollableTabPage(
        title = if (section == RoomsSection.Rooms) "房间" else "工坊",
        subtitle = if (section == RoomsSection.Rooms) "加入实时联机房间" else "浏览创作者地图",
        modifier = modifier,
    ) {
        item { RoomSectionTabs(selectedSection = section, onSectionSelected = { section = it }) }
        if (section == RoomsSection.Rooms) {
            categorySections.take(3).forEach { gameSection(it) }
        } else {
            categorySections.drop(2).forEach { gameSection(it) }
        }
    }
}

@Composable
private fun RoomSectionTabs(
    selectedSection: RoomsSection,
    onSectionSelected: (RoomsSection) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = modifier.fillMaxWidth()) {
        RoomsSection.entries.forEach { section ->
            FilterChip(
                selected = section == selectedSection,
                onClick = { onSectionSelected(section) },
                label = { Text(section.label, fontWeight = FontWeight.SemiBold) },
                shape = RoundedCornerShape(8.dp),
                colors =
                    FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFF151A22),
                        selectedLabelColor = Color.White,
                        containerColor = Color.White,
                    ),
            )
        }
    }
}

private enum class RoomsSection(val label: String) {
    Rooms("房间"),
    Workshop("工坊"),
}

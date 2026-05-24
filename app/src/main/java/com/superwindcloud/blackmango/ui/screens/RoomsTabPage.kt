package com.superwindcloud.blackmango.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.superwindcloud.blackmango.R

@Composable
fun RoomsTabPage() {
    var section by remember { mutableStateOf(RoomsSection.Rooms) }

    Box(Modifier.fillMaxSize()) {
        ScreenshotPage(
            imageRes =
                when (section) {
                    RoomsSection.Rooms -> R.drawable.tab_rooms
                    RoomsSection.Workshop -> R.drawable.tab_workshop
                }
        )
        RoomSectionHitLayer(
            selectedSection = section,
            onSectionSelected = { section = it },
            modifier = Modifier.align(Alignment.TopStart),
        )
    }
}

@Composable
private fun RoomSectionHitLayer(
    selectedSection: RoomsSection,
    onSectionSelected: (RoomsSection) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.padding(top = 28.dp, start = 18.dp).height(56.dp)) {
        RoomsSection.entries.forEach { section ->
            val interactionSource = remember(section) { MutableInteractionSource() }
            Box(
                modifier =
                    Modifier.width(108.dp).height(56.dp).clickable(
                        enabled = section != selectedSection,
                        role = Role.Tab,
                        indication = null,
                        interactionSource = interactionSource,
                    ) {
                        onSectionSelected(section)
                    }
            )
        }
    }
}

private enum class RoomsSection {
    Rooms,
    Workshop,
}

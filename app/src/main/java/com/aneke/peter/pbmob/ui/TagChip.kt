package com.aneke.peter.pbmob.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aneke.peter.pbmob.ui.theme.cutCornerShape

@Composable
fun TagChip(tag : String) {
    
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onBackground,
                shape = cutCornerShape
            ).background(
                color = Transparent,
                shape = cutCornerShape)
            .clip(shape = cutCornerShape)
            .padding(4.dp)
    ) {
        Text(text = tag, color = MaterialTheme.colors.onBackground)
    }
}

@Composable
@Preview
fun TagChipPreview() {
    TagChip(tag = "Flowers")
}
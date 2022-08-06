package com.aneke.peter.pbmob

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.aneke.peter.pbmob.data.Hit
import com.aneke.peter.pbmob.ui.DecisionDialog
import com.aneke.peter.pbmob.ui.DetailScreen
import com.aneke.peter.pbmob.ui.TagChip
import com.aneke.peter.pbmob.util.resolveTags

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HitCard(hit: Hit) {
    
    val tags = resolveTags(hit.tags)
    val showDialogState = remember { mutableStateOf(false) }

    val showDetailsState = remember { mutableStateOf<Hit?>(null) }

    DecisionDialog(hit = hit,
        showDialogState = showDialogState,
        confirmAction = {
            showDetailsState.value = it
        })

    DetailScreen(hitState = showDetailsState)
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        onClick = { showDialogState.value = true }) {
        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            Image(painter = rememberImagePainter(data = hit.previewURL),
                modifier = Modifier
                    .size(150.dp)
                    .padding(4.dp)
                    .clip(CutCornerShape(8.dp)),
                contentDescription ="thumb_nail")

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.padding(8.dp),
            ) {
                Text(
                    text = "Username: ${hit.user}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp))

                Text("Tags:",
                    modifier = Modifier.padding(8.dp),
                    fontWeight = FontWeight.SemiBold)

                LazyVerticalGrid(
                    modifier = Modifier.height(100.dp),
                    columns = GridCells.Fixed(2),
                    userScrollEnabled = false
                ){
                    items(tags){
                        TagChip(tag = it)
                    }
                }
            }
        }
    }

}


@Composable
@Preview
fun HitCardPreview() {
    HitCard(Hit(previewURL = "https://via.placeholder.com/200" ))
}
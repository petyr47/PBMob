package com.aneke.peter.pbmob.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.aneke.peter.pbmob.data.Hit
import com.aneke.peter.pbmob.util.resolveTags

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DetailScreen(
    hitState: MutableState<Hit?>
) {

    if (hitState.value != null) {
        val hit = hitState.value!!
        val tags = resolveTags(hit.tags)
        Dialog(
            onDismissRequest = {
                hitState.value = null
            },
            DialogProperties(dismissOnClickOutside = false, usePlatformDefaultWidth = false)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)

            ) {
                Column (modifier = Modifier.padding(8.dp)){
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)) {
                        IconButton(
                            onClick = {
                                hitState.value = null
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close Icon",
                                tint = Color.White)
                        }
                        Image(painter = rememberAsyncImagePainter(
                            model = hit.largeImageURL,
                            error = rememberImagePainter(data = hit.previewURL),
                            placeholder = rememberImagePainter(data = hit.previewURL),
                        ),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp)
                                .clip(CutCornerShape(4.dp)),
                            contentDescription ="image")
                    }

                    Column(
                        modifier = Modifier.padding(8.dp)
                            .verticalScroll(rememberScrollState())
                            .weight(1f, false),
                        verticalArrangement= Arrangement.SpaceBetween,

                    ) {
                        Text(
                            text = "Username: ${hit.user}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(8.dp))

                        Text("Tags:",
                            modifier = Modifier.padding(8.dp),
                            fontSize =18.sp,
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

                        Text(
                            text = "Likes: ${hit.likes}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(8.dp))

                        Text(
                            text = "Downloads: ${hit.downloads}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(8.dp))


                        Text(
                            text = "Comments: ${hit.comments}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(8.dp))
                    }
                }
            }

        }
    }

}
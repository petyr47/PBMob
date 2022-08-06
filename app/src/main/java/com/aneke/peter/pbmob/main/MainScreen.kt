package com.aneke.peter.pbmob.ui

import MainAppBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.aneke.peter.pbmob.HitCard
import com.aneke.peter.pbmob.main.MainViewModel
import com.aneke.peter.pbmob.main.SearchWidgetState
import com.aneke.peter.pbmob.util.Status

@Composable
fun MainScreen(
    mainViewModel: MainViewModel
) {

    val searchWidgetState by mainViewModel.searchWidgetState
    val searchTextState by mainViewModel.searchTextState
    val searchResults by remember { mainViewModel.searchResults }
    val showDialogState = remember { mutableStateOf(false) }

    mainViewModel.search()

    Scaffold(
        topBar = {
            MainAppBar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = {
                    mainViewModel.updateSearchTextState(newValue = it)
                },
                onCloseClicked = {
                    mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                },
                onSearchClicked = {
                    mainViewModel.search()
                },
                onSearchTriggered = {
                    mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                }
            )
        },
        content = { it ->
            if (searchResults == null) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(it),
                        text = "Search Images",
                        style = TextStyle(fontSize = 40.sp)
                    )
                }
            } else {
                searchResults?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            val items = resource.data?.hits ?: emptyList()
                            LazyColumn{
                                items(items){
                                    HitCard(it)
                                }
                            }
                        }
                        Status.ERROR -> {
                            showDialogState.value = true
                            ErrorDialog(
                                showDialogState = showDialogState,
                                message = resource.resolveMessage())
                        }
                        Status.LOADING -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Loader()
                            }
                        }
                    }
                }
            }
        }
    )
}
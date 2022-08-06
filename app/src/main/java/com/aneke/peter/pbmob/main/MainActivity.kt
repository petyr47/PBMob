package com.aneke.peter.pbmob.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.aneke.peter.pbmob.ui.MainScreen
import com.aneke.peter.pbmob.ui.theme.PBMobTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PBMobTheme {
                MainScreen(mainViewModel = mainViewModel)
            }
        }
    }
}
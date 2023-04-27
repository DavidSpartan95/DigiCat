package com.example.digicat.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.digicat.utilities.MenuButton
import com.example.digicat.utilities.Title


@Composable
fun HomeScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize(),
        color = Color.Black) {

        Column(Modifier.fillMaxWidth(), Arrangement.Top, Alignment.CenterHorizontally) {

            Title("DigiCat")
        }
        Column(Modifier.fillMaxWidth(), Arrangement.Center, Alignment.CenterHorizontally) {

            MenuButton("NEW GAME",navController,Screen.Create.route)
            MenuButton("LOAD GAME",navController,Screen.Load.route)
            MenuButton("OPTION",navController,Screen.Option.route)
        }
    }
}


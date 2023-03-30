package com.example.digicat.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun OptionScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize(),
        color = Color.Green) {

        Box(
            modifier = Modifier.fillMaxSize(),
            Alignment.Center,

            ){
            Text(
                modifier = Modifier.clickable {
                    navController.navigate(route = Screen.Home.route){
                        popUpTo(Screen.Home.route){
                            inclusive = true
                        }
                    }
                },
                text = "Option", color = Color.Blue, fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }

    }

}
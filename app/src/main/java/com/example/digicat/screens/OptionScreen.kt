package com.example.digicat.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.digicat.api.fetchWeather
import com.example.digicat.api.temperatureViewModel
import com.example.digicat.ui.theme.orbitronBold



@Composable
fun OptionScreen(navController: NavController) {
    val temperature by temperatureViewModel.temp.collectAsState()
    Surface(modifier = Modifier.fillMaxSize(),
        color = Color.Black) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            Arrangement.Top,Alignment.CenterHorizontally

            ){
            Text(
                modifier = Modifier.clickable {
                    navController.navigate(route = Screen.Home.route){
                        popUpTo(Screen.Home.route){
                            inclusive = true
                        }
                    }
                },
                text = "Option", color = Color.White, fontSize = 65.sp,fontFamily = orbitronBold)

        }
        Column(modifier = Modifier.fillMaxWidth(),
            Arrangement.Center, Alignment.CenterHorizontally) {

            MenuButton(text = "Fetch", onClick = {fetchWeather()})

        }
        Column(modifier = Modifier.fillMaxWidth(),
            Arrangement.Bottom, Alignment.CenterHorizontally) {

            Text(text = "Temperature is $temperature", fontFamily = orbitronBold, fontSize = 30.sp, color = Color.White)
        }

    }

}




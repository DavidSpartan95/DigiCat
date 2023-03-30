package com.example.digicat.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.digicat.ui.theme.orbitronBold


@Composable
fun HomeScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize(),
        color = Color.Black) {

        Column(Modifier.fillMaxWidth(), Arrangement.Top, Alignment.CenterHorizontally) {

            Title("DigiCat")
        }
        Column(Modifier.fillMaxWidth(), Arrangement.Center, Alignment.CenterHorizontally) {

            MenuButton("START",navController,Screen.Option.route)
            MenuButton("OPTION",navController,Screen.Option.route)
        }
    }
}

@Composable
fun Title(name: String) {
    Text(text = name,fontFamily = orbitronBold, fontSize = 75.sp, color = Color.White)
}

@Composable
fun MenuButton(text: String,navController: NavController,nav:String){
    Box(
        modifier = Modifier.padding(8.dp)
    ) {

        Button(
            modifier = Modifier
                .fillMaxWidth(0.5F)
                .height(50.dp),
            onClick = { navController.navigate(route = nav) },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(219,137,39),
                contentColor = Color.White)
        ) {
            Text(text = text, fontFamily = orbitronBold, fontSize = 30.sp)
        }
    }
}
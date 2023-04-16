package com.example.digicat.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.digicat.dataBase.AppDatabase
import com.example.digicat.dataBase.UserRepository
import com.example.digicat.ui.theme.orbitronBold



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

@Composable
fun Title(name: String) {
    Text(text = name,fontFamily = orbitronBold, fontSize = 75.sp, color = Color.White)
}

@Composable
fun MenuButton(text: String,navController: NavController? = null,nav:String?=null,onClick: (() -> Unit)? = null){
    Box(
        modifier = Modifier.padding(8.dp)
    ) {

        Button(
            modifier = Modifier
                //.fillMaxWidth(0.5F)
                .height(50.dp)
                .widthIn(min = 32.dp)
            ,
            onClick = { navController?.navigate(route = nav?:"") ?:onClick?.invoke() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(219,137,39),
                contentColor = Color.White)
        ) {
            Text(text = text, fontFamily = orbitronBold, fontSize = 24.sp, modifier = Modifier.widthIn(min = 32.dp))
        }
    }
}
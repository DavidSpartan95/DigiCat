package com.example.digicat.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.digicat.dataBase.UserRepository
import com.example.digicat.viewModel.GameViewModel


@Composable
fun GameScreen(navController: NavController, userRepository: UserRepository, username: String) {

    val viewModel = remember {
        GameViewModel()
    }
    val userDraw = viewModel.userDraw.collectAsState()
        if (userDraw.value == null){
            viewModel.getModel(userRepository = userRepository, username = username)
        }



    Text(text = "Hello $username", color = Color.White)


    userDraw.value?.let {
        Box(
            Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            println("TEST1")
            DrawDigiCat(userDraw.value!!.color, userDraw.value!!.drawInstruction)
        }
    }
}


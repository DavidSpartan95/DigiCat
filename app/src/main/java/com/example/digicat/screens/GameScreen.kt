package com.example.digicat.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.digicat.dataBase.UserRepository

@Composable
fun GameScreen(navController: NavController, userRepository: UserRepository, username: String) {


    Text(text = "Hello $username", color = Color.White)

}
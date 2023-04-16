package com.example.digicat.screens

import android.annotation.SuppressLint
import android.text.style.ClickableSpan
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.digicat.dataBase.UserRepository
import com.example.digicat.viewModel.DigiCatColorViewModel
import com.example.digicat.viewModel.GameViewModel
import kotlinx.coroutines.Dispatchers


@SuppressLint("SuspiciousIndentation")
@Composable
fun GameScreen(navController: NavController, userRepository: UserRepository, username: String) {

    //Reset the default createScreen digiCat
    LaunchedEffect(true){digiCatColorViewModel = DigiCatColorViewModel()}
    var points = 0
    val viewModel = remember {
        GameViewModel()
    }
    val userDraw = viewModel.userDraw.collectAsState()
        if (userDraw.value == null){
            viewModel.getModel(userRepository = userRepository, username = username)
        }

    Surface(modifier = Modifier.fillMaxSize(),
        color = Color.Black) {
        Column(Modifier.fillMaxWidth(), Arrangement.Top, Alignment.CenterHorizontally) {

            Text(text = "Hello $username", color = Color.White)

            userDraw.value?.let {

            }

            userDraw.value?.let {

                Text(text = "Points ${userDraw.value!!.points}", color = Color.White)

                Box(
                    Modifier
                        .widthIn(min = 32.dp)
                        .heightIn(min = 32.dp)
                        .clickable {
                            points++
                            println(points)
                            userRepository.performDatabaseOperation(Dispatchers.IO) {
                                userRepository.addPoint(username)//TODO make this a viewModel

                            }
                        },
                    contentAlignment = Alignment.Center
                ) {

                    DrawDigiCat(userDraw.value!!.draw[0].color, userDraw.value!!.draw[0].drawInstruction)

                }
            }
        }

    }

}


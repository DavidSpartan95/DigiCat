package com.example.digicat.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.digicat.dataBase.UserRepository
import com.example.digicat.ui.theme.orbitronBold
import com.example.digicat.viewModel.DigiCatColorViewModel
import com.example.digicat.viewModel.GameViewModel
import kotlinx.coroutines.Dispatchers


@SuppressLint("SuspiciousIndentation")
@Composable
fun GameScreen(navController: NavController, userRepository: UserRepository, username: String) {
    var points: Int  by remember {mutableStateOf(0)}
    //Reset the default createScreen digiCat
    LaunchedEffect(true){digiCatColorViewModel = DigiCatColorViewModel()}

    val viewModel = remember {
        GameViewModel()
    }

    val userDraw = viewModel.userDraw.collectAsState()

        LaunchedEffect(true){
            viewModel.getModel(userRepository = userRepository, username = username)
            userRepository.performDatabaseOperation(Dispatchers.IO) {
                try {
                    points = userRepository.fetchPoints(username)
                }catch (e: java.lang.Exception){
                    println(e)
                }
            }

        }


    Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {

        Column(Modifier.fillMaxWidth(), Arrangement.Top, Alignment.CenterHorizontally) {

            Text(text = "Hello $username", color = Color.White,fontFamily = orbitronBold, fontSize = 24.sp)

            userDraw.value?.let {

                Text(text = "Points $points", color = Color.White,fontFamily = orbitronBold, fontSize = 24.sp)

                Box(
                    Modifier
                        .widthIn(min = 32.dp)
                        .heightIn(min = 32.dp)
                        .clickable {
                            userRepository.performDatabaseOperation(Dispatchers.IO) {

                                userRepository.addPoint(username)
                                points = userRepository.fetchPoints(username)

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


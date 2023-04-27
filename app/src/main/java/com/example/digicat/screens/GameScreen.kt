package com.example.digicat.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.digicat.api.temperatureViewModel
import com.example.digicat.dataBase.UserRepository
import com.example.digicat.ui.theme.orbitronBold
import com.example.digicat.utilities.DrawDigiCat
import com.example.digicat.utilities.PaintAchviment
import com.example.digicat.utilities.toastMessage
import com.example.digicat.viewModel.DigiCatViewModel
import com.example.digicat.viewModel.GameViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("SuspiciousIndentation")
@Composable
fun GameScreen(userRepository: UserRepository, username: String) {

    val temp by temperatureViewModel.temp.collectAsState()
    val context = LocalContext.current
    var sunProg by remember { mutableStateOf(0) }
    var snowProg by remember { mutableStateOf(0) }
    var achvAmount by remember { mutableStateOf(0) }
    var achivmentUnlock by remember { mutableStateOf(false) }
    var points: Int  by remember {mutableStateOf(0)}
    val scrollState = rememberScrollState()
    val viewModel = remember { GameViewModel() }

    val userDraw = viewModel.userDraw.collectAsState()

        LaunchedEffect(true,achivmentUnlock){
            if (achivmentUnlock){achivmentUnlock = false}
            println("Launched")
            digiCatViewModel = DigiCatViewModel()
            viewModel.getModel(userRepository = userRepository, username = username)
            userRepository.performDatabaseOperation(Dispatchers.IO) {
                try {
                    points = userRepository.fetchPoints(username)
                    achvAmount = userRepository.fetchUnlocksNew(username)
                }catch (e: java.lang.Exception){
                    println(e)
                }
            }
        }


    Surface(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(state = scrollState), color = Color.Black) {

        Column(Modifier.fillMaxWidth(), Arrangement.Top, Alignment.CenterHorizontally) {

            Text(text = "Hello $username", color = Color.White,fontFamily = orbitronBold, fontSize = 24.sp)

            userDraw.value?.let {

                Text(text = "Points $points", color = Color.White,fontFamily = orbitronBold, fontSize = 24.sp)

                Box(
                    Modifier
                        .widthIn(min = 32.dp)
                        .heightIn(min = 32.dp)
                        .padding(45.dp)
                        .clickable {
                            // This clickable is a bit too long,
                            // to summarise: this code will add points, and progress to achievements
                            // if a mile stone is meet a Toast pops up and the achievement is unlcoked
                            userRepository.performDatabaseOperation(Dispatchers.IO) {

                                userRepository.addPoint(username)
                                points = userRepository.fetchPoints(username)
                                if (temp != "null") {
                                    if (temp.toInt() > 10) {
                                        sunProg++
                                    } else if(temp.toInt() <= 10) {
                                        snowProg++
                                    }
                                }
                                if (points == 10 || points == 100 || points == 1000 || points == 10000) {
                                    CoroutineScope(Dispatchers.Main).launch {

                                        toastMessage("Achievement unlocked!", context)

                                    }
                                    userRepository.unlockAchvimentsNew(username)
                                    achivmentUnlock = true
                                }
                                if (sunProg == 30 && !userDraw.value!!.sunAchievement) {
                                    userRepository.unlockSunAchvivments(username)
                                    CoroutineScope(Dispatchers.Main).launch {

                                        toastMessage("Achievement unlocked!", context)
                                    }
                                    achivmentUnlock = true
                                }

                                if (snowProg == 30 && !userDraw.value!!.snowAchievement) {
                                    userRepository.unlockSnowAchvivments(username)

                                    CoroutineScope(Dispatchers.Main).launch {
                                        toastMessage("Achievement unlocked!", context)
                                    }
                                    achivmentUnlock = true
                                }
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {

                    DrawDigiCat(userDraw.value!!.draw[0].color, userDraw.value!!.draw[0].drawInstruction)

                }
                Text(text = "Achievements", color = Color.White,fontFamily = orbitronBold, fontSize = 24.sp)

                LazyRow(){

                    items(achvAmount+2) {
                        if ((it == 0 && !userDraw.value!!.sunAchievement) ||(it == 1 && !userDraw.value!!.snowAchievement)){
                            //
                        }else{
                            Box(
                                Modifier
                                    .widthIn(min = 32.dp)
                                    .heightIn(min = 32.dp)) {
                                PaintAchviment(
                                    unlocked = it + 1,
                                    sun = userDraw.value!!.sunAchievement,
                                    snow = userDraw.value!!.snowAchievement
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


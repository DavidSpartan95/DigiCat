package com.example.digicat.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.digicat.api.temperatureViewModel
import com.example.digicat.dataBase.User
import com.example.digicat.dataBase.UserRepository
import com.example.digicat.dataBase.userDigiCatData.DigiCatData
import com.example.digicat.ui.theme.orbitronBold
import kotlinx.coroutines.Dispatchers

@Composable
fun LoadScreen(navController: NavController, userRepository: UserRepository) {
    var saveFiles: List<User>? by remember {mutableStateOf(null)}
    var delete: Boolean  by remember {mutableStateOf(false)}

        LaunchedEffect(key1 = true, key2 = delete){
            if (delete){delete = false}
            userRepository.performDatabaseOperation(Dispatchers.IO) {
                try {
                    saveFiles = userRepository.getUsers()
                }
                catch (e: Exception){
                    println("Error Load")
                }
            }
        }

    Surface(modifier = Modifier.fillMaxSize(),color = Color.Black) {

    saveFiles?.let {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(saveFiles!!.size) {

                Column(modifier = Modifier
                    .background(Color.Black)
                    .fillMaxWidth()
                    .padding(2.dp)
                    .border(width = 5.dp, color = Color.Green, shape = RoundedCornerShape(8.dp))
                    .padding(20.dp)
                ) {

                Text(
                    text = saveFiles!![it].name,
                    fontFamily = orbitronBold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                )
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {

                    Button(modifier = Modifier
                        .heightIn(min = 30.dp)
                        .widthIn(min = 80.dp),
                        onClick = {
                            userRepository.performDatabaseOperation(Dispatchers.IO) {
                                userRepository.deleteUser(saveFiles!![it].name)
                                delete = true
                            }

                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(255,0,0),
                            contentColor = Color.White)


                    ) {
                        Text(text = "Delete",fontFamily = orbitronBold, fontSize = 20.sp)
                    }
                    DrawDigiCat(saveFiles!![it].draw[0].color, saveFiles!![it].draw[0].drawInstruction, 50)
                    Button(modifier = Modifier
                        .heightIn(min = 30.dp)
                        .widthIn(min = 80.dp),
                        onClick = {
                            navController.navigate(route = "game_screen/" + saveFiles!![it].name){
                                popUpTo(Screen.Load.route){
                                    inclusive = true
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0,255,0),
                            contentColor = Color.White)
                    ) {
                        Text(text = "Load",fontFamily = orbitronBold, fontSize = 20.sp)
                    }

                }
            }
            }
        }
    }
}
}
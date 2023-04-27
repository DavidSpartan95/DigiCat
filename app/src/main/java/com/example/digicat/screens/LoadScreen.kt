package com.example.digicat.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.digicat.dataBase.User
import com.example.digicat.dataBase.UserRepository
import com.example.digicat.utilities.DeleteButton
import com.example.digicat.utilities.DisplayName
import com.example.digicat.utilities.DrawDigiCat
import com.example.digicat.utilities.LoadButton
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

                        DisplayName(name = saveFiles!![it].name)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            DeleteButton(userRepository, saveFiles!![it].name) {delete = true}

                            DrawDigiCat(saveFiles!![it].draw[0].color, saveFiles!![it].draw[0].drawInstruction, 50)

                            LoadButton(navController = navController, userName = saveFiles!![it].name)

                        }
                    }
                }
            }
        }
        saveFiles?.let {
            println(saveFiles)
            if (saveFiles!!.isEmpty()){
                println("Is not Empty $saveFiles")
                Box(modifier = Modifier.size(height = 90.dp, width = 290.dp)) {

                    Text("EMPTY", Modifier.align(Alignment.Center), fontSize = 50.sp, color = Color.White)

                }
            }
        }
    }
}



package com.example.digicat.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.digicat.dataBase.User
import com.example.digicat.dataBase.UserRepository
import kotlinx.coroutines.Dispatchers

@Composable
fun LoadScreen(navController: NavController, userRepository: UserRepository) {
    var saveFiles: List<User>? by remember {mutableStateOf(null)}

    if (saveFiles == null){

        userRepository.performDatabaseOperation(Dispatchers.IO) {
            saveFiles = userRepository.getUsers()
            println(saveFiles)
        }

    }

    saveFiles?.let {
        LazyColumn {
            items(saveFiles!!.size) {

                Text(
                    text = "${saveFiles!![it].name}",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp)
                )
            }
        }
    }
}
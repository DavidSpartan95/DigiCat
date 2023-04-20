package com.example.digicat.screens

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.digicat.R
import com.example.digicat.dataBase.UserRepository
import com.example.digicat.ui.theme.orbitronBold
import com.example.digicat.utilities.*
import com.example.digicat.viewModel.DigiCatViewModel

var digiCatViewModel = DigiCatViewModel()
@Composable
fun CreateScreen(navController: NavController,userRepository: UserRepository) {

    val context = LocalContext.current
    val digiEye by digiCatViewModel.eyes.collectAsState()
    val digiColor by digiCatViewModel.color.collectAsState()
    val text by digiCatViewModel.text.collectAsState()
    val scrollState = rememberScrollState()


    Surface(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(state = scrollState), color = Color.Black) {

        Column(Modifier.fillMaxWidth(),Arrangement.Top, Alignment.CenterHorizontally) {

            Text(text = "Create", fontSize = 30.sp, fontFamily = orbitronBold, color = Color.White)

            DrawDigiCat(digiColor,digiEye)

            ChangeColorButton()

            ChangeEyeButton { digiCatViewModel.changeEyeTest() }

            DoneButton{ checkAndNavigate(navController,userRepository,text,context,digiEye,digiColor) }

            //TODO style this
            TextField(value = text, onValueChange = { digiCatViewModel.changeText(it)}, colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                label = { Text("Name") },
                modifier = Modifier.padding(20.dp))
        }
    }
}













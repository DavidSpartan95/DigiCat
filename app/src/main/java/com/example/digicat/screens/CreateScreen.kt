package com.example.digicat.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.digicat.R
import com.example.digicat.dataBase.User
import com.example.digicat.dataBase.UserRepository
import com.example.digicat.dataBase.userDigiCatData.DigiCatData
import com.example.digicat.ui.theme.orbitronBold
import com.example.digicat.viewModel.DigiCatColorViewModel
import kotlinx.coroutines.Dispatchers

var digiCatColorViewModel = DigiCatColorViewModel()
@Composable
fun CreateScreen(navController: NavController,userRepository: UserRepository) {
    val context = LocalContext.current

    //TODO eyePart needs to be a viewModel aswell
    val eyePart = arrayOf(R.drawable.eyes,R.drawable.eyes2)
    val color by digiCatColorViewModel.color.collectAsState()
    var text by remember { mutableStateOf("TEST") }
    var num by remember {mutableStateOf(0)}
    val scrollState = rememberScrollState()


    Surface(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(state = scrollState), color = Color.Black) {

        Column(Modifier.fillMaxWidth(),Arrangement.Top, Alignment.CenterHorizontally) {

            Text(text = "Create", fontSize = 30.sp, fontFamily = orbitronBold, color = Color.White)

            DrawDigiCat(color,eyePart[num])

            Button(modifier = Modifier
                .height(50.dp)
                .widthIn(min = 32.dp)
                ,onClick = {
                digiCatColorViewModel.setColor(randomColor())
            }, colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(219,137,39))) {
                Text(text = "Random Color",color = Color.White,fontFamily = orbitronBold, fontSize = 24.sp)
            }
            Button(modifier = Modifier
                .height(50.dp)
                .widthIn(min = 32.dp)
                ,onClick = {
                num = if(num == eyePart.size-1) 0 else num+1
            }, colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(219,137,39))) {
                Text(text = "Change Eyes",color = Color.White,fontFamily = orbitronBold, fontSize = 24.sp)
            }

            Button(modifier = Modifier
                .height(50.dp)
                .widthIn(min = 32.dp)
                ,onClick = {
                if (text.isNotEmpty()){
                    userRepository.performDatabaseOperation(Dispatchers.IO) {//TODO try to do this at GameScreen
                        userRepository.insertUser(User(text,0, arrayOf(DigiCatData(eyePart[num],color))))
                    }

                        navController.navigate(route = "game_screen/$text"){
                            popUpTo(Screen.Create.route){
                                inclusive = true
                            }
                        }
                }else{
                    Toast.makeText(context,"please insert name", Toast.LENGTH_LONG).show()
                }
            }, colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(219,137,39))){
                Text("DONE",color = Color.White,fontFamily = orbitronBold, fontSize = 24.sp)
            }

            TextField(value = text, onValueChange = { text = it}, colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                label = { Text("Name") },
                modifier = Modifier.padding(20.dp))
        }
    }
}

@Composable
fun DrawDigiCat(primeColor:Color,eyePart:Int,size: Int? = null) {
    var drawSize = 250
    if (size != null){ drawSize = size}
    Box(Modifier.size(drawSize.dp), contentAlignment = Alignment.TopCenter) {
        Image(painter = painterResource(id = R.drawable.ears_outer), contentDescription = "",
            colorFilter = ColorFilter.tint(color = primeColor))

        Image(painter = painterResource(id = R.drawable.ears_inner), contentDescription = "")

        Image(painter = painterResource(id = R.drawable.body), contentDescription = "",
            colorFilter = ColorFilter.tint(color = primeColor))

        Image(painter = painterResource(id = eyePart), contentDescription = "")

        Image(painter = painterResource(id = R.drawable.mouth_w), contentDescription = "")

        Image(painter = painterResource(id = R.drawable.chins), contentDescription = "")


    }

}

fun randomColor(): Color {
    val red: Int = (0..255).random()
    val green: Int = (0..255).random()
    val blue : Int = (0..255).random()
    return Color(red, green, blue)
}




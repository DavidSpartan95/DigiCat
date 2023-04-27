package com.example.digicat.utilities

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.digicat.R
import com.example.digicat.dataBase.UserRepository
import com.example.digicat.screens.Screen
import com.example.digicat.screens.digiCatViewModel
import com.example.digicat.ui.theme.orbitronBold
import kotlinx.coroutines.Dispatchers

@Composable
fun DrawDigiCat(primeColor: Color, eyePart:Int, size: Int? = null) {

    var drawSize = 250

    if (size != null){ drawSize = size}

    Box(Modifier.size(drawSize.dp).padding(5.dp), contentAlignment = Alignment.TopCenter) {
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
@Composable
fun Title(name: String) {
    Text(text = name,fontFamily = orbitronBold, fontSize = 75.sp, color = Color.White)
}

@Composable
fun MenuButton(text: String, navController: NavController? = null, nav:String?=null, onClick: (() -> Unit)? = null){
    Box(
        modifier = Modifier.padding(8.dp)
    ) {

        Button(
            modifier = Modifier
                .height(50.dp)
                .widthIn(min = 32.dp)
            ,
            onClick = { navController?.navigate(route = nav?:"") ?:onClick?.invoke() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(219,137,39),
                contentColor = Color.White)
        ) {
            Text(text = text, fontFamily = orbitronBold, fontSize = 24.sp)
        }
    }
}
@Composable
fun ChangeColorButton(){

    Box(Modifier.padding(10.dp)) {

        Button(modifier = Modifier
            .height(50.dp)
            .widthIn(min = 32.dp)
            ,onClick = {
                digiCatViewModel.setColor()
            }, colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(219,137,39))) {
            Text(text = "Random Color",color = Color.White,fontFamily = orbitronBold, fontSize = 24.sp)
        }
    }

}

@Composable
fun ChangeEyeButton(onClick:(() -> Unit)){

    Box(Modifier.padding(10.dp)) {

        Button(modifier = Modifier
            .height(50.dp)
            .widthIn(min = 32.dp)
            ,onClick = {
                onClick.invoke()
            }, colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(219,137,39))) {
            Text(text = "Change Eyes",color = Color.White,fontFamily = orbitronBold, fontSize = 24.sp)
        }
    }
}

@Composable
fun DoneButton(onClick:(() -> Unit)){

    Box(Modifier.padding(5.dp)) {

        Button(modifier = Modifier
            .height(50.dp)
            .widthIn(min = 32.dp)
            ,onClick = {
                onClick.invoke()
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(219,137,39))){
            Text("DONE",color = Color.White,fontFamily = orbitronBold, fontSize = 24.sp)
        }
    }

}

@Composable
fun PaintAchviment(unlocked:Int,sun:Boolean,snow:Boolean) {

    Box(Modifier.size(75.dp), contentAlignment = Alignment.TopCenter) {
        if (unlocked == 1 && sun) {
            Image(painter = painterResource(id = R.drawable.sun), contentDescription = "")
        }
        if (unlocked == 2 && snow) {
            Image(painter = painterResource(id = R.drawable.snow), contentDescription = "")
        }
        if (unlocked == 3) {
            Image(painter = painterResource(id = R.drawable.bronze), contentDescription = "")
        }
        if (unlocked == 4) {
            Image(painter = painterResource(id = R.drawable.silver), contentDescription = "")
        }
        if (unlocked == 5) {
            Image(painter = painterResource(id = R.drawable.gold), contentDescription = "")
        }
        if (unlocked == 6) {
            Image(painter = painterResource(id = R.drawable.dimond), contentDescription = "")
        }
    }
}

@Composable
fun DeleteButton(userRepository: UserRepository, userName: String, function:(() -> Unit)) {
    Button(modifier = Modifier
        .heightIn(min = 30.dp)
        .widthIn(min = 80.dp),
        onClick = {
            userRepository.performDatabaseOperation(Dispatchers.IO) {
                userRepository.deleteUser(userName)
                function.invoke()
            }

        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(255,0,0),
            contentColor = Color.White)


    ) {
        Text(text = "Delete",fontFamily = orbitronBold, fontSize = 20.sp)
    }

}

@Composable
fun LoadButton(navController: NavController, userName: String){
    Button(modifier = Modifier
        .heightIn(min = 30.dp)
        .widthIn(min = 80.dp),
        onClick = {
            navController.navigate(route = "game_screen/$userName"){
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

@Composable
fun DisplayName(name:String){
    Text(
        text = name,
        fontFamily = orbitronBold,
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
    )
}
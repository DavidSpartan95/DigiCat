package com.example.digicat.utilities

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.digicat.R
import com.example.digicat.screens.digiCatViewModel
import com.example.digicat.ui.theme.orbitronBold

@Composable
fun DrawDigiCat(primeColor: Color, eyePart:Int, size: Int? = null) {
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

@Composable
fun ChangeColorButton(){
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

@Composable
fun ChangeEyeButton(onClick:(() -> Unit)){
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

@Composable
fun DoneButton(onClick:(() -> Unit)){
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
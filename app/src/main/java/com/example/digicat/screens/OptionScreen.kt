package com.example.digicat.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.digicat.api.fetchWeather
import com.example.digicat.api.temperatureViewModel
import com.example.digicat.ui.theme.orbitronBold
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@Composable
fun OptionScreen(navController: NavController) {
    val temperature by temperatureViewModel.temp.collectAsState()
    Surface(modifier = Modifier.fillMaxSize(),
        color = Color.Black) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            Arrangement.Top,Alignment.CenterHorizontally

            ){
            Text(
                modifier = Modifier.clickable {
                    navController.navigate(route = Screen.Home.route){
                        popUpTo(Screen.Home.route){
                            inclusive = true
                        }
                    }
                },
                text = "Option", color = Color.White, fontSize = 65.sp,fontFamily = orbitronBold)

        }
        Column(modifier = Modifier.fillMaxWidth(),
            Arrangement.Center, Alignment.CenterHorizontally) {

            MenuButton(text = "Fetch", onClick = {fetchWeather()})

        }
        Column(modifier = Modifier.fillMaxWidth(),
            Arrangement.Bottom, Alignment.CenterHorizontally) {

            Text(text = "Temperature is $temperature", fontFamily = orbitronBold, fontSize = 30.sp, color = Color.White)
        }

    }

}


//TODO put ViewModel in a seperate package
class TemperatureViewModel : ViewModel(){
    private val _temp = MutableStateFlow<String>("null")
    val temp: StateFlow<String> get() = _temp

    fun setTemp(newTemp: String){
        _temp.value = newTemp
        println("temp is " + temp.value)
        println("_temp is " +_temp.value)
    }
}


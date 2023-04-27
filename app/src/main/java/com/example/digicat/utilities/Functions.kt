package com.example.digicat.utilities

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.digicat.dataBase.User
import com.example.digicat.dataBase.UserRepository
import com.example.digicat.dataBase.userDigiCatData.DigiCatData
import com.example.digicat.screens.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// used in Create screen
fun randomColor(): Color {
    val red: Int = (0..255).random()
    val green: Int = (0..255).random()
    val blue : Int = (0..255).random()
    return Color(red, green, blue)
}

fun toastMessage(msg: String, context: Context){
    Toast.makeText(context,msg, Toast.LENGTH_LONG).show()
}

fun checkAndNavigate(
    navController: NavController,
    userRepository: UserRepository,
    username:String,
    context: Context,
    eyePart: Int,
    color: Color
){
    if (username.isNotEmpty() && username.length <= 8){

        userRepository.performDatabaseOperation(Dispatchers.IO) {

            val foundUser = userRepository.getUsers().find { it.name == username }

            if (foundUser == null){
                userRepository.insertUser(User(username,0, arrayOf(DigiCatData(eyePart,color))))

                CoroutineScope(Dispatchers.Main).launch {

                    navController.navigate(route = "game_screen/$username"){

                        popUpTo(Screen.Create.route){

                            inclusive = true

                        }
                    }
                }
            }else{
                CoroutineScope(Dispatchers.Main).launch {

                    toastMessage("User name already exist",context)

                }
            }
        }

    }else{
        if (username.length > 8) {
            toastMessage("name too long",context)
        }else{
            toastMessage("please insert name",context)
        }

    }
}
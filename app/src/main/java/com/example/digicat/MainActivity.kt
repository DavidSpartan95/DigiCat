package com.example.digicat

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.digicat.dataBase.AppDatabase
import com.example.digicat.dataBase.User
import com.example.digicat.dataBase.UserRepository
import com.example.digicat.screens.SetupNavGraph
import com.example.digicat.ui.theme.DigiCatTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var navController: NavHostController
        super.onCreate(savedInstanceState)
        val db = AppDatabase.getInstance(applicationContext)
        val userRepository = UserRepository(db, lifecycleScope)
        println(applicationContext .getDatabasePath ("my-app-db" ))


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContent {

            DigiCatTheme{
                // A surface container using the 'background' color from the theme
                Surface(
                    color = Color.Black
                ) {
                    navController = rememberNavController()
                    SetupNavGraph(navController = navController, userRepository)
                }
            }
        }
    }
}


package com.example.digicat

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.digicat.screens.SetupNavGraph
import com.example.digicat.ui.theme.DigiCatTheme

class MainActivity : ComponentActivity() {
/*TODO make 2 Buttons. 1 for Swe one fore Jp*/

    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var navController: NavHostController
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContent {
            DigiCatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = Color.Black
                ) {
                    navController = rememberNavController()
                    SetupNavGraph(navController = navController)
                }
            }
        }
    }
}
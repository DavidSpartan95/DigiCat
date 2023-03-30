package com.example.digicat

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.digicat.Screens.SetupNavGraph
import com.example.digicat.ui.theme.DigiCatTheme
import com.example.digicat.ui.theme.orbitronBold

class MainActivity : ComponentActivity() {
/*TODO make navigaton work on Buttons*/
/*COmMIT 2, Changed Color Theme, Text Size*/
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
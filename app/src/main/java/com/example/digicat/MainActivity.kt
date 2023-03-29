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
import com.example.digicat.ui.theme.DigiCatTheme
import com.example.digicat.ui.theme.orbitron
import com.example.digicat.ui.theme.orbitronBold

class MainActivity : ComponentActivity() {
/*TODO make navigaton work on Buttons*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContent {
            DigiCatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    //color is light/baby blue
                color = Color(204, 229, 255)
                ) {
                    Menu()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Menu() {

    Column(Modifier.fillMaxWidth(),Arrangement.Top,Alignment.CenterHorizontally) {

        Title("DigiCat")
    }

        Column(Modifier.fillMaxWidth(), Arrangement.Center, Alignment.CenterHorizontally) {

            MenuButton("START")

            MenuButton("OPTION")

        }
}

@Composable
fun Title(name: String) {
    Text(text = name,fontFamily = orbitronBold, fontSize = 75.sp)
}


@Composable
fun MenuButton(text: String){
    Box(
        modifier = Modifier.padding(8.dp)
    ) {

        Button(
            modifier = Modifier

                .fillMaxWidth(0.5F)
                .height(50.dp),
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(255, 218, 185),
                contentColor = Color(0,0,0))
        ) {
            Text(text = text, fontFamily = orbitronBold, fontSize = 20.sp)
        }
    }
}

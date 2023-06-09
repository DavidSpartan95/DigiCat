package com.example.digicat.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.digicat.dataBase.UserRepository

@Composable
fun SetupNavGraph(
    navController: NavHostController, userRepository: UserRepository
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ){
        composable(
            route = Screen.Home.route
        ){
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Option.route
        ){
            OptionScreen(navController = navController)
        }
        composable(
            route = Screen.Create.route
        ){
            CreateScreen(navController = navController,userRepository = userRepository)
        }
        composable(
            route = Screen.Game.route,
            arguments = listOf(navArgument("username"){
                type = NavType.StringType
                defaultValue = "???"
            })
        ){ backStackEntry ->
            GameScreen(userRepository = userRepository,backStackEntry.arguments?.getString("username")?:"")
        }
        composable(
            route = Screen.Load.route
        ){
            LoadScreen(navController = navController,userRepository = userRepository)
        }
    }

}
package com.example.digicat.screens

sealed class Screen(val route: String){
    object Home:Screen(route = "home_screen")
    object Option:Screen(route = "option_screen")
    object Create:Screen(route = "create_screen")
    object Game:Screen(route = "game_screen/{username}")
    object Load:Screen(route = "load_screen")
}
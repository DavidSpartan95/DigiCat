package com.example.digicat.Screens

sealed class Screen(val route: String){
    object Home:Screen(route = "home_screen")
    object Option:Screen(route = "option_screen")
}
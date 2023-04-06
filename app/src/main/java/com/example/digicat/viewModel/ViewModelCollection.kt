package com.example.digicat.viewModel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TemperatureViewModel : ViewModel(){
    private val _temp = MutableStateFlow<String>("null")
    val temp: StateFlow<String> get() = _temp

    fun setTemp(newTemp: String){
        _temp.value = newTemp
    }
}

class DigiCatColorViewModel : ViewModel(){
    private val _color =MutableStateFlow<Color>(Color.Green)
    val color: StateFlow<Color> get() = _color

    fun setColor(newColor: Color){
        _color.value = newColor
    }

}
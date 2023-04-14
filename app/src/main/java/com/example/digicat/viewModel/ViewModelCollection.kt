package com.example.digicat.viewModel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digicat.R
import com.example.digicat.dataBase.UserRepository
import com.example.digicat.dataBase.userDigiCatData.DigiCatData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TemperatureViewModel : ViewModel(){
    private val _temp = MutableStateFlow<String>("null")
    val temp: StateFlow<String> get() = _temp

    fun setTemp(newTemp: String){
        _temp.value = newTemp
    }
}

class DigiCatColorViewModel : ViewModel(){//This is going to change
    private val _color = MutableStateFlow<Color>(Color.Green)
    val color: StateFlow<Color> get() = _color

    fun setColor(newColor: Color){
        _color.value = newColor
    }

}
class GameViewModel() : ViewModel() {
    private val _userDraw = MutableStateFlow<DigiCatData?>(null)
    val userDraw: StateFlow<DigiCatData?> = _userDraw
    fun getModel(userRepository: UserRepository, username: String){
        viewModelScope.launch(Dispatchers.IO) {
            for (x in userRepository.getUsers()) {
                if (x.name == username) {
                    _userDraw.value = x.draw[0]
                    break
                }
            }
        }
    }

}

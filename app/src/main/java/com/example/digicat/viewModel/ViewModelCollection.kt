package com.example.digicat.viewModel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digicat.R
import com.example.digicat.dataBase.User
import com.example.digicat.dataBase.UserRepository
import com.example.digicat.utilities.randomColor
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

class DigiCatViewModel : ViewModel(){//This is going to change
private val _text = MutableStateFlow<String>("")
    val text: StateFlow<String> get() = _text

    private val _color = MutableStateFlow<Color>(Color(102,189,209))
    val color: StateFlow<Color> get() = _color

    private val _eyes = MutableStateFlow<Int>(R.drawable.eyes)
    val eyes: StateFlow<Int> get() = _eyes

    private val eyeParts = arrayOf(R.drawable.eyes,R.drawable.eyes2,R.drawable.eyes3,R.drawable.eyes4)
    private var selectNum = 0

    fun changeText(text:String){
        _text.value = text
    }fun changeEyeTest(){
        selectNum = (selectNum + 1) % eyeParts.size
        _eyes.value = eyeParts[selectNum]
    }fun setColor(){
        _color.value = randomColor()
    }

}
class GameViewModel() : ViewModel() {
    private val _userDraw = MutableStateFlow<User?>(null)
    val userDraw: StateFlow<User?> = _userDraw
    fun getModel(userRepository: UserRepository, username: String){
        viewModelScope.launch(Dispatchers.IO) {
            for (x in userRepository.getUsers()) {
                if (x.name == username) {
                    _userDraw.value = x
                    break
                }
            }
        }
    }

}

package com.example.digicat.dataBase.userDigiCatData

import androidx.room. TypeConverter
import com.google.gson. Gson

class DigiCatArrayTypeConverter {
    @TypeConverter
    fun fromInt(value: String): Array<DigiCatData>{

        return Gson().fromJson(value,Array<DigiCatData>::class.java)
    }

    @TypeConverter
    fun fromDigiCatArray(array: Array<DigiCatData>): String {
        return Gson().toJson(array)
    }

}
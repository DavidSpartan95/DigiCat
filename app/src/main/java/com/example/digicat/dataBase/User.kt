package com.example.digicat.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.digicat.dataBase.userDigiCatData.DigiCatArrayTypeConverter
import com.example.digicat.dataBase.userDigiCatData.DigiCatData

@Entity
data class User(
    var name: String,
    var points: Int,

    @TypeConverters(DigiCatArrayTypeConverter::class)
        var draw: Array<DigiCatData>,
    var achievements: Int = 0,
    var sunAchievement: Boolean = false,
    var snowAchievement: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}



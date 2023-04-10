package com.example.digicat.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    var name: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}
package com.example.digicat.dataBase
import  androidx.room. Dao
import androidx.room. Insert
import androidx.room. Query
import com.example.digicat.dataBase.userDigiCatData.DigiCatData

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Query("SELECT * FROM User")
    fun getAllUsers(): List<User>


}
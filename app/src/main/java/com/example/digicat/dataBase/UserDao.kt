package com.example.digicat.dataBase
import  androidx.room. Dao
import androidx.room.Delete
import androidx.room. Insert
import androidx.room. Query
import com.example.digicat.dataBase.userDigiCatData.DigiCatData

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Query("SELECT * FROM User")
    fun getAllUsers(): List<User>

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM User WHERE name = :name")
    fun deleteUserByName(name: String)

    @Query("UPDATE User SET points = points + 1 WHERE name = :name")
     fun incrementPoints(name: String)
}
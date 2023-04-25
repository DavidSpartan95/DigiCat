package com.example.digicat.dataBase
import androidx.room.*

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
    @Query("SELECT points FROM User WHERE name = :name")
    fun getPointsByName(name: String): Int
    //Old Achv
    @Query("SELECT sunAchievement FROM User WHERE name = :name")
    fun getSunUnlcok(name: String): Boolean
    @Query("UPDATE User SET sunAchievement = 1 WHERE name = :name")
    fun unlockSunAchv(name: String)
    // new
    @Query("UPDATE User SET achievements = achievements + 1 WHERE name = :name")
    fun incrementAchv(name: String)
    @Query("SELECT achievements FROM User WHERE name = :name")
    fun getAchvByName(name: String): Int

}
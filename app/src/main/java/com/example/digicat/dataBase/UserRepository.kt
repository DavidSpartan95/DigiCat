package com.example.digicat.dataBase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class UserRepository (private val appDatabase : AppDatabase, private val
coroutineScope : CoroutineScope ) {
    fun insertUser(user: User) {
        appDatabase .userDao().insertUser(user)
    }
    fun getUsers(): List<User> {
        return appDatabase .userDao().getAllUsers ()
    }

    fun deleteUser(name: String) {
        appDatabase.userDao().deleteUserByName(name)
    }
    fun addPoint(name: String){
        appDatabase.userDao().incrementPoints(name)
    }
    fun fetchPoints(name: String): Int {
        return appDatabase.userDao().getPointsByName(name)
    }
    fun unlockSunAchvivments(name:String){//OLD
        appDatabase.userDao().unlockSunAchv(name)
    }
    fun unlockSnowAchvivments(name:String){//OLD
        appDatabase.userDao().unlockSnowAchv(name)
    }
    fun unlockAchvimentsNew(name:String){//New
        appDatabase.userDao().incrementAchv(name)
    }
    fun fetchSunUnlock(name: String): Boolean {//OLD
        return appDatabase.userDao().getSunUnlcok(name)
    }
    fun fetchUnlocksNew(name: String): Int{
        return appDatabase.userDao().getAchvByName(name)
    }
    fun performDatabaseOperation (dispatcher: CoroutineDispatcher,
                                  databaseOperation : suspend () -> Unit) {
        coroutineScope .launch(dispatcher) {
            databaseOperation ()
        }
    }

}

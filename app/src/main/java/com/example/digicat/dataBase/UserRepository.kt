package com.example.digicat.dataBase

import com.example.digicat.dataBase.userDigiCatData.DigiCatData
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
    fun performDatabaseOperation (dispatcher: CoroutineDispatcher,
                                  databaseOperation : suspend () -> Unit) {
        coroutineScope .launch(dispatcher) {
            databaseOperation ()
        }
    }

}

package com.example.digicat.dataBase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines. Dispatchers
import kotlinx.coroutines.launch

class UserRepository (private val appDatabase : AppDatabase, private val
coroutineScope : CoroutineScope ) {
    fun insertUser(user: User) {
        appDatabase .userDao().insertUser(user)
    }
    fun getUsers(): List<User> {
        return appDatabase .userDao().getAllUsers ()
    }
    fun performDatabaseOperation (dispatcher: CoroutineDispatcher,
                                  databaseOperation : suspend () -> Unit) {
        coroutineScope .launch(dispatcher) {
            databaseOperation ()
        }
    }
}

package com.example.datossinmvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.datossinmvvm.data.dao.UserDao
import com.example.datossinmvvm.data.model.User


@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}
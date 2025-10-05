package com.example.datossinmvvm.data.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.util.List

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    suspend fun getAll(): List<User>

    @Insert
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM User ORDER BY uid DESC LIMIT 1")
    suspend fun getLastUser(): User
}
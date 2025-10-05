package com.example.datossinmvvm.data.client

import androidx.room.*

@Dao
interface ClienteDao {
    @Query("SELECT * FROM Cliente")
    suspend fun getAll(): List<Cliente>

    @Insert
    suspend fun insert(cliente: Cliente)

    @Update
    suspend fun update(cliente: Cliente)

    @Delete
    suspend fun delete(cliente: Cliente)

    @Query("SELECT * FROM Cliente ORDER BY uid DESC LIMIT 1")
    suspend fun getLastCliente(): Cliente

    @Query("SELECT COUNT(*) FROM Cliente")
    suspend fun getCount(): Int

    @Query("SELECT * FROM Cliente WHERE uid = :id")
    suspend fun getById(id: Int): Cliente?
}

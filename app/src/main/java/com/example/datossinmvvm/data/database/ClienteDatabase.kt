package com.example.datossinmvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.datossinmvvm.data.model.Cliente
import com.example.datossinmvvm.data.dao.ClienteDao

@Database(entities = [Cliente::class], version = 1)
abstract class ClienteDatabase: RoomDatabase() {
    abstract fun clienteDao(): ClienteDao
}
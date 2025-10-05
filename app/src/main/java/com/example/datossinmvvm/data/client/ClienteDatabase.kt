package com.example.datossinmvvm.data.client

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Cliente::class], version = 1)
abstract class ClienteDatabase: RoomDatabase() {
    abstract fun clienteDao(): ClienteDao
}
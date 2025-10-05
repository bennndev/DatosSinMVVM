package com.example.datossinmvvm.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cliente(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "nombres") val nombres: String?,
    @ColumnInfo(name = "apellidos") val apellidos: String?,
    @ColumnInfo(name = "telefono") val telefono: String?,
    @ColumnInfo(name = "correo") val correo: String?
)
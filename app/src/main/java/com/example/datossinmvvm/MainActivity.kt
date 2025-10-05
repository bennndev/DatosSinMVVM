package com.example.datossinmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.datossinmvvm.data.database.ClienteDatabase
import com.example.datossinmvvm.ui.theme.DatosSinMVVMTheme
import com.example.datossinmvvm.ui.theme.screens.client.ScreenCliente

class MainActivity : ComponentActivity() {

    private lateinit var database: ClienteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Room.databaseBuilder(
            applicationContext,
            ClienteDatabase::class.java,
            "cliente_database"
        ).build()

        setContent {
            DatosSinMVVMTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenCliente(database)
                }
            }
        }
    }
}
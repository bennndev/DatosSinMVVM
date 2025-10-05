package com.example.datossinmvvm.ui.theme.screens.client

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.datossinmvvm.data.client.Cliente
import com.example.datossinmvvm.data.database.ClienteDatabase
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenCliente(database: ClienteDatabase) {
    var clientes by remember { mutableStateOf<List<Cliente>>(emptyList()) }
    var totalClientes by remember { mutableStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }
    var clienteToEdit by remember { mutableStateOf<Cliente?>(null) }
    var clienteToDelete by remember { mutableStateOf<Cliente?>(null) }

    val scope = rememberCoroutineScope()

    // Cargar clientes al iniciar
    LaunchedEffect(Unit) {
        cargarClientes(database) { lista, total ->
            clientes = lista
            totalClientes = total
        }
    }

    // Función para recargar datos
    fun recargarDatos() {
        scope.launch {
            cargarClientes(database) { lista, total ->
                clientes = lista
                totalClientes = total
            }
        }
    }

    Scaffold(
        containerColor = Color(0xFFF5F5F5)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Título
            Text(
                text = "Clientes",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botones de búsqueda y registro
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Botón Buscar
                OutlinedButton(
                    onClick = { /* TODO: Implementar búsqueda */ },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color(0xFFE0E0E0),
                        contentColor = Color(0xFF757575)
                    ),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Buscar Cliente")
                }

                // Botón Registrar
                Button(
                    onClick = {
                        clienteToEdit = null
                        showDialog = true
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2E5B8C)
                    ),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Agregar",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Registrar Cliente")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tarjetas de estadísticas
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Card Totales
                StatCard(
                    title = "Totales",
                    count = totalClientes,
                    modifier = Modifier.weight(1f)
                )

                // Card Activos
                StatCard(
                    title = "Activos",
                    count = totalClientes,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Card Inactivos
            StatCard(
                title = "Inactivos",
                count = 0,
                modifier = Modifier.width(180.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de clientes
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(clientes) { cliente ->
                    ClienteItem(
                        cliente = cliente,
                        onEdit = {
                            clienteToEdit = cliente
                            showDialog = true
                        },
                        onDelete = {
                            clienteToDelete = cliente
                        }
                    )
                }
            }
        }
    }

    // Dialog de Registro/Edición
    if (showDialog) {
        ClienteDialog(
            cliente = clienteToEdit,
            onDismiss = { showDialog = false },
            onSave = { cliente ->
                scope.launch {
                    if (clienteToEdit == null) {
                        database.clienteDao().insert(cliente)
                    } else {
                        database.clienteDao().update(cliente)
                    }
                    showDialog = false
                    recargarDatos()
                }
            }
        )
    }

    // Dialog de Confirmación de Eliminación
    if (clienteToDelete != null) {
        DeleteConfirmationDialog(
            clienteName = "${clienteToDelete!!.nombres} ${clienteToDelete!!.apellidos}",
            onConfirm = {
                scope.launch {
                    database.clienteDao().delete(clienteToDelete!!)
                    clienteToDelete = null
                    recargarDatos()
                }
            },
            onDismiss = {
                clienteToDelete = null
            }
        )
    }
}

suspend fun cargarClientes(
    database: ClienteDatabase,
    onResult: (List<Cliente>, Int) -> Unit
) {
    val lista = database.clienteDao().getAll()
    val total = database.clienteDao().getCount()
    onResult(lista, total)
}

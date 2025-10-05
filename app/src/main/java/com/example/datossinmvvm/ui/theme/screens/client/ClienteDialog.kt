package com.example.datossinmvvm.ui.theme.screens.client

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.datossinmvvm.data.client.Cliente

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClienteDialog(
    cliente: Cliente?,
    onDismiss: () -> Unit,
    onSave: (Cliente) -> Unit
) {
    var nombres by remember { mutableStateOf(cliente?.nombres ?: "") }
    var apellidos by remember { mutableStateOf(cliente?.apellidos ?: "") }
    var telefono by remember { mutableStateOf(cliente?.telefono ?: "") }
    var correo by remember { mutableStateOf(cliente?.correo ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    val nuevoCliente = Cliente(
                        uid = cliente?.uid ?: 0,
                        nombres = nombres,
                        apellidos = apellidos,
                        telefono = telefono,
                        correo = correo
                    )
                    onSave(nuevoCliente)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2E5B8C)
                ),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(text = if (cliente == null) "Registrar" else "Actualizar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        },
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (cliente == null) "Registrar nuevo cliente" else "Editar cliente",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                IconButton(onClick = onDismiss) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Cerrar"
                    )
                }
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = "Información básica del cliente",
                    fontSize = 14.sp,
                    color = Color(0xFF757575)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = nombres,
                    onValueChange = { nombres = it },
                    label = { Text("Nombres") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = apellidos,
                    onValueChange = { apellidos = it },
                    label = { Text("Apellidos") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = { Text("Teléfono") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
        },
        containerColor = Color.White,
        tonalElevation = 4.dp,
        shape = RoundedCornerShape(28.dp)
    )
}

package net.iesseveroochoa.victorsanchez.t05otroscomponentes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource

// Pantalla principal con funcion composable body
@Composable
fun Body(
    ejercicioSeleccionado: Int,
    enEjercicioSeleccionado: (Int) -> Unit,
    innerPadding: PaddingValues
) {

    val context = LocalContext.current
    val listaEjercicios = stringArrayResource(R.array.opciones) // Recupera las opciones desde strings.xml

    // Contenedor principal
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        var expanded by remember { mutableStateOf(false) }

        // Botón desplegable para mostrar el menú
        Box(modifier = Modifier.wrapContentSize()) {
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = stringResource(R.string.men_desplegable))
            }

            // Menú desplegable con las opciones de ejercicios
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listaEjercicios.forEachIndexed { index, exercise ->
                    DropdownMenuItem(
                        text = { Text(exercise) },
                        onClick = {
                            enEjercicioSeleccionado(index)
                            expanded = false
                            // Mostrar el Toast cuando se selecciona un ejercicio
                            Toast.makeText(context,
                                context.getString(R.string.has_elegido, exercise), Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }

        // Espacio entre el menú y el contenido
        Spacer(modifier = Modifier.height(16.dp))

        // Contenido según la opción seleccionada
        when (ejercicioSeleccionado) {
            0 -> PedidoPizzaScreen()
            1 -> ColorPickerScreen()
            else -> Text(text = stringResource(R.string.selecciona_un_ejercicio))
        }
    }
}



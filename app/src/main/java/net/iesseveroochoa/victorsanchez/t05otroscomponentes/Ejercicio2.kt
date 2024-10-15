package net.iesseveroochoa.victorsanchez.t05otroscomponentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource

// Pantalla de selección de color
@Composable
fun ColorPickerScreen() {
    var red by remember { mutableStateOf(149f) }
    var green by remember { mutableStateOf(208f) }
    var blue by remember { mutableStateOf(115f) }

    @Composable
    // Función para crear filas con texto y slider
    fun RowWithSlider(label: String, value: Float, onValueChange: (Float) -> Unit) {
        // Fila con texto y slider
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(text = label, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.width(8.dp))
            Slider(
                value = value,
                onValueChange = onValueChange,
                valueRange = 0f..255f,
                modifier = Modifier.weight(1f)
            )
        }
    }

    // Color resultante de los valores RGB
    val currentColor = Color(
        red = red.toInt(),
        green = green.toInt(),
        blue = blue.toInt()
    )
    // Contenedor principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = stringResource(R.string.ejercicio_2),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        // Espacio entre el título y los sliders
        Spacer(modifier = Modifier.height(16.dp))

        // Crear filas con sliders para RGB
        RowWithSlider("R", red) { red = it }
        RowWithSlider("G", green) { green = it }
        RowWithSlider("B", blue) { blue = it }

        // Mostrar valores actuales del color
        Text(
            text = "Color: (${red.toInt()}, ${green.toInt()}, ${blue.toInt()})",
            style = MaterialTheme.typography.bodyLarge
        )
        // Espacio entre el color y el botón
        Spacer(modifier = Modifier.height(16.dp))

        // Caja de color que ocupa el espacio restante con esquinas redondeadas
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(16.dp)) // Esquinas redondeadas con un radio de 16.dp
                .background(currentColor)
        )
    }
}

// Preview de la pantalla
@Preview(showBackground = true)
@Composable
fun ColorPickerScreenPreview() {
    ColorPickerScreen()
}



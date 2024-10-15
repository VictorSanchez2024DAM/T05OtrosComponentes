package net.iesseveroochoa.victorsanchez.t05otroscomponentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


// Pantalla de pedido de pizza
@Composable
fun PedidoPizzaScreen() {
    var total by remember { mutableStateOf(10) } // Pedido base de 10 euros
    val context = LocalContext.current
    val ingredientes = context.resources.getStringArray(R.array.ingredients_array).toList()
    val precios = listOf(2, 2, 2) // Cada ingrediente a 2 euros
    val ingredientesSeleccionados = remember { mutableStateListOf<Boolean>(false, false, false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top

    ) {
        // Título de la pantalla
        Text(
            text = stringResource(R.string.pedido_de_pizza),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())

        // Padding de 16dp entre el título y los ingredientes
        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar los ingredientes con checkboxes
        ingredientes.forEachIndexed { index, ingrediente ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .toggleable(
                        value = ingredientesSeleccionados[index],
                        onValueChange = {
                            ingredientesSeleccionados[index] = it
                            total = 10 + ingredientesSeleccionados.indices.sumOf { i ->
                                if (ingredientesSeleccionados[i]) precios[i] else 0
                            }
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = ingredientesSeleccionados[index], onCheckedChange = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = ingrediente, style = MaterialTheme.typography.bodyLarge)
            }
        }

        // Padding de 32dp entre los ingredientes y el total
        Spacer(modifier = Modifier.height(32.dp))

        // Mostrar el total
        Text(text = stringResource(R.string.total, total), style = MaterialTheme.typography.displayLarge)

        // Fila para mostrar las imagenes de los ingredientes seleccionados
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ingredientesSeleccionados.forEachIndexed { index, seleccionado ->
                if (seleccionado) {
                    Image(
                        painter = painterResource(id = when (index) {
                            0 -> R.drawable.peperoni
                            1 -> R.drawable.champinon
                            2 -> R.drawable.queso
                            else -> R.drawable.pizza_base
                        }),
                        contentDescription = ingredientes[index],
                        modifier = Modifier
                            .size(80.dp)
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}

// Preview de la pantalla
@Preview(showBackground = true)
@Composable
fun PedidoPizzaScreenPreview() {
    PedidoPizzaScreen()
}

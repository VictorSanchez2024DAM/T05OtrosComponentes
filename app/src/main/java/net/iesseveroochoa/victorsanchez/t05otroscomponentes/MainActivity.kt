package net.iesseveroochoa.victorsanchez.t05otroscomponentes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import net.iesseveroochoa.victorsanchez.t05otroscomponentes.ui.theme.T05OtrosComponentesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            T05OtrosComponentesTheme {

                // Llamada a la función App
                App()

            }
        }
    }

    // Función principal de la aplicación
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun App() {
        val listaEjercicios = stringArrayResource(R.array.opciones) // Recupera el array de ejercicios
        var ejercicioSeleccionado by remember { mutableStateOf(0) } // Estado del ejercicio seleccionado
        val context = LocalContext.current

        // Estado necesario para manejar Snackbar
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()

        // Scaffold con la TopBar y el FloatingActionButton
        T05OtrosComponentesTheme {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = listaEjercicios[ejercicioSeleccionado]) // Mostrar el ejercicio actual en la TopBar
                        }
                    )
                },
                // Botón flotante para mostrar el Snackbar
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        // Mostrar el Snackbar cuando se pulsa el FloatingActionButton
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = getString(
                                    R.string.snackbar_message,
                                    listaEjercicios[ejercicioSeleccionado]
                                )
                            )
                        }
                    }) {
                        Icon(Icons.Default.Add, contentDescription = stringResource(R.string.bot_n_flotante))
                    }
                },
                snackbarHost = {
                    SnackbarHost(hostState = snackbarHostState)
                }
            ) { innerPadding ->
                // Contenido principal de la aplicación
                Body(
                    ejercicioSeleccionado = ejercicioSeleccionado,
                    enEjercicioSeleccionado = { newIndex -> ejercicioSeleccionado = newIndex },
                    innerPadding = innerPadding
                )
            }
        }
    }

    // Funcion preview
    @Preview(showBackground = true)
    @Composable
    fun PreviewApp() {
        App()
    }


}

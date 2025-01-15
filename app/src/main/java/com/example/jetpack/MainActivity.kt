package com.example.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MensajitouWu("Jose")
            InputNumbersScreen()
        }
    }
}

@Composable
private fun MensajitouWu(name: String) {
    Column(
        modifier = Modifier
            .background(color = Color(0xFF6852A5))
            .fillMaxWidth()
            .padding(top = 55.dp)
    ) {
        Text(text = "Hello, " + name)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Para utilizar la calculadora introduce dos numeros y haz click en Sumar para obtener el resultado")
    }
}


@Composable
fun InputNumbersScreen() {
    //Las variables
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String>("Resultado:") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Entrada para el primer número
        OutlinedTextField(
            value = number1,
            onValueChange = { newValue ->
                //Para que ningun gracioso meta mas de un punto en los decimales
                if (newValue.all { it.isDigit() || it == '.' } && newValue.count { it == '.' } <= 1) {
                    number1 = newValue
                }

            },
            placeholder = { Text("Primer número") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Entrada para el segundo número
        OutlinedTextField(
            value = number2,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() || it == '.' } && newValue.count { it == '.' } <= 1) {
                    number2 = newValue
                }
            },
            placeholder = { Text("Segundo número") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = result,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para realizar la suma
        Button(onClick = {
            val num1 = number1.toBigDecimalOrNull()
            val num2 = number2.toBigDecimalOrNull()

            if (num1 != null && num2 != null) {
                result = "Resultado: " + (num1 + num2)
            } else {
                result = "Resultado: nulo, introduce dos números"
            }
        }) {
            Text("Sumar - PSOE")
        }
    }
}
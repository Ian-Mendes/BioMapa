package br.ufrn.biomapa.ui.screens   // antes era br.ufrn.biomapa.biomapa.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

fun isFormularioValido(
    nome: String,
    descricao: String,
    latitude: String,
    longitude: String
): Boolean {
    return nome.isNotBlank() &&
            descricao.isNotBlank() &&
            latitude.toDoubleOrNull() != null &&
            longitude.toDoubleOrNull() != null
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaCadastro(
    nome: String,
    descricao: String,
    propriedades: String,
    latitude: String,
    longitude: String,
    onNomeChange: (String) -> Unit,
    onDescricaoChange: (String) -> Unit,
    onPropriedadesChange: (String) -> Unit,
    onLatitudeChange: (String) -> Unit,
    onLongitudeChange: (String) -> Unit,
    onSalvar: () -> Unit,
    onVoltar: () -> Unit
) {
    val isValido = isFormularioValido(nome, descricao, latitude, longitude)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cadastrar Planta") },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = nome,
                onValueChange = onNomeChange,
                label = { Text("Nome da planta *") },
                isError = nome.isBlank(),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = descricao,
                onValueChange = onDescricaoChange,
                label = { Text("Descrição *") },
                isError = descricao.isBlank(),
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            OutlinedTextField(
                value = propriedades,
                onValueChange = onPropriedadesChange,
                label = { Text("Propriedades medicinais") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            OutlinedTextField(
                value = latitude,
                onValueChange = onLatitudeChange,
                label = { Text("Latitude *") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = latitude.toDoubleOrNull() == null,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = longitude,
                onValueChange = onLongitudeChange,
                label = { Text("Longitude *") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = longitude.toDoubleOrNull() == null,
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = onSalvar,
                enabled = isValido,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Salvar planta")
            }
        }
    }
}

@Preview
@Composable
fun TelaCadastroPreview() {
    MaterialTheme {
        TelaCadastro(
            nome = "Samambaia",
            descricao = "Planta de sombra",
            propriedades = "Calmante",
            latitude = "-5.84",
            longitude = "-35.20",
            onNomeChange = {}, onDescricaoChange = {}, onPropriedadesChange = {},
            onLatitudeChange = {}, onLongitudeChange = {},
            onSalvar = {}, onVoltar = {}
        )
    }
}

@Preview
@Composable
fun TelaCadastroVazioPreview() {
    MaterialTheme {
        TelaCadastro(
            nome = "", descricao = "", propriedades = "",
            latitude = "", longitude = "",
            onNomeChange = {}, onDescricaoChange = {}, onPropriedadesChange = {},
            onLatitudeChange = {}, onLongitudeChange = {},
            onSalvar = {}, onVoltar = {}
        )
    }
}

package br.ufrn.biomapa.ui.screens   // antes era br.ufrn.biomapa.biomapa.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.ufrn.biomapa.domain.Planta
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaMapa(
    plantas: List<Planta>,
    onCadastrarClick: () -> Unit,
    onPlantaClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("BioMapa — Mapa da UFRN") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onCadastrarClick) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Cadastrar nova planta"
                )
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            Box(
                modifier = Modifier.fillMaxWidth().weight(1f),
                contentAlignment = Alignment.Center
            ) {
                if (plantas.isEmpty()) {
                    Text("Nenhuma planta cadastrada ainda. Toque no + para adicionar.")
                } else {
                    Text("Mapa da UFRN — ${plantas.size} plantas cadastradas")
                }
            }

            LazyColumn {
                items(plantas, key = { it.id }) { planta ->
                    ListItem(
                        headlineContent = { Text(planta.nome) },
                        supportingContent = { Text(planta.descricao) },
                        modifier = Modifier.clickable { onPlantaClick(planta.id) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TelaMapaPreview() {
    MaterialTheme {
        TelaMapa(
            plantas = listOf(
                Planta("1", "Samambaia", "Sombra", "Calmante", null, -5.84, -35.20, "u1"),
                Planta("2", "Cacto", "Sol", "Diurético", null, -5.85, -35.21, "u1")
            ),
            onCadastrarClick = {},
            onPlantaClick = {}
        )
    }
}

@Preview
@Composable
fun TelaMapaVaziaPreview() {
    MaterialTheme {
        TelaMapa(plantas = emptyList(), onCadastrarClick = {}, onPlantaClick = {})
    }
}

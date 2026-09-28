package org.example.biomapa

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun PlantMapCard(
    plantas: List<Planta>,
    modifier: Modifier = Modifier
)

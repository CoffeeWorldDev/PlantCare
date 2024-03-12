package com.example.plantcare.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource

@Composable
fun PlantCareIconButton(
    iconImage : ImageVector,
    contentDescription : Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier){
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = iconImage,
            contentDescription = stringResource(id = contentDescription)
        )
    }
}
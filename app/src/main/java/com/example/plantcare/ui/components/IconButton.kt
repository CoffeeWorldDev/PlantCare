package com.example.plantcare.ui.components

import android.util.Log
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

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
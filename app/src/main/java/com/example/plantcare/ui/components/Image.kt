package com.example.plantcare.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.plantcare.R

@Composable
fun PlantCareImage(
    imageUrl: Any,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        placeholder = painterResource(id = R.drawable.baseline_image_24),
        modifier = modifier.clip(RoundedCornerShape(3)),
        contentScale = ContentScale.Crop,
    )
}
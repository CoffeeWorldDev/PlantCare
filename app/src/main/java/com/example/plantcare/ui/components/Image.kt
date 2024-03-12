package com.example.plantcare.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.plantcare.R

@Composable
fun PlantCareImage(
    imageUrl: Any,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    val photo = if (imageUrl == "") {
        R.drawable.placeholderimage
    } else { imageUrl }

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(photo)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        placeholder = painterResource(id = R.drawable.baseline_image_24),
        fallback = painterResource(id = R.drawable.placeholderimage),
        modifier = modifier.clip(RoundedCornerShape(3)),
        contentScale = ContentScale.Crop,
    )
}
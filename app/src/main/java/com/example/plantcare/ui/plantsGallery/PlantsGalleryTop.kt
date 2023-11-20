package com.example.plantcare.ui.plantsGallery

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.ui.util.TitlesBackgroundShape


@Composable
fun PlantsGalleryTop(
    plants: Map<Plants?, List<Tasks>>?,
    modifier: Modifier,
    onValueChange: (Int) -> Unit
) {
    val plantsList = plants?.keys?.toList()
    val primaryColor = MaterialTheme.colorScheme.primary
    val onPrimaryColor = MaterialTheme.colorScheme.onPrimary
    val primaryColorContainer = MaterialTheme.colorScheme.primaryContainer
    val onPrimaryColorContainer = MaterialTheme.colorScheme.onPrimaryContainer
    val titleLarge = MaterialTheme.typography.titleLarge
    Row (Modifier.padding(0.dp, 10.dp, 0.dp, 40.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Card(
            shape = TitlesBackgroundShape(250f, 110f),
            colors = CardDefaults.cardColors(containerColor = primaryColor),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            modifier = Modifier
                .weight(6f)

        ) {
            Box(
                modifier = Modifier.height(100.dp),
                contentAlignment = Alignment.Center
                ) {
                Text(
                    text = "You have ${plantsList?.size}\nplants",
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    lineHeight = 29.sp,
                    color = onPrimaryColor,
                    modifier = Modifier
                        .padding(10.dp, 0.dp),
                    fontStyle = FontStyle.Italic,
                    style = titleLarge
                )
            }


        }

        Column(
            modifier = Modifier
                .weight(1f)
                .height(100.dp),
            verticalArrangement = Arrangement.SpaceBetween) {
            IconButton(
                //TODO create search plants gallery page
                modifier = Modifier
                    .weight(0.5f)
                    .padding(0.dp, 10.dp, 12.dp, 5.dp)
                    .height(45.dp)
                    .width(45.dp),
                onClick = { Log.d("Click", "IconExample") }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Home Icon"
                )
            }

            IconButton(
                //TODO create sorting plants gallery page
                modifier = Modifier
                    .weight(0.5f)
                    .padding(0.dp, 5.dp, 12.dp, 5.dp)
                    .height(45.dp)
                    .width(45.dp),
                onClick = { Log.d("Click", "IconExample") }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Home Icon"
                )
           }
       }
    }
}
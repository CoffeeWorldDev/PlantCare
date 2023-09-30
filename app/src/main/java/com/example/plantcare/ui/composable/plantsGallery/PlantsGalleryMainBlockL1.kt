package com.example.plantcare.ui.composable.plantsGallery

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.plantcare.R
import com.example.plantcare.data.model.Plants


@Composable
fun PlantsGalleryMainBlockL1(plants: List<Plants>) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val onPrimaryColor = MaterialTheme.colorScheme.onPrimary
    val shape = RoundedCornerShape(topStartPercent = 50, bottomStartPercent = 50)
    val headlineLarge = MaterialTheme.typography.headlineLarge
    LazyColumn(){ items(plants.size) {
        Row(modifier = Modifier.padding(15.dp, 0.dp,0.dp, 20.dp)) {
            Image(painter = painterResource(id = R.drawable.baseline_image_24),
                contentDescription = "plant image",
                modifier = Modifier
                    .border(1.dp, color = Color.Black)
                    .weight(1.1f)
                    .height(95.dp)
            )
            Column(modifier = Modifier
                .weight(3f)
                .padding(10.dp, 0.dp, 0.dp, 0.dp),
                horizontalAlignment = Alignment.End) {
                Text(text = plants[it].name,
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 10.dp, 0.dp)
                        .fillMaxWidth(),
                    style = headlineLarge,
                    textAlign = TextAlign.Center)
                Divider(thickness = 1.dp,
                    modifier = Modifier.padding(0.dp, 0.dp,0.dp, 10.dp))
                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 10.dp, 0.dp)
                        .fillMaxWidth()) {
                    plants[it].age?.let { it1 ->
                        Text(text = "${it1} days old" ,
                             modifier = Modifier)
                    }
                    plants[it].type?.let { it1 ->
                        Text(text = it1,
                             modifier = Modifier)
                    }
                }
            }
        }
    }

    }
}
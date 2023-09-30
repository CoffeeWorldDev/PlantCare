package com.example.plantcare.ui.composable.home


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Calendar

fun AddDay(day: Int):String{
    val time = Calendar.getInstance().time
    val ca = Calendar.getInstance()
    ca.time = time
    ca.add(Calendar.DAY_OF_YEAR, day)
    val formatter = SimpleDateFormat("MMM\ndd")
    val current = formatter.format(ca.time)
    Log.d("Click", current)
    return current
}


@Composable
fun HomeTop(modifier: Modifier){
    val primaryColor = MaterialTheme.colorScheme.primary
    val onPrimaryColor = MaterialTheme.colorScheme.onPrimary
    val primaryColorContainer = MaterialTheme.colorScheme.primaryContainer
    val onPrimaryColorContainer = MaterialTheme.colorScheme.onPrimaryContainer
    val shape = CircleShape
    Row(Modifier.padding(0.dp, 10.dp, 0.dp, 40.dp),
        horizontalArrangement = Arrangement.SpaceEvenly) {
        LazyRow(modifier = Modifier.weight(6f),
                horizontalArrangement = Arrangement.SpaceEvenly
        ){
            items(6){
                Card(shape = shape,
                     colors = CardDefaults.cardColors(containerColor = primaryColorContainer),
                     elevation = CardDefaults.cardElevation(
                                 defaultElevation = 5.dp
                                 )
                ) {
                    Text(
                        text = AddDay(it),
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        lineHeight = 19.sp,
                        color = onPrimaryColorContainer,
                        //TODO see about flexible size
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
        IconButton(
            //TODO create sorting menu home page
            modifier = Modifier
                .weight(0.5f)
                .padding(0.dp, 5.dp, 12.dp, 5.dp)
                .height(45.dp)
                .width(45.dp),
            onClick = { Log.d("Click", "IconExample")}) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Home Icon"
            )
        }
    }
}
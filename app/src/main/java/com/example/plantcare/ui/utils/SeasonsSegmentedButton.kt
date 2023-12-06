package com.example.plantcare.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//items: List<String>,
//defaultSelectedItemIndex: Int = 0,
//useFixedWidth: Boolean = false,
//itemWidth: Dp = 120.dp,
//cornerRadius: Int = 24,
//onItemSelection: (selectedItemIndex: Int) -> Unit
//) {
//    val selectedIndex = remember { mutableStateOf(defaultSelectedItemIndex) }
//    val itemIndex = remember { mutableStateOf(defaultSelectedItemIndex) }
//

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeasonsSegmentedButton(
    modifier: Modifier = Modifier,
    currentSeason: String,
    defaultSelectedItemIndex: Int = 0,
    cornerRadius: Int = 50,
    onItemSelection: (selectedItem: String) -> Unit
) {
    var currentSeason by remember { mutableStateOf(currentSeason)}
    val seasons = listOf("summer", "winter", "dormant")
    val selectedIndex = remember { mutableStateOf(currentSeason) }
    val itemIndex = remember { mutableStateOf(defaultSelectedItemIndex) }
Box(modifier = Modifier.fillMaxWidth()
    .padding(0.dp, 10.dp),
    contentAlignment = Alignment.Center) {

    Card(
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .height(38.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (currentSeason == selectedIndex.value) {

                MaterialTheme.colorScheme.primaryContainer
            } else {

                MaterialTheme.colorScheme.primary
            }
        ),
        shape = RoundedCornerShape(cornerRadius)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary),
            horizontalArrangement = Arrangement.Center
        ) {
            seasons.forEachIndexed { index, item ->
                //itemIndex.value = index
                Card(
                    modifier = modifier
                        .weight(1f)
                        .padding(2.dp),
                    onClick = {
                        selectedIndex.value = item
                        onItemSelection(selectedIndex.value)
                    },
                    colors = CardDefaults.cardColors(
                        containerColor = if (selectedIndex.value == item) {
                            Color.White
                        } else {
                            MaterialTheme.colorScheme.primary
                        },
                        contentColor = if (selectedIndex.value == item)
                            MaterialTheme.colorScheme.scrim
                        else
                            MaterialTheme.colorScheme.onSecondary
                    ),
                    shape = when (index) {
                        0 -> RoundedCornerShape(
                            topStartPercent = cornerRadius,
                            topEndPercent = cornerRadius,
                            bottomStartPercent = cornerRadius,
                            bottomEndPercent = cornerRadius
                        )

                        seasons.size - 1 -> RoundedCornerShape(
                            topStartPercent = cornerRadius,
                            topEndPercent = cornerRadius,
                            bottomStartPercent = cornerRadius,
                            bottomEndPercent = cornerRadius
                        )

                        else -> RoundedCornerShape(
                            topStartPercent = 0,
                            topEndPercent = 0,
                            bottomStartPercent = 0,
                            bottomEndPercent = 0
                        )
                    },
                ) {
                    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = item,
                            style = LocalTextStyle.current.copy(
                                fontSize = 14.sp,
                                fontWeight = if (selectedIndex.value == item)
                                    LocalTextStyle.current.fontWeight
                                else
                                    FontWeight.Normal,
                                color = if (selectedIndex.value == item)
                                    MaterialTheme.colorScheme.scrim
                                else
                                    MaterialTheme.colorScheme.onSecondary
                            ),
                            textAlign = TextAlign.Center,

                            )
                    }
                }
            }
        }
    }
}
}

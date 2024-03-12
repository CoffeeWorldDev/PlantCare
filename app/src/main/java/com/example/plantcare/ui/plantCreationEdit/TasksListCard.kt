package com.example.plantcare.ui.plantCreationEdit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.plantcare.R
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.ui.navigation.PlantSections
import com.example.plantcare.ui.utils.AddFloatingBtn
import com.example.plantcare.ui.utils.EmptyListMsg
import com.example.plantcare.ui.utils.ReadOnlyTaskList
import com.example.plantcare.ui.utils.SeasonsSegmentedButton


@Composable
fun TasksListCard(
    plantId: Long,
    tasks : List<Tasks>?,
    activeSeason : String,
    isEdit : Boolean,
    onSeasonChange: (String) -> Unit,
    onNavigateToDetail: (String, Long) -> Unit,
    modifier: Modifier
) {
    var currentSeason by remember(activeSeason) { mutableStateOf(activeSeason) }
    val activeTasks: MutableList<Tasks> = mutableListOf()
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tasks:",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(10.dp, 20.dp, 0.dp, 5.dp)
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.BottomEnd
        ) {
            Card(
                shape = CardDefaults.elevatedShape,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                ),
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier
                    .fillMaxWidth(0.96f)
                    .fillMaxHeight()
            ) {
                if (!tasks.isNullOrEmpty()) {
                    tasks.forEach {
                        if (it.currentSeason == currentSeason) {
                            activeTasks.add(it)
                        }
                    }
                    if (isEdit){
                        SeasonsSegmentedButton(modifier = Modifier, currentSeason) {
                            currentSeason = it
                            onSeasonChange(it)
                        }
                    }
                    ReadOnlyTaskList(activeTasks)
                } else {
                    EmptyListMsg(stringResource(id = R.string.empty_task_list))
                }
            }
            AddFloatingBtn(
                onClick = {
                    onNavigateToDetail(
                        PlantSections.TASKS.route,
                        plantId
                    )
                },
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .height(50.dp)
                    .width(50.dp)
                    .offset(0.dp, 20.dp))
        }
    }
}

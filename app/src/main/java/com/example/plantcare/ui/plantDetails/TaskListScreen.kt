package com.example.plantcare.ui.plantDetails

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.plantcare.R
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.ui.components.DatePickerMenuRow
import com.example.plantcare.ui.components.PlantCareIconButton
import com.example.plantcare.ui.components.PlantCareSnackbar
import com.example.plantcare.ui.components.PlantCareUpPress
import com.example.plantcare.ui.components.TextInputRow
import com.example.plantcare.ui.components.TimePeriodPickerRow
import com.example.plantcare.ui.plantCreationEdit.PlantDetailsViewModel
import com.example.plantcare.ui.utils.AddFloatingBtn
import com.example.plantcare.ui.utils.getDateInMillis
import com.example.plantcare.ui.utils.SeasonsSegmentedButton
import com.example.plantcare.ui.utils.TitlesBackgroundShape
import com.example.plantcare.ui.utils.Urgency
import kotlinx.coroutines.launch

@Composable
fun TaskListScreen(
    plantId: Long,
    upPress: () -> Unit,
    viewModel: PlantDetailsViewModel
){
    val plantEditCreationUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val plant = plantEditCreationUiState.plant
    val tasks = plantEditCreationUiState.tasks
    Log.e("VIEWMODEL IN SECOND SCREEN", plantEditCreationUiState.toString())

    var isCreateNewOpen by remember {
        mutableStateOf(false)
    }

    var currentSeason by remember {
        mutableStateOf(plant.currentSeason)
    }

    val activeTasks = remember {
        mutableStateListOf<Tasks>()
    }
    activeTasks.clear()
    tasks?.forEach {task ->
        if (task.currentSeason == plant.currentSeason && !activeTasks.contains(task)) {
            activeTasks.add(task)
        }
    }

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.systemBarsPadding(),
                snackbar = { snackbarData -> PlantCareSnackbar(snackbarData) }
            )
        },
        floatingActionButton = {
            AddFloatingBtn(
                onClick = { isCreateNewOpen = !isCreateNewOpen },
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ){innerPadding ->
        Column(
            modifier = Modifier.padding(
                PaddingValues(
                    0.dp,
                    0.dp,
                    0.dp,
                    innerPadding.calculateBottomPadding()
                )
            )
        ) {
            PlantCareUpPress(upPress)
            SeasonsSegmentedButton(modifier = Modifier, plant.currentSeason) {
                plant.currentSeason = it
                currentSeason = it
                activeTasks.clear()
                tasks?.forEach {task ->
                    if (task.currentSeason == plant.currentSeason) {
                        activeTasks.add(task)
                    }
                }
            }
            if (isCreateNewOpen){
                TaskCard(
                    task = Tasks(0,
                        plantId, "", true, getDateInMillis(),
                        7, 0, Urgency.Normal.toString(),
                        0, getDateInMillis(), currentSeason
                    ),
                    snackbarHostState = snackbarHostState,
                    isEdit = false,
                    onSave = {
                        viewModel.createTask(it)
                        if(!plantEditCreationUiState.isEdit) {
                            activeTasks.add(it)
                        }
                        isCreateNewOpen = false
                    },
                    onDelete = {isCreateNewOpen = false}
                )
            }
            TasksColumn(
                taskList = activeTasks,
                snackbarHostState = snackbarHostState,
                onUpdate = viewModel::updateTask,
                onDelete = {task, int ->
                    viewModel.deleteTask(task, int)
                    activeTasks.remove(task)
                }
            )
        }
    }
}

@Composable
fun TasksColumn(
    taskList :  MutableList<Tasks>,
    snackbarHostState: SnackbarHostState,
    onUpdate : (Tasks, Int) -> Unit,
    onDelete: (Tasks, Int) -> Unit
){
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        if (taskList.isNotEmpty()) {
            items(taskList.size) { int ->
                TaskCard(
                    task = taskList[int],
                    snackbarHostState = snackbarHostState,
                    onSave = { onUpdate(taskList[int], int) },
                    onDelete = { onDelete(taskList[int], int) }
                )
            }
        }
    }
}
@Composable
fun TaskCard(
    task: Tasks,
    snackbarHostState: SnackbarHostState,
    isEdit : Boolean = true,
    onSave: (Tasks) -> Unit,
    onDelete: () -> Unit
){
    var isExpanded by remember { mutableStateOf(!isEdit) }
    var isError by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val rotationState by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        label = stringResource(R.string.task_icon_animation)
    )
    var name by remember {
        mutableStateOf(task.name)
    }
    var cycleLength by remember {
        mutableIntStateOf(task.cycleLength)
    }
    var lastCompleted by remember {
        mutableStateOf(task.lastCompleted)
    }

    Card(
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .fillMaxWidth(0.97f)
            .padding(10.dp)
    ) {
        if (isEdit) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = task.name,
                    modifier = Modifier.padding(start = 5.dp)
                )
                //todo MAKE A SINGLE DAY VER
                Text(text = "every ${task.cycleLength} days")
                PlantCareIconButton(
                    iconImage = Icons.Filled.ArrowDropDown,
                    contentDescription = R.string.open_close_task_icon,
                    onClick = { isExpanded = !isExpanded },
                    modifier = Modifier.rotate(rotationState)
                )
            }
        }
        if (isExpanded){
            TextInputRow(
                label = "Name",
                value = task.name,
                onTextChanged = {
                    name = it
                },
                modifier = Modifier.padding(5.dp, 15.dp, 5.dp, 0.dp)
            )
            TimePeriodPickerRow(
                label = "repeat\nevery",
                value = task.cycleLength,
                onTextChanged = {
                    cycleLength = it
                },
                isError = isError,
                modifier = Modifier.padding(5.dp, 0.dp, 5.dp, 0.dp)
            )
            DatePickerMenuRow(
                label = "last\ncompleted",
                value = task.lastCompleted,
                onSelect = {
                    lastCompleted = it
                },
                modifier = Modifier.padding(5.dp, 0.dp, 5.dp, 0.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                PlantCareIconButton(
                    iconImage = Icons.Filled.Delete,
                    contentDescription = R.string.delete_task,
                    onClick = {
                        onDelete()
                    }
                )
                PlantCareButton(
                    label = "save",
                    onButtonClick = {
                        Log.e("cycleLength", cycleLength.toString())
                        if (cycleLength == 0){
                            isError = true
                            scope.launch {
                                snackbarHostState.showSnackbar(context.resources.getString(R.string.number_zero))
                            }
                        } else {
                            task.name = name
                            task.cycleLength = cycleLength
                            task.lastCompleted = lastCompleted
                            onSave(task)
                            isExpanded = false
                        }
                    },
                    modifier = Modifier
                )
            }
        }

    }
}

@Composable
fun PlantCareButton(
    label : String,
    onButtonClick : () -> Unit,
    modifier: Modifier
){
    Button(
        onClick = { onButtonClick() },
        shape = TitlesBackgroundShape(70f, 40f),
        modifier = modifier
            .graphicsLayer {
                this.rotationZ = 179f
            }
    ) {
        Text(
            text = label,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .graphicsLayer {
                    this.rotationZ = 179f
                }
                .padding(5.dp, 5.dp, 0.dp, 0.dp)
        )
    }
}
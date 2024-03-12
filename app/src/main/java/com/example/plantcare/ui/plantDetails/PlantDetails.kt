package com.example.plantcare.ui.plantDetails

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.plantcare.R
import com.example.plantcare.data.model.Plants
import com.example.plantcare.ui.components.PlantCareImage
import com.example.plantcare.ui.navigation.PlantSections
import com.example.plantcare.ui.plantCreationEdit.PlantDetailsViewModel
import com.example.plantcare.ui.plantCreationEdit.TasksListCard
import com.example.plantcare.ui.utils.TitlesBackgroundShape
import com.example.plantcare.ui.utils.getElapsedTime
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@Composable
fun PlantDetails(
    plantId: Long,
    onNavigateToDetail: (String, Long) -> Unit,
    //upPress: () -> Unit,
    viewModel: PlantDetailsViewModel
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
            launch {
                viewModel.getPlantWithId(plantId)
            }
        }
    }

    val state by viewModel.uiState.collectAsStateWithLifecycle()


    val scrollState = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .verticalScroll(scrollState)
    ) {
        PlantDetailsScreenTop(
            state.plant
        )
        Column(
            modifier = Modifier
           .fillMaxHeight()
        ) {
            //todo move it in its own file
            TasksListCard(
                plantId = state.plant.plantsId,
                tasks = state.tasks,
                activeSeason = state.plant.currentSeason,
                isEdit = false,
                onSeasonChange = { state.plant.currentSeason = it},
                onNavigateToDetail = onNavigateToDetail,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
            )
            PlantDetailsNotes(
                state.plant.notes,
                showDialog = {showDialog = true},
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
                    .padding(bottom = 23.dp)
            )
            if(showDialog){
                NotesDialog(
                    notes = state.plant.notes,
                    closeDialog = {showDialog = false},
                    onSave = {
                        state.plant.notes = it
                        viewModel.savePlant(state.plant)
                    }
                )
            }
        }
        PlantCareButton(
            label = "edit",
            onButtonClick = { onNavigateToDetail(  PlantSections.EDIT_CREATE.route, plantId)},
            modifier = Modifier.align(Alignment.End)
        )
    }
}
//
@Composable
fun PlantDetailsScreenTop(
    plant : Plants
) {

    Row(
        modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        PlantCareImage(
            imageUrl = plant.photo,
            contentDescription = stringResource(id = R.string.missing_photo_description),
            modifier = Modifier
                .border(1.dp, color = Color.Black)
                .fillMaxWidth(0.5f)
                .fillMaxHeight()
        )
        Column(
            modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
               verticalArrangement = Arrangement.SpaceEvenly,
               horizontalAlignment = Alignment.End
        ) {
            Card(
                shape = TitlesBackgroundShape(150f, 100f),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                ),
                modifier = Modifier
                    .graphicsLayer {
                        this.rotationZ = 180f
                    }
            ){
                //TODO changing box side depending on screen size?
                Box(
                    modifier = Modifier
                        .height(getScreenHeight()),
                        //.height(40.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    FormatName(name = plant.name)
                }
            }
            //TODO make a more flexible age text
           // val age : String
            val age = if(getElapsedTime(plant.age) > 1){
                "${getElapsedTime(plant.age)} days old"
            }else{
                "${getElapsedTime(plant.age)} day old"
            }
            Text(
                text = age,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                ,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.titleLarge
            )
            if(plant.species!=""){
                Text(
                    text = plant.species,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                     .fillMaxWidth()
                    ,
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}


 //   @Composable
 //   fun getScreenWidth(): Dp {
 //       val density = LocalDensity.current;
 //       val configuration = LocalConfiguration.current;
 //       val screenWidthDp = with(density) {configuration.screenWidthDp.dp}
 //       val divider : Int = when {
 //           screenWidthDp <= 360.dp -> 4
 //           screenWidthDp <= 400.dp -> 5
 //           screenWidthDp <= 450.dp -> 6
 //           else -> 7
 //       }
 //       Log.e("HERE", screenWidthDp.toString())
 //       return screenWidthDp / divider
 //   }
    @Composable
    fun getScreenHeight(): Dp {
        val density = LocalDensity.current
        val configuration = LocalConfiguration.current
        val screenHeightDp = with(density) {configuration.screenHeightDp.dp}
        val divider : Int = when {
            screenHeightDp <= 720.dp -> 9
            screenHeightDp <= 800.dp -> 10
            screenHeightDp <= 900.dp -> 9
            else -> 12
        }
        Log.e("HERE", (screenHeightDp / divider).toString())
        return screenHeightDp / divider
    }


    @Composable
    fun FormatName(name : String) {
        var formattedName = name
        var maxLength = name.length
        var isOverflowOn = TextOverflow.Clip

        if (name.contains(" ")) {
            formattedName = name.replace(" ", "\n")
            val lines = formattedName.split("\\s".toRegex()).toTypedArray()

            lines.forEach {
                if (it.length > 5) {
                    maxLength = it.length + 5
                    Log.e("HERE", maxLength.toString())
                }
            }
            if (lines.size > 3) isOverflowOn = TextOverflow.Ellipsis
        }

        val size: TextUnit = if (maxLength <= 6) {
            10.em
        } else if (maxLength <= 8) {
            7.8.em
        } else if (maxLength <= 9) {
            7.2.em
        } else if (maxLength <= 11) {
            5.9.em
        } else if (maxLength <= 13) {
            4.7.em
        } else if (maxLength <= 15) {
            4.em
        } else {
            3.5.em
        }

        Text(
            text = formattedName,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            fontSize = size,
            lineHeight = 20.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .padding(5.dp, 0.dp, 0.dp, 0.dp)
                .graphicsLayer {
                    this.rotationZ = 180f
                },
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.titleLarge,
            overflow = isOverflowOn
        )

    }



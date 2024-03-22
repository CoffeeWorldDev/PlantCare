package com.example.plantcare.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.plantcare.R
import com.example.plantcare.ui.components.PlantCareIconButton
import com.example.plantcare.ui.navigation.HomeSections
import com.example.plantcare.ui.navigation.PlantCareBottomBar
import com.example.plantcare.ui.utils.getDateInMillis
import com.example.plantcare.ui.utils.getDateInString
import java.util.Date

@Composable
fun Home(
    onPlantClick: (Long) -> Unit,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeUiState by viewModel.uiState.collectAsStateWithLifecycle()

    //todo delete
    val context = LocalContext.current



    var showOptionsDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        bottomBar = {
            PlantCareBottomBar(
                tabs = HomeSections.entries.toTypedArray(),
                currentRoute = HomeSections.FEED.route,
                navigateToRoute = onNavigateToRoute
            )
        },
        modifier = modifier
    ) { innerPadding ->
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
            HomeHeader(
                onValueChange = { viewModel.changeQuery(it)
                                //todo delete
                    viewModel.addDatePeriodicWorker(context)
                    },
                showDialog = {showOptionsDialog = true},
                modifier = Modifier
            )
            HomeMainBlock(
                homeUiState.plantsMap,
                onPlantClick = onPlantClick
            ) { viewModel.updateTask(it) }

            if(showOptionsDialog){
                HomeOptionsMenu(
                    closeDialog = {showOptionsDialog = false}
                )
            }
        }
    }
}
@Composable
fun HomeHeader(
    onValueChange: (Date) -> Unit,
    showDialog: () -> Unit,
    modifier: Modifier
){
    val primaryColorContainer = MaterialTheme.colorScheme.primaryContainer
    val onPrimaryColorContainer = MaterialTheme.colorScheme.onPrimaryContainer
    val shape = CircleShape

    Row(
        Modifier.padding(top = 10.dp, bottom = 40.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        LazyRow(
            modifier = Modifier.weight(6f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            items(6){
                Card(
                    shape = shape,
                    colors = CardDefaults.cardColors(containerColor = primaryColorContainer),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 5.dp
                    ),
                    modifier = Modifier.clickable {
                        onValueChange(getDateInMillis(it))
                    }
                ) {
                    Text(
                        text = getDateInString(it),
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
        PlantCareIconButton(
            iconImage = Icons.Filled.Menu,
            contentDescription = R.string.hamburger_button_icon,
            onClick = { showDialog() },
            modifier = Modifier
                .weight(0.5f)
                .padding(0.dp, 5.dp, 12.dp, 5.dp)
                .height(45.dp)
                .width(45.dp))
    }
}

@Composable
fun HomeOptionsMenu(
    closeDialog : () -> Unit
){
    //val queryOptions = listOf("name", "type", "age", "position")
    Dialog(
        onDismissRequest = {closeDialog()}
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.8f)
        ) {
            Text(
                text = "Sort by:",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(5){
                    TextButton(
                        onClick = {  }) {
                        Text(text = "option ${it + 1}")
                    }
                }
            }
        }
    }
}
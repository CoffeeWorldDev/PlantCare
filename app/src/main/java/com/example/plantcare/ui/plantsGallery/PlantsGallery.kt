package com.example.plantcare.ui.plantsGallery

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.plantcare.data.model.Plants
import com.example.plantcare.ui.navigation.HomeSections
import com.example.plantcare.ui.navigation.PlantCareBottomBar
import com.example.plantcare.ui.utils.AddFloatingBtn
import com.example.plantcare.ui.utils.TitlesBackgroundShape
import kotlinx.coroutines.launch

@Composable
fun PlantsGallery(
    onPlantClick: (Long) -> Unit,
    onNavigateToRoute: (String) -> Unit,
    onCreateNew: (Long) -> Unit,
    viewModel: PlantsGalleryViewModel = hiltViewModel()
) {

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
            launch {
                viewModel.sortGallery("name")
            }
        }
    }

    val galleryUiState by viewModel.uiState.collectAsStateWithLifecycle()

    var showOptionsDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        bottomBar = {
            PlantCareBottomBar(
                tabs = HomeSections.entries.toTypedArray(),
                currentRoute = HomeSections.GALLERY.route,
                navigateToRoute = onNavigateToRoute
            )
        },
        floatingActionButton = {
            AddFloatingBtn(
                onClick = { onCreateNew(-1) },
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
            )
        },
        floatingActionButtonPosition = FabPosition.End
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
            PlantsGalleryHeader(
                galleryUiState.plants,
                showDialog = {showOptionsDialog = true}
            )
            if (galleryUiState.plants.isNotEmpty()) {
                when (galleryUiState.currentLayout) {
                    1 -> PlantsGalleryBodyVerL1(
                        plants = galleryUiState.plants,
                        onPlantClick = onPlantClick
                    )
                    2 -> PlantsGalleryBodyVerL2(
                        plants = galleryUiState.plants,
                        columns = 1,
                        onPlantClick = onPlantClick,
                        )
                    3 -> PlantsGalleryBodyVerL2(
                        plants = galleryUiState.plants,
                        columns = 2,
                        onPlantClick = onPlantClick,
                    )
                    4 -> PlantsGalleryBodyVerL3(
                        plants = galleryUiState.plants,
                        columns = 2,
                        onPlantClick = onPlantClick
                    )
                    5 -> PlantsGalleryBodyVerL3(
                        plants = galleryUiState.plants,
                        columns = 3,
                        onPlantClick = onPlantClick
                    )
                }
            }
            if(showOptionsDialog){
                GalleryOptionsMenu(
                    closeDialog = {showOptionsDialog = false},
                    changeQuery = viewModel::sortGallery,
                    changeLayout = viewModel::changeLayout
                )
            }
        }
    }
}

@Composable
fun GalleryOptionsMenu(
    closeDialog : () -> Unit,
    changeQuery : (String) -> Unit,
    changeLayout : (Int) -> Unit
){
    val queryOptions = listOf("name", "type", "age", "position")
    Dialog(
        onDismissRequest = {closeDialog()}
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.8f)
        ) {
            Text(
                text = "Order by:",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(queryOptions.size){
                    TextButton(
                        onClick = {
                            changeQuery(queryOptions[it])
                            closeDialog()
                        }) {
                            Text(text = queryOptions[it])
                    }
                }
            }
            Text(
                text = "Layout:",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(5){
                    TextButton(
                        onClick = { changeLayout(it + 1) }) {
                        Text(text = "layout ${it + 1}")
                    }
                }
            }
        }
    }
}

@Composable
fun PlantsGalleryHeader(
    plants: List<Plants>,
    showDialog: () -> Unit
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val onPrimaryColor = MaterialTheme.colorScheme.onPrimary
    val titleLarge = MaterialTheme.typography.titleLarge
    Row (
        Modifier.padding(top = 10.dp, bottom = 40.dp),
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
                    text = "You have ${plants.size}\nplants",
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
            verticalArrangement = Arrangement.SpaceBetween
        ) {
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
                modifier = Modifier
                    .weight(0.5f)
                    .padding(0.dp, 5.dp, 12.dp, 5.dp)
                    .height(45.dp)
                    .width(45.dp),
                onClick = { showDialog() }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Home Icon"
                )
            }
        }
    }
}
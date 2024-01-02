package com.example.plantcare.ui.plantCreationEdit

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.plantcare.R
import com.example.plantcare.data.model.Plants
import com.example.plantcare.ui.components.PlantCareIconButton
import com.example.plantcare.ui.components.PlantCareImage
import com.example.plantcare.ui.components.PlantCareSnackbar
import com.example.plantcare.ui.components.PlantCareUpPress
import com.example.plantcare.ui.utils.GetDateInMillis


@Composable
fun PlantCreationEdit(
    plantId: Long,
    upPress: () -> Unit,
    viewModel: PlantCreationEditViewModel = hiltViewModel()
) {
    //TODO delete log
    Log.e("PLANT id", plantId.toString())

    viewModel.getPlantWithId(plantId)
    val plantEditCreationUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }



    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.systemBarsPadding(),
                snackbar = { snackbarData -> PlantCareSnackbar(snackbarData) }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.TopStart
        ) {
            PlantCareUpPress(upPress)
            PlantCreationEditForm(
                plantEditCreationUiState.plant,
                snackbarHostState,
                onSave = {viewModel.createPlant(it)},
                onDelete = {viewModel.deletePlant(it)},
                goBack = upPress
            )
        }
    }
}

@Composable
fun PlantCreationEditForm(
    plant: Plants,
    snackbarHostState: SnackbarHostState,
    onSave: (Plants) -> Unit,
    onDelete: (Plants) -> Unit,
    goBack: () -> Unit){

    //TODO delete log
    Log.e("PLANT ui state", plant.toString())
    //val plantToList = plantMap?.toList()
    //val plant = plantToList?.get(0)?.first
    //val tasks = plantToList?.get(0)?.second
    Log.e("PLANT id testing", plant.type.toString())

    // Data
    var name = plant.name
    //var name by remember { mutableStateOf(plant.name ?: "")
    Log.d("input name", name)
    var type by remember { mutableStateOf(plant.type)}
    var species by remember { mutableStateOf(plant.species ?: "")}
    //Log.d("input species", type)
    var position by remember { mutableStateOf(plant.position ?: "")}
    var age by remember { mutableStateOf(plant.age ?: "")}
    var photo by remember { mutableStateOf(plant.photo ?: "")}
    var notes by remember { mutableStateOf(plant.notes ?: "")}
    var currentSeason by remember { mutableStateOf(plant.currentSeason ?: "")}

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ImageBlock(photo = photo,
            onSelect = {photo = it.toString() })
        PlantCareTextInput(value = plant.type,
            onTextChanged = {name = it
                Log.d("inFun name", it)},
            label = "Name",
            maxLength = 18,
            isObligatory = true)
        PlantCareTextInput(value = species,
            onTextChanged = {species = it
                Log.d("inFun species", it)},
            label = "Species")
        PlantCareTextInput(value = type,
            onTextChanged = {type = it},
            label = "Type")
        PlantCareTextInput(value = position,
            onTextChanged = {position = it},
            label = "Position")
        SavePlant(
            onButtonClick = {onSave(it)},
            snackbarHostState,
            name = name,
            type = type.orEmpty(),
            species = species.orEmpty(),
            photo = photo.orEmpty(),
            position = position.orEmpty(),
            goBack = goBack)
        if(plant != null){
            Button(onClick = {onDelete(plant)}) {
                Text(text = "delete")
            }
        }
    }
}

@Composable
fun ImageBlock(photo : String?,
               onSelect: (Uri?) -> Unit){

    val photoUri = remember { mutableStateOf<String?>(photo) }

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            photoUri.value = uri.toString()
            onSelect(uri)
            uri?.let { saveImageToInternalStorage(context, it) }
        }
    )
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(40.dp, 10.dp)
        //.background(Color.Red)
    ) {
        PlantCareImage(imageUrl = photoUri.value ?: R.drawable.baseline_image_24,
            contentDescription = stringResource(id = R.string.plants_photo_description),
            modifier = Modifier
                .height(250.dp)
                .width(250.dp)
                .offset(25.dp)
                .padding(10.dp))
        Column(verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxHeight()
            //.background(Color.Cyan)
        ) {
            //TODO open camera function
            PlantCareIconButton(
                Icons.Filled.AccountBox,
                R.string.photo_from_camera_icon,
                { Log.d("Click", "open camera") },
                Modifier.padding(10.dp)
            )
            PlantCareIconButton(
                Icons.Filled.Settings,
                R.string.photo_from_gallery_icon,
                {
                    launcher.launch("image/*")
                    // launcher.launch(
                    //     PickVisualMediaRequest(
                    //     mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly))
                    Log.d("Click", "open gallery") },
                Modifier.padding(10.dp)
            )
        }
    }
}


fun saveImageToInternalStorage(context: Context, uri: Uri) {
    val inputStream = context.contentResolver.openInputStream(uri)
    val outputStream = context.openFileOutput("image.jpg", Context.MODE_PRIVATE)
    inputStream?.use { input ->
        outputStream.use { output ->
            input.copyTo(output)
        }
    }
}

@Composable
fun PlantCareTextInput(value: String?,
                       onTextChanged: (String) -> Unit,
                       label: String,
                       maxLength: Int = 40,
                       isObligatory: Boolean = false,
                       isError: Boolean = false){

    var inputValue by remember { mutableStateOf(value ?: "")}

    var isError by remember { mutableStateOf(isError)}

    val focusManager = LocalFocusManager.current

    OutlinedTextField(value = inputValue,
        onValueChange = { if (it.length <= maxLength) inputValue = it },
        label = { Text(label) },
        singleLine = true,
        isError = isError,
        modifier = Modifier.fillMaxWidth(),
        supportingText = {
            if(maxLength < 40) {
                Text(
                    text = "${inputValue.length} / $maxLength",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                )
            }
        },
        keyboardOptions = KeyboardOptions(autoCorrect = false),
        //TODO check text
        keyboardActions = KeyboardActions {
            isError = if (isObligatory && inputValue == ""){
                true
            } else {
                onTextChanged(inputValue.trim())
                false
            }
            focusManager.clearFocus()
        }
    )
}

@Composable
fun SavePlant(onButtonClick: (Plants) -> Unit,
              snackbarHostState : SnackbarHostState,
              goBack: () -> Unit,
              name: String,
              type : String?,
              species: String?,
              photo: String?,
              position: String?){
    var plant = Plants(0, name, type, species, position, GetDateInMillis(), photo,
        mapOf("" to ""), "summer"
    )
    var isValid = plant.name.isNotEmpty()
    Button(onClick = { onButtonClick(plant)
        goBack() }) {
        Text(text = "click")
    }
}

fun validatePlant(
    plant : Plants,
    snackbarHostState : SnackbarHostState) : Boolean{
    if (plant.name.isNotEmpty()) {
        return true
    } else {
       // snackbarHostState.showSnackbar("hey")
        return false
    }
}

fun deletePlant(onButtonClick: (Plants) -> Unit,
                goBack: () -> Unit){

}






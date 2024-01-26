package com.example.plantcare.ui.plantCreationEdit

import android.Manifest
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.plantcare.R
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.ui.components.DatePickerMenuRow
import com.example.plantcare.ui.components.DropdownMenuRow
import com.example.plantcare.ui.components.PlantCareIconButton
import com.example.plantcare.ui.components.PlantCareImage
import com.example.plantcare.ui.components.PlantCareSnackbar
import com.example.plantcare.ui.components.PlantCareUpPress
import com.example.plantcare.ui.components.TextInputRow
import com.example.plantcare.ui.utils.getTypesOfPlantsList
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.launch
import java.io.File


@Composable
fun PlantCreationEdit(
    plantId: Long,
    upPress: () -> Unit,
    viewModel: PlantCreationEditViewModel = hiltViewModel()
) {
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
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            PlantCareUpPress(upPress)
            PlantCreationEditForm(
                plantEditCreationUiState.plant,
                plantEditCreationUiState.tasks,
                plantEditCreationUiState.isEdit,
                snackbarHostState,
                onSave = viewModel::savePlant,
                onDelete = viewModel::deletePlant,
                goBack = upPress
            )
        }
    }
}

@Composable
fun PlantCreationEditForm(
    plant: Plants,
    tasks : List<Tasks>?,
    isEdit : Boolean,
    snackbarHostState: SnackbarHostState,
    onSave: (Plants) -> Unit,
    onDelete: (Plants) -> Unit,
    goBack: () -> Unit){

    //TODO delete log
    Log.e("PLANT ui state", plant.toString())

    val currentPhoto = plant.photo!!.toUri()
    Log.e("current photo saved", currentPhoto.toString())
    val plantToBeChanged = plant
    Log.e("new photo saved", plantToBeChanged.photo.toString())
    Log.e("new other saved", plantToBeChanged.species.toString())

    var isFormInvalid by remember {
        mutableStateOf(false)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(15.dp, 0.dp)) {
        ImageBlock(
            photo = plant.photo?.toUri(),
            snackbarHostState = snackbarHostState
        ) {
            if (it != null) {
                plantToBeChanged.photo = it.toString()
            }
        }
        TextInputRow(
            label = "Name",
            value = plant.name,
            onTextChanged = { plantToBeChanged.name = it },
            maxLength = 18,
            isError = isFormInvalid
        )
        TextInputRow(
            label = "Species",
            value = plant.species!!,
            onTextChanged = {plantToBeChanged.species = it})
        DropdownMenuRow(
            label = "Type",
            value = plant.type!!,
            onSelect = {plantToBeChanged.type = it},
            options = getTypesOfPlantsList().map { it.name })
        TextInputRow(
            label = "Position",
            value = plant.position!!,
            onTextChanged = {plantToBeChanged.position = it},)
        DatePickerMenuRow(
            label = "birthday",
            value = plant.age!!,
            onSelect = {plantToBeChanged.age = it})
        TasksListCard(
            tasks = tasks,
            activeSeason = plant.currentSeason,
            isEdit = isEdit
        )
        SavePlant(
            onButtonClick = {onSave(it)},
            snackbarHostState,
            plant = plantToBeChanged,
            goBack = goBack,
            currentlySavedPhoto = currentPhoto,
            isError = {isFormInvalid = it})
        if(isEdit){
            DeletePlant(
                plant = plant,
                onButtonClick = onDelete,
                goBack = goBack)
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ImageBlock(
    photo: Uri?,
    snackbarHostState: SnackbarHostState,
    onSelect: (Uri?) -> Unit
) {

        val coroutineScope = rememberCoroutineScope()
        val context = LocalContext.current

        var photoUri by remember(photo) {
            mutableStateOf<Uri?>(
                if (photo.toString() == ""){null} else {photo}
            )
        }
        var tempPhotoUri by remember {
            mutableStateOf(value = Uri.EMPTY)
        }

        val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
        val galleryPermissionState =
            when(Build.VERSION.SDK_INT){
                Build.VERSION_CODES.UPSIDE_DOWN_CAKE ->  rememberMultiplePermissionsState(
                    listOf(
                        READ_MEDIA_IMAGES, READ_MEDIA_VISUAL_USER_SELECTED
                    ))
                Build.VERSION_CODES.TIRAMISU -> rememberMultiplePermissionsState(
                    listOf(
                        READ_MEDIA_IMAGES
                    ))
                else -> rememberMultiplePermissionsState(
                    listOf(
                        READ_EXTERNAL_STORAGE
                    ))
            }

        val launcherCamera = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture(),
            onResult = { success ->
                Log.d("camera", tempPhotoUri.toString())
                    if (success) photoUri = tempPhotoUri
                    onSelect(tempPhotoUri)
                }
            )

        val launcherGallery = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = { uri: Uri? ->
                photoUri = uri
                onSelect(uri)
            }
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp, 0.dp, 0.dp, 20.dp)
        ) {
            PlantCareImage(
                imageUrl = photoUri ?: R.drawable.placeholderimage,
                contentDescription = stringResource(id = R.string.plants_photo_description),
                modifier = Modifier
                    .height(250.dp)
                    .width(250.dp)
                    .offset(25.dp)
                    .padding(10.dp)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()
                //.background(Color.Cyan)
            ) {
                PlantCareIconButton(
                    Icons.Filled.AccountBox,
                    R.string.photo_from_camera_icon,
                    {
                        if (cameraPermissionState.status.isGranted){
                            Log.d("Click", "open camera permission granted")
                            coroutineScope.launch {
                                tempPhotoUri = context.createTempPictureUri()
                                launcherCamera.launch(tempPhotoUri)
                            }
                        } else {
                            if (cameraPermissionState.status.shouldShowRationale) {
                                cameraPermissionState.launchPermissionRequest()
                            } else {
                                coroutineScope.launch {
                                    val result = snackbarHostState
                                        .showSnackbar(
                                            message = context.resources.getString(R.string.permission_needed_camera),
                                            actionLabel = "Settings",
                                            duration = SnackbarDuration.Short
                                        )
                                    if (result == SnackbarResult.ActionPerformed) {
                                        Log.d("Clicked", "settings")
                                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                        intent.setData(Uri.fromParts("package", context.packageName, null))
                                            startActivity(context, intent, null)
                                       }
                                }
                            }
                            cameraPermissionState.launchPermissionRequest()
                        }
                    },
                    Modifier.padding(10.dp)
                )
                PlantCareIconButton(
                    Icons.Filled.Settings,
                    R.string.photo_from_gallery_icon,
                    {
                        if (galleryPermissionState.allPermissionsGranted){
                            Log.d("Click", "open gallery permission granted")
                            launcherGallery.launch("image/*")
                        } else {
                            if (galleryPermissionState.shouldShowRationale) {
                                galleryPermissionState.launchMultiplePermissionRequest()
                            } else {
                                coroutineScope.launch {
                                    val result = snackbarHostState
                                        .showSnackbar(
                                            message = context.resources.getString(R.string.permission_needed_gallery),
                                            actionLabel = "Settings",
                                            duration = SnackbarDuration.Short
                                        )
                                    if (result == SnackbarResult.ActionPerformed) {
                                        Log.d("Clicked", "settings")
                                        val intent =
                                            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                        intent.setData(
                                            Uri.fromParts(
                                                "package",
                                                context.packageName,
                                                null
                                            )
                                        )
                                        startActivity(context, intent, null)
                                    }
                                }
                            }
                            galleryPermissionState.launchMultiplePermissionRequest()
                        }
                    },
                    Modifier.padding(10.dp)
                )
            }
        }
    }
fun Context.createTempPictureUri(
    provider: String = "com.example.plantcare.fileProvider",
    fileName: String = "picture_${System.currentTimeMillis()}",
    fileExtension: String = ".jpg"
): Uri {
    val tempFile = File.createTempFile(
        fileName, fileExtension, cacheDir
    ).apply {
        createNewFile()
    }
    return FileProvider.getUriForFile(applicationContext, provider, tempFile)
}

fun saveImageToInternalStorage(context: Context, uri: Uri) : String {

    val fileName = "Image-${System.currentTimeMillis()}.jpg"
    val inputStream = context.contentResolver.openInputStream(uri)
    val outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
    inputStream?.use { input ->
        outputStream.use { output ->
            input.copyTo(output)
        }
    }
    val file = File(context.filesDir, fileName)
    Log.e("file", file.exists().toString())
    return file.path.toString()
}
fun deletePreviousPhoto(context: Context, uri: Uri){
    val fdelete = File(uri.path.toString())
    if (fdelete.exists()) {
        if (fdelete.delete()) {
            System.out.println("file Deleted :" + uri.path)
        } else {
            System.out.println("file not Deleted :" + uri.path)
        }
    }
}



@Composable
fun DeletePlant(
    plant: Plants,
    onButtonClick: (Plants) -> Unit,
    goBack: () -> Unit){
    Button(onClick = {
        onButtonClick(plant)
        goBack()
    }) {
        Text(text = "delete")
    }
}

@Composable
fun SavePlant(onButtonClick: (Plants) -> Unit,
              snackbarHostState : SnackbarHostState,
              goBack: () -> Unit,
              plant: Plants,
              currentlySavedPhoto : Uri,
              isError : (Boolean) -> Unit
){
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Log.e("worked",plant.photo!!)
    Log.e("worked",currentlySavedPhoto.toString())
    Button(onClick = {
        if (plant.name == ""){
            scope.launch {
                snackbarHostState.showSnackbar(context.resources.getString(R.string.form_not_valid))
            }
            isError(true)
        } else {
            if (plant.photo != "" && plant.photo != currentlySavedPhoto.toString()){
                Log.e("worked","if started to save image")
                Log.e("worked",plant.photo!!)
                Log.e("worked",currentlySavedPhoto.toString())
                plant.photo = saveImageToInternalStorage(context, plant.photo!!.toUri())
            }
            onButtonClick(plant)
            if(plant.photo != "" && plant.photo != currentlySavedPhoto.toString()){
                deletePreviousPhoto(context, currentlySavedPhoto)
                Log.e("worked","if started to delete image")
            }
            goBack()
        }
    }) {
        Text(text = "click")
    }
}
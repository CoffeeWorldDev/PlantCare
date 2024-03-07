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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
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
import com.example.plantcare.ui.plantDetails.PlantCareButton
import com.example.plantcare.ui.utils.PlantCareAlertDialog
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
    onNavigateToDetail: (String, Long) -> Unit,
    navigateBackToGallery : () -> Unit,
    upPress: () -> Unit,
    viewModel: PlantDetailsViewModel
) {

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        Log.e("empty screen log", "???")
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
            launch {
                viewModel.getPlantWithId(plantId)
            }
        }
    }

    val state by viewModel.uiState.collectAsState()
    //Log.e("PLANT ui state photo", plantEditCreationUiState.photo.toString())
    //todo delete
    if (state.tasks?.isEmpty() == false){
        val test = state.tasks!![0]
        Log.e("TASK", test.toString())
    }
    // Log.e("TASK", viewModel.getAllTasks().toString())
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
                state.plant,
                state.tasks,
                state.isEdit,
                snackbarHostState,
                onSave = viewModel::savePlant,
                onDelete = viewModel::deletePlant,
                onNavigateToDetail = onNavigateToDetail,
                navigateBackToGallery = navigateBackToGallery,
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
    onNavigateToDetail: (String, Long) -> Unit,
    navigateBackToGallery : () -> Unit,
    goBack: () -> Unit){

    //TODO delete log
    //Log.e("PLANT ui state", plant.toString())
    var showDialog by remember { mutableStateOf(false) }

    val currentPhoto = plant.photo!!.toUri()
    //Log.e("current photo saved", currentPhoto.toString())
    //Log.e("new photo saved", plant.photo.toString())
    //Log.e("new other saved", plant.species.toString())

    var bottomArrangment = if (isEdit){Arrangement.SpaceBetween} else {Arrangement.End}

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
                plant.photo = it.toString()
            }
        }
        TextInputRow(
            label = "Name",
            value = plant.name,
            onTextChanged = { plant.name = it },
            maxLength = 18,
            isError = isFormInvalid,
            modifier = Modifier
        )
        TextInputRow(
            label = "Species",
            value = plant.species!!,
            onTextChanged = { plant.species = it},
            modifier = Modifier
        )
        DropdownMenuRow(
            label = "Type",
            value = plant.type!!,
            onSelect = { plant.type = it},
            options = getTypesOfPlantsList().map { it.name },
            modifier = Modifier
        )
        TextInputRow(
            label = "Position",
            value = plant.position!!,
            onTextChanged = { plant.position = it},
            modifier = Modifier
        )
        DatePickerMenuRow(
            label = "birthday",
            value = plant.age!!,
            onSelect = { plant.age = it},
            modifier = Modifier)
        TasksListCard(
            plantId = plant.plantsId,
            tasks = tasks,
            activeSeason = plant.currentSeason,
            isEdit = isEdit,
            onSeasonChange = { plant.currentSeason = it},
            onNavigateToDetail = onNavigateToDetail,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
        )

        PlantCareAlertDialog(
            title = stringResource(id = R.string.delete_plant_title),
            message = stringResource(id = R.string.delete_plant_message),
            isVisible = showDialog,
            onConfirm = {
                        onDelete(plant)
                        deletePhotoFromDb(plant.photo?.toUri())
                        navigateBackToGallery()
                        showDialog = false
            },
            onDismiss = {showDialog = false})
    }
    Row(
        horizontalArrangement = bottomArrangment,
        modifier = Modifier
            .padding(top = 30.dp, bottom = 15.dp)
            .fillMaxWidth()
    ) {
        if(isEdit){
            //TODO clean up to fit the new ver with dialog
            DeletePlant(
                plant = plant,
                onButtonClick = {
                    showDialog = true
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        SavePlant(
            onButtonClick = { onSave(it) },
            snackbarHostState,
            plant = plant,
            goBack = goBack,
            currentlySavedPhoto = currentPhoto,
            isError = { isFormInvalid = it },
            modifier = Modifier.align(Alignment.CenterVertically)
        )
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
fun deletePhotoFromDb(uri: Uri?){
    val fdelete = File(uri?.path.toString())
    if (uri != null) {
        if (fdelete.exists()) {
            if (fdelete.delete()) {
                println("file Deleted :" + uri.path)
            } else {
                println("file not Deleted :" + uri.path)
            }
        }
    }
}



@Composable
fun DeletePlant(
    plant: Plants,
    onButtonClick: (Plants) -> Unit,
    modifier: Modifier
){
    PlantCareIconButton(
        iconImage = Icons.Filled.Delete,
        contentDescription = R.string.delete_plant_icon,
        onClick = { onButtonClick(plant) },
        modifier = modifier)
}

@Composable
fun SavePlant(onButtonClick: (Plants) -> Unit,
              snackbarHostState : SnackbarHostState,
              goBack: () -> Unit,
              plant: Plants,
              currentlySavedPhoto : Uri,
              isError : (Boolean) -> Unit,
              modifier: Modifier
){
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    PlantCareButton(
        label = "save",
        onButtonClick = {
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
                    deletePhotoFromDb(currentlySavedPhoto)
                    Log.e("worked","if started to delete image")
                }
                goBack()
            }
        },
        modifier = modifier)
 //   Button(onClick = {
 //       if (plant.name == ""){
 //           scope.launch {
 //               snackbarHostState.showSnackbar(context.resources.getString(R.string.form_not_valid))
 //           }
 //           isError(true)
 //       } else {
 //           if (plant.photo != "" && plant.photo != currentlySavedPhoto.toString()){
 //               Log.e("worked","if started to save image")
 //               Log.e("worked",plant.photo!!)
 //               Log.e("worked",currentlySavedPhoto.toString())
 //               plant.photo = saveImageToInternalStorage(context, plant.photo!!.toUri())
 //           }
 //           onButtonClick(plant)
 //           if(plant.photo != "" && plant.photo != currentlySavedPhoto.toString()){
 //               deletePhotoFromDb(currentlySavedPhoto)
 //               Log.e("worked","if started to delete image")
 //           }
 //           goBack()
 //       }
 //   },
 //       modifier = modifier) {
 //       Text(text = "save")
 //   }
}
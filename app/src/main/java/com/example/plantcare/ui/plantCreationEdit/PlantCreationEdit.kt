package com.example.plantcare.ui.plantCreationEdit

import android.Manifest
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.DatePicker
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.plantcare.R
import com.example.plantcare.data.model.Plants
import com.example.plantcare.ui.components.PlantCareIconButton
import com.example.plantcare.ui.components.PlantCareImage
import com.example.plantcare.ui.components.PlantCareSnackbar
import com.example.plantcare.ui.components.PlantCareUpPress
import com.example.plantcare.ui.utils.GetDateInMillis
import com.example.plantcare.ui.utils.getTypesOfPlantsList
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch
import java.io.File

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            //contentAlignment = Alignment.TopStart
        ) {
            PlantCareUpPress(upPress)
            PlantCreationEditForm(
                plantEditCreationUiState.plant,
                plantEditCreationUiState.isEdit,
                snackbarHostState,
                onSave = viewModel::savePlant,
                //onDelete = {viewModel.deletePlant(it)},
                onDelete = viewModel::deletePlant,
                goBack = upPress
            )
        }
    }
}

@Composable
fun PlantCreationEditForm(
    plant: Plants,
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



    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(15.dp, 0.dp)) {
        ImageBlock(
            photo = plant.photo?.toUri()
        ) {
            if (it != null) {
                plantToBeChanged.photo = it.toString()
            }
        }
        PlantCareTextInput(
            value = plant.name,
            onTextChanged = { plantToBeChanged.name = it },
            label = "Name",
            maxLength = 18,
            isObligatory = true
        )
        PlantCareTextInput(value = plant.species!!,
            onTextChanged = {plantToBeChanged.species = it},
            label = "Species")
        TypesDropdownMenu(value = plant.type,
            onSelect = {plantToBeChanged.type = it})
        PlantCareTextInput(value = plant.position!!,
            onTextChanged = {plantToBeChanged.position = it},
            label = "Position")
        PlantCareDatePicker(label = "birthday:",
            startingDate = plant.age!!,
            onDatePicked = {plantToBeChanged.age = it})
        SavePlant(
            onButtonClick = {onSave(it)},
            snackbarHostState,
            plant = plantToBeChanged,
            goBack = goBack,
            currentlySavedPhoto = currentPhoto)
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
                .height(350.dp)
                .padding(40.dp, 10.dp)
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
                //TODO open camera function

                PlantCareIconButton(
                    Icons.Filled.AccountBox,
                    R.string.photo_from_camera_icon,
                    {
                        if (cameraPermissionState.status.isGranted){
                            Log.d("Click", "open camera permission granted")
                            coroutineScope.launch {
                               // val values = ContentValues()
                               // values.put(
                               //     MediaStore.Images.Media.TITLE,
                               //     "New PictureGroup"
                               // )
                               // values.put(
                               //     MediaStore.Images.Media.DESCRIPTION,
                               //     "From the Camera"
                               // )
                               // photoUri = context.contentResolver.insert(
                               //     MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                               //     values
                               // )
                                tempPhotoUri = context.createTempPictureUri()
                                launcherCamera.launch(tempPhotoUri)
                            }
                        } else {
                            Log.d("Click", "open camera permission to ask")
                            cameraPermissionState.launchPermissionRequest()
                        }
                        //addPhotoFromCamera()
                    },
                    Modifier.padding(10.dp)
                )
                PlantCareIconButton(
                    Icons.Filled.Settings,
                    R.string.photo_from_gallery_icon,
                    {
                        //launcher.launch("image/*")
                        // launcher.launch(
                        //     PickVisualMediaRequest(
                        //     mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly))

                        // Permission request logic
                        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                        //    requestPermissions.launch(arrayOf(READ_MEDIA_IMAGES, READ_MEDIA_VIDEO, READ_MEDIA_VISUAL_USER_SELECTED))
                        //} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        //    requestPermissions.launch(arrayOf(READ_MEDIA_IMAGES, READ_MEDIA_VIDEO))
                        //} else {
                        //    requestPermissions.launch(arrayOf(READ_EXTERNAL_STORAGE))
                        //}
                        if (cameraPermissionState.status.isGranted){
                            launcherGallery.launch("image/*")
                           // launcher.launch(
                           //     PickVisualMediaRequest(
                           //         mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                           //     )
                           // )
                        } else {
                            cameraPermissionState.launchPermissionRequest()
                        }
                        if (galleryPermissionState.allPermissionsGranted){
                            Log.d("Click", "open gallery permission granted")
                        } else {
                            Log.d("Click", "open gallery permission to ask")
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
fun PlantCareTextInput(value: String,
                       onTextChanged: (String) -> Unit,
                       label: String,
                       maxLength: Int = 40,
                       isObligatory: Boolean = false,
                       isError: Boolean = false){

    val text = remember(value) { mutableStateOf(value) }
    //var inputValue = value
    //Log.e("in text fun", inputValue)
    var isError by remember { mutableStateOf(isError)}

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = text.value,
        onValueChange = { if (text.value.length <= maxLength) {
            text.value = it
            onTextChanged(text.value.trim())
        }},
        label = { Text(label) },
        singleLine = true,
        isError = isError,
        modifier = Modifier.fillMaxWidth(),
        supportingText = {
            if(maxLength < 40) {
                Text(
                    text = "${text.value.length} / $maxLength",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                )
            }
        },
        keyboardOptions = KeyboardOptions(autoCorrect = false),
        //TODO check text
        keyboardActions = KeyboardActions(onDone = {
            isError = if (isObligatory && text.value == ""){
                true
            } else {
                onTextChanged(text.value.trim())
                false
            }
            focusManager.clearFocus()
        })
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypesDropdownMenu(value: String?,
                      onSelect: (String) -> Unit) {

    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Type: ",
            textAlign = TextAlign.Start,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        val options = getTypesOfPlantsList().map { it.name }
        var expanded by remember { mutableStateOf(false) }
        var selectedOptionText by remember(value) { mutableStateOf(value) }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            TextField(
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                value = selectedOptionText!!,
                onValueChange = {},
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            selectedOptionText = selectionOption
                            onSelect(selectionOption)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PlantCareDatePicker(
    label: String,
    startingDate : Date,
    onDatePicked : (Date) -> Unit) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    var currentDate by remember(startingDate) { mutableStateOf(startingDate.toFormattedString())}
    //var selectedDate by rememberSaveable { mutableStateOf(currentDate) }

    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = label,
            textAlign = TextAlign.Start,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        val datePickerDialog =
            DatePickerDialog(context, { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                val newDate = Calendar.getInstance()
                newDate.set(year, month, dayOfMonth)
                currentDate = "$dayOfMonth ${month.toMonthName()} $year"
                onDatePicked(newDate.time)
                Log.e("selected date",newDate.time.toString())
            }, year, month, day)

        TextField(
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            value = currentDate,
            onValueChange = {},
            trailingIcon = { Icons.Default.DateRange },
            interactionSource = interactionSource
        )

        if (isPressed) {
            datePickerDialog.show()
        }
    }
}

fun Int.toMonthName(): String {
    return DateFormatSymbols().months[this]
}

fun Date.toFormattedString(): String {
    val simpleDateFormat = SimpleDateFormat("dd LLLL yyyy", Locale.getDefault())
    return simpleDateFormat.format(this)
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


@Preview(showBackground = true)
@Composable
fun preview(){
    PlantCareDatePicker(label = "Age : ", startingDate = GetDateInMillis(), onDatePicked = {})
}


@Composable
fun SavePlant(onButtonClick: (Plants) -> Unit,
              snackbarHostState : SnackbarHostState,
              goBack: () -> Unit,
              plant: Plants,
              currentlySavedPhoto : Uri
){
    var isValid = plant.name.isNotEmpty()

    val context = LocalContext.current
    //val file = File(context.filesDir, "test")
    Log.e("savePlant", plant.toString())
    //file.toUri()
    Button(onClick = {
        //todo empty image
        if (plant.photo != "" || plant.photo != currentlySavedPhoto.toString()){
            Log.e("worked","if started to save image")
            plant.photo = saveImageToInternalStorage(context, plant.photo!!.toUri())
        }
        onButtonClick(plant)
        if(plant.photo != "" || plant.photo != currentlySavedPhoto.toString()){
            deletePreviousPhoto(context, currentlySavedPhoto!!)
            Log.e("worked","if started to delete image")
        }
        goBack()
    }) {
        Text(text = "click")
    }
}
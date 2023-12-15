package com.example.plantcare.ui.plantCreationEdit

import android.net.Uri
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.plantcare.R
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.ui.components.PlantCareIconButton
import com.example.plantcare.ui.components.PlantCareImage

//var plantToCreate = Plants(0, "", "", "", "", 0,
//    "", emptyMap(), "")

@Composable
fun PlantCreationEditForm(plantMap: Map<Plants?, List<Tasks>>?){
    val plantToList = plantMap?.toList()
    val plant = plantToList?.get(0)?.first
    val tasks = plantToList?.get(0)?.second

    // Data
    var name = plant?.name
    var type = plant?.type
    var species = plant?.species
    var position = plant?.position
    var age = plant?.age
    var photo = plant?.photo
    var notes = plant?.notes
    var currentSeason = plant?.currentSeason
    //Log.d("uri2", photoUri.toString())
        //plant to Pair<Plants?, List<Tasks>>(plant.entries.)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ImageSection(photo = photo,
                    onSelect = {photo = it.toString() })
        PlantCareTextInput(value = name,
                           onTextChanged = {name = it},
                           label = "Name",
                           maxLength = 18)
    }
}

@Composable
fun ImageSection(photo : String?,
                 onSelect: (Uri?) -> Unit){

    val photoUri = remember { mutableStateOf<String?>(photo) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
       onSelect(uri)
        photoUri.value = uri.toString()
    }

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
                    launcher.launch(
                        PickVisualMediaRequest(
                        mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly))
                    Log.d("Click", "open gallery") },
                Modifier.padding(10.dp)
            )
        }
    }
}

@Composable
fun PlantCareTextInput(value: String?,
                       onTextChanged: (String?) -> Unit,
                       label: String,
                       maxLength: Int = 0){

    var inputValue by remember { mutableStateOf(value ?: "")}

    var isError by remember { mutableStateOf(false)}

    OutlinedTextField(value = inputValue,
                      onValueChange = { if (it.length <= maxLength) inputValue = it },
                      label = { Text(label) },
                      singleLine = true,
                      isError = isError,
                      modifier = Modifier.fillMaxWidth(),
                      supportingText = {
                          if(maxLength != 0) {
                              Text(
                                  text = "${inputValue.length} / $maxLength",
                                  modifier = Modifier.fillMaxWidth(),
                                  textAlign = TextAlign.End,
                              )
                          }
                      },
                      keyboardOptions = KeyboardOptions(autoCorrect = false),
                      //TODO check text
                      keyboardActions = KeyboardActions()
    )
}
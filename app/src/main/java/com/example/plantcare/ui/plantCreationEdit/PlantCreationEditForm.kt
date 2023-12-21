package com.example.plantcare.ui.plantCreationEdit

import android.net.Uri
import android.util.Log
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
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.plantcare.R
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.ui.components.PlantCareIconButton
import com.example.plantcare.ui.components.PlantCareImage
import com.example.plantcare.ui.utils.GetDateInMillis

//var plantToCreate = Plants(0, "", "", "", "", 0,
//    "", emptyMap(), "")

@Composable
fun PlantCreationEditForm(plantMap: Map<Plants?, List<Tasks>>?,
                          onButtonClick : (Plants) -> Unit){

    val plantToList = plantMap?.toList()
    val plant = plantToList?.get(0)?.first
    val tasks = plantToList?.get(0)?.second
    Log.e("PLANT id testing", plant?.name.toString())

    // Data
    var name by remember { mutableStateOf(plant?.name ?: "")}
    Log.d("input name", name)
    var type by remember { mutableStateOf(plant?.type ?: "")}
    var species by remember { mutableStateOf(plant?.species ?: "")}
    Log.d("input species", species)
    var position by remember { mutableStateOf(plant?.position ?: "")}
    var age by remember { mutableStateOf(plant?.age ?: "")}
    var photo by remember { mutableStateOf(plant?.photo ?: "")}
    var notes by remember { mutableStateOf(plant?.notes ?: "")}
    var currentSeason by remember { mutableStateOf(plant?.currentSeason ?: "")}

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ImageSection(photo = photo,
                    onSelect = {photo = it.toString() })
        PlantCareTextInput(value = name,
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
        PlantCareButton(onButtonClick = {onButtonClick(it)},
                                         name = name,
                                         type = type.orEmpty(),
                                         species = species.orEmpty(),
                                         photo = photo.orEmpty(),
                                         position = position.orEmpty())
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
                       onTextChanged: (String) -> Unit,
                       label: String,
                       maxLength: Int = 40,
                       isObligatory: Boolean = false){

    var inputValue by remember { mutableStateOf(value ?: "")}

    var isError by remember { mutableStateOf(false)}

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
fun PlantCareButton(onButtonClick: (Plants) -> Unit,
                    name: String,
                    type : String?,
                    species: String?,
                    photo: String?,
                    position: String?){
    var plant = Plants(0, name, type, species, position, GetDateInMillis(), photo,
        mapOf("" to ""), "summer"
    )
    Button(onClick = { onButtonClick(plant) }) {
        Text(text = "click")
    }
}
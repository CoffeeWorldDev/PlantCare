package com.example.plantcare.ui.plantCreationEdit

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.plantcare.R


@Composable
fun PlantCreationEdit(plantId: Long,
                      upPress: () -> Unit,
                      viewModel: PlantCreationEditViewModel = hiltViewModel()
) {

    if (plantId.toInt() != -1){
        viewModel.getPlantWithId(plantId)
    }
    val plantEditCreationUiState by viewModel.uiState.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()
    Log.e("PLANT id", plantEditCreationUiState.plantMap.toString())
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Up(upPress)
            PlantCreationEditForm(plantEditCreationUiState.plantMap)
          //  PlantCreationEditTasksList(plants[3])
          //  Spacer(modifier = Modifier.height(30.dp))
        }
    }
}




@Composable
private fun Up(upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .size(36.dp)
            .background(
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.32f),
                shape = CircleShape
            )
    ) {
        Icon(
            Icons.Filled.ArrowBack,
            contentDescription = stringResource(R.string.label_back)
        )
    }
}
//TODO edit text example to delete
//@Composable
//fun EditNumberField(
//    label: Int,
//    value: String,
//    onValueChanged: (String) -> Unit,
//    modifier: Modifier = Modifier
//)
//@Composable
//fun EditNumberField(
//    @StringRes label: Int,
//    keyboardOptions: KeyboardOptions,
//    // ...
//){
//    TextField(
//        //...
//        keyboardOptions = keyboardOptions
//    )
//}
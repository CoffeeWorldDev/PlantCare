package com.example.plantcare.ui.plantCreationEdit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.plantcare.ui.home.HomeViewModel


@Composable
fun PlantCreationEditBase(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.verticalScroll(scrollState)) {
          //  PlantCreationEditForm(plants)
          //  PlantCreationEditTasksList(plants[3])
          //  Spacer(modifier = Modifier.height(30.dp))
        }
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
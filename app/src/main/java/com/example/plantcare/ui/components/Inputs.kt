package com.example.plantcare.ui.components

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun TextInputRow(
    label : String,
    value : String,
    onTextChanged: (String) -> Unit,
    maxLength: Int = 40,
    isError: Boolean = false
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp, 0.dp, 5.dp, 10.dp)
    ) {
        Text(
            text = "$label: ",
            textAlign = TextAlign.Start,
            modifier = Modifier.width(80.dp)
        )
        PlantCareTextInput(
            value = value,
            onTextChanged = onTextChanged,
            maxLength = maxLength,
            isError = isError,
            modifier = Modifier
        )
    }
}
@Composable
fun DropdownMenuRow(
    label : String,
    value : String,
    onSelect: (String) -> Unit,
    options : List<String>
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp, 0.dp, 5.dp, 10.dp)
    ) {
        Text(
            text = "$label: ",
            textAlign = TextAlign.Start,
            modifier = Modifier.width(80.dp)
        )
        PlantCareDropDownMenu(
            value = value,
            onSelect = onSelect,
            options = options)
    }
}
@Composable
fun DatePickerMenuRow(
    label : String,
    value : Date,
    onSelect: (Date) -> Unit
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp, 0.dp, 5.dp, 10.dp)
    ) {
        Text(
            text = "$label: ",
            textAlign = TextAlign.Start,
            modifier = Modifier.width(80.dp)
        )
        PlantCareDatePicker(
            startingDate = value,
            onDatePicked = onSelect)
    }
}

@Composable
fun PlantCareTextInput(value: String,
                       onTextChanged: (String) -> Unit,
                       maxLength: Int = 40,
                       isError: Boolean = false,
                       modifier: Modifier){

    val text = remember(value) { mutableStateOf(value) }
    var error = if(text.value.isNotEmpty()){false} else isError

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = text.value,
        onValueChange = { if (text.value.length <= maxLength) {
            error = false
            text.value = it
            onTextChanged(text.value.trim())
        }},
        singleLine = true,
        isError = error,
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
        keyboardActions = KeyboardActions(onAny = {
            onTextChanged(text.value.trim())
            focusManager.clearFocus()
        })
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantCareDropDownMenu(
    value: String?,
    onSelect: (String) -> Unit,
    options : List<String>) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember(value) { mutableStateOf(value) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            modifier = Modifier.menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            value = selectedOptionText!!,
            onValueChange = {},
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            supportingText = {Text(text = "")}
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

@Composable
fun PlantCareDatePicker(
    startingDate : Date,
    onDatePicked : (Date) -> Unit) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    var currentDate by remember(startingDate) { mutableStateOf(startingDate.toFormattedString())}

    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val datePickerDialog =
        DatePickerDialog(context, { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val newDate = Calendar.getInstance()
            newDate.set(year, month, dayOfMonth)
            currentDate = "$dayOfMonth ${month.toMonthName()} $year"
            onDatePicked(newDate.time)
            Log.e("selected date",newDate.time.toString())
        }, year, month, day)
    datePickerDialog.datePicker.maxDate = System.currentTimeMillis()

    TextField(
        modifier = Modifier.fillMaxWidth(),
        readOnly = true,
        value = currentDate,
        onValueChange = {},
        trailingIcon = { Icons.Default.DateRange },
        interactionSource = interactionSource,
        supportingText = {Text(text = "")}
    )

    if (isPressed) {
        datePickerDialog.show()
    }
}

fun Int.toMonthName(): String {
    return DateFormatSymbols().months[this]
}

fun Date.toFormattedString(): String {
    val simpleDateFormat = SimpleDateFormat("dd LLLL yyyy", Locale.getDefault())
    return simpleDateFormat.format(this)
}

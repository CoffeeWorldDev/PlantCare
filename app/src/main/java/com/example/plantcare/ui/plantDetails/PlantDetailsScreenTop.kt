package com.example.plantcare.ui.plantDetails

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks

@Composable
fun PlantDetailsScreenTop(plants: State<Map<Plants, List<Tasks>>>) {


//    Row(modifier = Modifier
//        .fillMaxWidth()
//        .height(200.dp),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically) {
//
//        Image(painter = painterResource(id = R.drawable.baseline_image_24),
//              contentDescription = "plant image",
//              modifier = Modifier
//                  .border(1.dp, color = Color.Black)
//                  .fillMaxWidth(0.5f)
//                  .fillMaxHeight()
//        )
//        Column(modifier = Modifier
//            .fillMaxHeight()
//            .fillMaxWidth(),
//               verticalArrangement = Arrangement.SpaceEvenly,
//               horizontalAlignment = Alignment.End) {
//                    Card(
//                        shape = TitlesBackgroundShape(150f, 100f),
//                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
//                        elevation = CardDefaults.cardElevation(
//                            defaultElevation = 8.dp
//                        ),
//                        modifier = Modifier
//                            .graphicsLayer {
//                                this.rotationZ = 180f
//                            }
//                    ){
//                        //TODO changing box side depending on screen size?
//                        Box( modifier = Modifier
//                            .height(getScreenHeight()),
//                            contentAlignment = Alignment.CenterEnd) {
//                            formatName(name = plants[3].name)
//                        }
//                    }
//            //TODO make a more flexible age text
//            val age : String
//            if(plants[3].age!!.toInt() > 1){
//                age = "${plants[3].age.toString()} days old"
//            }else{
//                age = "${plants[3].age.toString()} day old"
//            }
//            Text(
//                text = age,
//                fontWeight = FontWeight.SemiBold,
//                textAlign = TextAlign.Center,
//                fontSize = 20.sp,
//                color = MaterialTheme.colorScheme.primary,
//                modifier = Modifier
//                    .fillMaxWidth()
//                ,
//                fontStyle = FontStyle.Italic,
//                style = MaterialTheme.typography.titleLarge
//            )
//            if(plants[3].species!=""){
//                Text(
//                    text = plants[3].species!!,
//                    fontWeight = FontWeight.SemiBold,
//                    textAlign = TextAlign.Center,
//                    fontSize = 20.sp,
//                    color = MaterialTheme.colorScheme.primary,
//                    modifier = Modifier
//                     .fillMaxWidth()
//                    ,
//                    fontStyle = FontStyle.Italic,
//                    style = MaterialTheme.typography.titleLarge
//                )
//            }
//
//        }
//
//    }
//}


@Composable
fun getScreenWidth(): Dp {
    val density = LocalDensity.current;
    val configuration = LocalConfiguration.current;
    val screenWidthDp = with(density) {configuration.screenWidthDp.dp}
    val divider : Int = when {
        screenWidthDp <= 360.dp -> 4
        screenWidthDp <= 400.dp -> 5
        screenWidthDp <= 450.dp -> 6
        else -> 7
    }
    Log.e("HERE", screenWidthDp.toString())
    return screenWidthDp / divider
}
@Composable
fun getScreenHeight(): Dp {
    val density = LocalDensity.current;
    val configuration = LocalConfiguration.current;
    val screenHeightDp = with(density) {configuration.screenHeightDp.dp}
    val divider : Int = when {
        screenHeightDp <= 720.dp -> 9
        screenHeightDp <= 800.dp -> 10
        screenHeightDp <= 900.dp -> 9
        else -> 12
    }
    Log.e("HERE", (screenHeightDp / divider).toString())
    return screenHeightDp / divider
}


@Composable
fun formatName(name : String) {
    var formattedName = name
    var maxLength = name.length
    var isOverflowOn = TextOverflow.Clip

    if (name.contains(" ")) {
        formattedName = name.replace(" ", "\n")
        val lines = formattedName.split("\\s".toRegex()).toTypedArray()

        lines.forEach {
            if (it.toString().length > 5) {
                maxLength = it.toString().length + 5
                Log.e("HERE", maxLength.toString())
            }
        }
        if (lines.size > 3) isOverflowOn = TextOverflow.Ellipsis
    }
    var size: TextUnit = 10.em

    if (maxLength <= 6) {
        size
    } else if (maxLength <= 8) {
        size = 7.8.em
    } else if (maxLength <= 9) {
        size = 7.2.em
    } else if (maxLength <= 11) {
        size = 5.9.em
    } else if (maxLength <= 13) {
        size = 4.7.em
    } else if (maxLength <= 15) {
        size = 4.em
    } else {
        size = 3.5.em
    }

    Text(
        text = formattedName,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
        fontSize = size,
        lineHeight = 20.sp,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = Modifier
            .padding(5.dp, 0.dp, 0.dp, 0.dp)
            .graphicsLayer {
                this.rotationZ = 180f
            },
        fontStyle = FontStyle.Italic,
        style = MaterialTheme.typography.titleLarge,
        overflow = isOverflowOn
    )

}
}


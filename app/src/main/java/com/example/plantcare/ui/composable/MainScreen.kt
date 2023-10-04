package com.example.plantcare.ui.composable

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.plantcare.data.model.Plants
import com.example.plantcare.data.model.Tasks
import com.example.plantcare.ui.PlantCareNavHost
import com.example.plantcare.ui.PlantsViewModel
import com.example.plantcare.ui.PlantsViewModelFactory
import com.example.plantcare.ui.composable.BottomNavBar.BottomNav

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    factory: PlantsViewModelFactory,
    plantsViewModel: PlantsViewModel = viewModel(factory = factory),
    modifier : Modifier = Modifier
) {
    var navController : NavHostController = rememberNavController()
    var plantsList = plantsViewModel.getPlants()
    Log.e("HERE", plantsList.children.toString())
    Scaffold(
        bottomBar = {
            BottomAppBar(modifier = Modifier) {
                BottomNav(navController)
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(
                PaddingValues(
                    0.dp,
                    0.dp,
                    0.dp,
                    innerPadding.calculateBottomPadding()
                )
            )
        ) {
           // PlantCareNavHost(navController, plantsList)
        }
    }
}

fun getPlants() = listOf(
    Plants(1,"plant1","succulent","a species","", "69", "", mapOf(),
      //  listOf( Tasks(0, 0, "task 1", true, "", 0, true, 0, "", 0, ""),
      //      Tasks(0, 0, "task 2", true, "", 0, true, 0, "", 0, "")
      //  ),
        ""   ),
    Plants(2,"plant2which" + "has a long","cactus","another species","", "420", "", mapOf(),
      //  listOf( Tasks(0, 0, "task 1", true, "", 0, true, 0, "", 0,""),
      //      Tasks(0, 0, "task 2", true, "", 0, true, 0, "", 0,""),
      //      Tasks(0, 0, "task 3", true, "", 0, true, 0, "", 0,""),
      //      Tasks(0, 0, "task 4", true, "", 0, true, 0, "", 0,"")
      //  ),
        ""   ),
    Plants(3,"plant3g","succulent","species again","", "453", "", mapOf(),
      //  listOf( Tasks(0, 0, "task 1", true, "", 0, true, 0, "", 0, ""),
      //      Tasks(0, 0, "task 2", true, "", 0, true, 0, "", 0, "")
      //  ),
        ""   ),
    Plants(4,"Nipple","succulent","nipple cactus","", "143", "", mapOf("20/3/23" to "here's a note", "9/29/23" to "Here's a much longer to not to check how this stuff looks in multiple lines and yeah it seems to be working okay", "30/60/40" to "another one to see if it's scrollable"),
       // listOf(
       //   Tasks(0, 0, "task 1", true, "", 0, true, 6, "", 7, "winter"),
       //   Tasks(0, 0, "task 2", true, "", 0, true, 9, "", 3, "summer"),
       //   Tasks(0, 0, "task 3", true, "", 0, true, 9, "", 3, "summer")
       // ) ,
        "summer"
    ),
    Plants(5,"plant5","cactus","species part 5","", "786", "", mapOf(),
       // listOf( Tasks(0, 0, "task 1", true, "", 0, true, 5, "", 3, ""),
       //     Tasks(0, 0, "task 2", true, "", 0, true, 0, "", 0, "")
       // ),
        ""   ),
)

import androidx.navigation.testing.TestNavHostController
import androidx.activity.ComponentActivity
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.plantcare.repository.HiltTestActivity
import com.example.plantcare.ui.MainActivity
import org.junit.Rule
import com.example.plantcare.ui.PlantCareNavHost
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject


@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
@HiltAndroidTest
class NavTesting {

//   @get:Rule(order = 0)
//   var hiltRule = HiltAndroidRule(this)

//   // Executes tasks in the Architecture Components in the same thread
//   @get:Rule(order = 1)
//   var instantTaskExecutorRule = InstantTaskExecutorRule()

//   @get:Rule(order = 2)
//   val composeTestRule = createAndroidComposeRule<HiltTestActivity>()
//   private val activity get() = composeTestRule.activity


//   @Before
//   fun init() {
//       hiltRule.inject()
//   }
//    @get:Rule
//    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
//
//    private lateinit var navController: TestNavHostController
//
//    //val mockMyViewModel= PlantsViewModel()
//
//    @Before
//    fun setUpNavHost(){
//
//        composeTestRule.setContent {
//            navController = TestNavHostController(LocalContext.current).apply {
//                navigatorProvider.addNavigator(ComposeNavigator())
//            }
//            PlantCareNavHost(navController = navController)
//        }
//    }

//    @get:Rule
//    val composeTestRule = createComposeRule()
//    lateinit var navController: TestNavHostController
//
//    @Before
//    fun setupAppNavHost() {
//        composeTestRule.setContent {
//            navController = TestNavHostController(LocalContext.current)
//            navController.navigatorProvider.addNavigator(ComposeNavigator())
//            PlantCareNavHost(navController = navController)
//        }
//    }

    @Test
    fun navHost_verifyStartDestination() {
       // assertEquals(BottomNavItem.HomeScreen, navController.currentBackStackEntry?.destination?.route)
    }
}

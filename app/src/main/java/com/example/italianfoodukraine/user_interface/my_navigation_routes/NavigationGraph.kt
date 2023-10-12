package com.example.italianfoodukraine.user_interface.my_navigation_routes


import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.italianfoodukraine.user_interface.MyMainDisplay
import com.example.italianfoodukraine.user_interface.Displays.MyOneRecipeDispaly
import com.example.italianfoodukraine.user_interface.Displays.SplashScreen

@Composable
fun NavigationComponent() {
    val navController = rememberNavController()
    val toBackScreen = { navController.popBackStack() }
    val toOneRecipeScreen =
        { id: String -> navController.navigate("${Screen.ONE_RECIPE_SCREEN.name}/$id") }
    val configurationOfScreen = LocalConfiguration.current

    NavHost(
        navController = navController,
        startDestination = Screen.SPLASH_SCREEN.name,
    ) {

        //MAIN SCREEN//////////////////////////////////////////////////////////////////////////////
        composable(
            route = Screen.MAIN_USER_SCREEN.name
        ) {
            MyMainDisplay(
                configurationOfScreen = configurationOfScreen,
                toOneRecipeScreen = toOneRecipeScreen,
            )
        }
        /////////////////////////////////////////////////////////////////////////////////
        composable(
            route = "${Screen.ONE_RECIPE_SCREEN}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            val routeId = it.arguments?.getString("id").orEmpty()

            MyOneRecipeDispaly(
                //configurationOfScreen = configurationOfScreen,
                navController = navController,
                routeId = routeId

            )
        }
        ///SPLASH SCREEN/////////////////////////////////////////////////////////////////////
        composable(route = Screen.SPLASH_SCREEN.name){
            SplashScreen() {
                navController.navigate(Screen.MAIN_USER_SCREEN.name){
                    popUpTo(Screen.SPLASH_SCREEN.name){
                        inclusive = true
                    }
                }
            }
        }
    }
}


enum class Screen {
    MAIN_USER_SCREEN,
    ONE_RECIPE_SCREEN,
    SPLASH_SCREEN
}
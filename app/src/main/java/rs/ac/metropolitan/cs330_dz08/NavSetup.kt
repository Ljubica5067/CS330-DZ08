package rs.ac.metropolitan.cs330_dz08

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import rs.ac.metropolitan.cs330_dz08.screens.FoodViewModel
import rs.ac.metropolitan.cs330_dz08.screens.HomeScreen
import android.widget.Toast
import rs.ac.metropolitan.cs330_dz08.screens.FoodDetailScreen

@Composable
fun NavSetup(navController:NavHostController)
{
    val vm: FoodViewModel= viewModel()
    vm.navController=navController

    NavHost(navController = navController, startDestination = FoodListRoute.Home.route)
    {
        composable(route=FoodListRoute.Home.route)
        {
            HomeScreen(vm)
        }
        composable(route = FoodListRoute.FoodDetailScreen.route){navBackStackEntry ->
            val elementId=navBackStackEntry.arguments?.getString("elementId")
            if(elementId!=null)
            {
                FoodDetailScreen(vm,elementId.toLong())
            }
            else
            {
                Toast.makeText(navController.context, "Error, elementId is required!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
package rs.ac.metropolitan.cs330_dz08.screens

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import rs.ac.metropolitan.cs330_dz08.Food
import rs.ac.metropolitan.cs330_dz08.FoodListRoute
import rs.ac.metropolitan.cs330_dz08.FoodModel
import java.net.URL

class FoodViewModel:ViewModel() {
    lateinit var navController:NavHostController
    private  lateinit var foodList:MutableList<Food>
    var granted= mutableStateOf(false)

    init{
        val jsonFile=loadResource("assets/food.json").readText()
        val model=FoodModel.fromJson(jsonFile)
        model?.let{
            foodList=it.recipes.toMutableStateList()
        }
    }

    fun loadResource(path:String):URL
    {
        println("Loading: $path")
        return Thread.currentThread().contextClassLoader.getResource(path)
    }

    val recipes:List<Food>
        get() = foodList.sortedBy { it.id }

    fun getFood(id:Long):Food?
    {
        return foodList.find { it.id.toLong()==id }
    }

    fun navigateToFoodDetails(id:Long)
    {
        navController.navigate(FoodListRoute.FoodDetailScreen.createRoute(id))
    }

    fun goBack()
    {
        navController.popBackStack()
    }
}
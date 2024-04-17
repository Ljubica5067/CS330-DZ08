package rs.ac.metropolitan.cs330_dz08.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import androidx.compose.ui.graphics.Color
import rs.ac.metropolitan.cs330_dz08.Food


@Composable
fun FoodDetailScreen(vm:FoodViewModel,foodId:Long)
{
FoodDetailScreenContent(vm.getFood(foodId)) {
    vm.goBack()
}
}

@Composable
fun FoodDetailScreenContent(food: Food?,goBack:()->Unit)
{
    Box(modifier=Modifier.fillMaxSize(),contentAlignment=Alignment.TopCenter)
    {
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            item{
                Row(modifier=Modifier.fillMaxWidth(), horizontalArrangement =Arrangement.Start){
                    IconButton(
                        modifier = Modifier.background(Color.Transparent),
                        onClick = goBack) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription ="Back",tint=MaterialTheme.colorScheme.primary, modifier = Modifier.size(36.dp))
                    }
                }
            }
            item { AsyncImage(model = food?.image, contentDescription = null, modifier = Modifier
                .size(240.dp)
                .clip(
                    CircleShape
                ))}
            item { FoodBasicData(food=food) }
            item{FoodIngredientsData(food=food)}
            item{FoodStepsData(food=food)}
        }
    }
}

@Composable
fun RowInCard(leftText: String, rightText: String){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = leftText,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondaryContainer,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = rightText,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun FoodBasicData(food:Food?)
{
    Card(shape = RoundedCornerShape(20.dp), modifier = Modifier.padding(16.dp)){
        Column(modifier = Modifier.padding(8.dp)){
            RowInCard(leftText = "title", rightText = "${food?.title}")
            RowInCard(leftText = "difficulty", rightText = "${food?.difficulty}")
            RowInCard(leftText = "portion", rightText = "${food?.portion}")
            RowInCard(leftText = "time", rightText = "${food?.time}")
            RowInCard(leftText = "description", rightText = "${food?.description}")
        }
    }
}

@Composable
fun FoodIngredientsData(food: Food?)
{
    Card(shape = RoundedCornerShape(20.dp),modifier = Modifier.padding(16.dp)){
        Column(modifier=Modifier.padding(8.dp)) {
            food?.ingredients?.forEach{ingredient->
            RowInCard(leftText = "ingredient", rightText = ingredient)}
        }
    }
}

@Composable
fun FoodStepsData(food: Food?) {
    Card(shape = RoundedCornerShape(20.dp), modifier = Modifier.padding(16.dp)) {
        Column(modifier = Modifier.padding(8.dp)) {
            food?.method?.forEach { step ->
                step.forEach { (title, description) ->
                    Text(text = "$title: $description")
            }
        }
    }
}}




@Preview
@Composable
fun FoodDetailScreenPreview(){
    val vm:FoodViewModel= viewModel()
    vm.navController= rememberNavController()
    FoodDetailScreen(vm, foodId = 1)
}
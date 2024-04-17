package rs.ac.metropolitan.cs330_dz08.screens

import android.Manifest
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import rs.ac.metropolitan.cs330_dz08.Food

@Composable
fun HomeScreen(vm:FoodViewModel)
{
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()){
            isGranted ->vm.granted.value = isGranted
    }

    Column {
        if(!vm.granted.value)
        {
            InternetPermission(launcher)
        }else
        {
            ListFoods(vm=vm)
        }
    }
}

@Composable
fun ListFoods(vm:FoodViewModel= viewModel())
{
    LazyColumn(modifier=Modifier.padding(top = 65.dp)){
        items(vm.recipes){
            food->
            PresentFood(food = food){
                vm.navigateToFoodDetails(it)
            }
        }
    }
}

@Composable
fun PresentFood(food:Food,onFoodSelected:(Long)->Unit)
{
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { onFoodSelected(food.id.toLong())})
    {
        AsyncImage(model = food.image, contentDescription = null, modifier = Modifier
            .size(80.dp)
            .clip(
                CircleShape
            )
        )
        Column {
            Text(text = food.title,fontSize=16.sp)
            Text(text = food.portion, color = Color.Gray, modifier = Modifier.padding(all=4.dp))
        }
        Spacer(Modifier.weight(1f))
        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(end=8.dp)){
            Text(text = food.difficulty, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun InternetPermission(launcher: ManagedActivityResultLauncher<String, Boolean>) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Internet permission not granted",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Magenta,
                modifier = Modifier.padding(8.dp)
            )
            Button(onClick = { launcher.launch(Manifest.permission.INTERNET) }) {
                Text("Request permission")
            }
        }
    }
}

@Preview
@Composable
fun InternetPermissionPreview() {
    InternetPermission(rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { })
}

@Preview
@Composable
fun ListFoodsPreview()
{
    val vm:FoodViewModel= viewModel()
    vm.navController= rememberNavController()
    ListFoods(vm)
}
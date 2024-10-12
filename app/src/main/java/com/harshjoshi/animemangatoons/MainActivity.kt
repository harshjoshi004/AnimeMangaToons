package com.harshjoshi.animemangatoons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.harshjoshi.animemangatoons.detail.MainViewModel
import com.harshjoshi.animemangatoons.detail.NavigationManager
import com.harshjoshi.animemangatoons.navigation.Screens
import com.harshjoshi.animemangatoons.navigation.listOfScreens
import com.harshjoshi.animemangatoons.ui.theme.AnimeMangaToonsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val mainViewModel: MainViewModel = viewModel()
            val navController = rememberNavController()
            AnimeMangaToonsTheme {
                Scaffold(
                    topBar = {
                        TopBar(viewModel = mainViewModel, navController = navController)
                    },
                    bottomBar = {
                        if (mainViewModel.currentScreen.value != Screens.DetailScreen)
                            BottomBar(viewModel = mainViewModel, navController = navController)
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavigationManager(mainViewModel = mainViewModel, navController = navController, padval = innerPadding)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(viewModel: MainViewModel, navController: NavHostController) {
    CenterAlignedTopAppBar(title = { Text(text = viewModel.currentScreen.value.title, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)},
        navigationIcon = {
            if (viewModel.currentScreen.value == Screens.DetailScreen)
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = null)
            }
        }
    )
}

@Composable
fun BottomBar(viewModel: MainViewModel, navController: NavHostController){
    NavigationBar(
        tonalElevation = 8.dp,
        modifier = Modifier.wrapContentHeight()){
        listOfScreens.forEach { screen->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primary,
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary
                ),
                selected = viewModel.currentScreen.value == screen,
                onClick = {
                    viewModel.setCurrentScreen(screen)
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(imageVector = screen.icon!!, contentDescription = null, modifier = Modifier.size(35.dp))
                }
            )
        }
    }
}
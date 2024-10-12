package com.harshjoshi.animemangatoons.detail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.harshjoshi.animemangatoons.home.HomePage
import com.harshjoshi.animemangatoons.home.HomeViewModel
import com.harshjoshi.animemangatoons.navigation.Screens
import com.harshjoshi.animemangatoons.saved.MyViewModelFactory
import com.harshjoshi.animemangatoons.saved.SavedPage
import com.harshjoshi.animemangatoons.saved.SavedViewModel

@Composable
fun NavigationManager(
    padval: PaddingValues,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    val context = LocalContext.current
    val savedViewModel: SavedViewModel = viewModel(factory = MyViewModelFactory(context))
    val homeViewModel: HomeViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route,
        modifier = Modifier.padding(padval)
    ){
        composable(Screens.HomeScreen.route){
            mainViewModel.setCurrentScreen(Screens.HomeScreen)
            HomePage(viewModel = homeViewModel, viewModel2 = savedViewModel, navController = navController, mainViewModel = mainViewModel)
        }
        composable(Screens.SavedScreen.route){
            mainViewModel.setCurrentScreen(Screens.SavedScreen)
            SavedPage(viewModel = savedViewModel, navController = navController, mainViewModel = mainViewModel)
        }
        composable(Screens.DetailScreen.route){
            mainViewModel.setCurrentScreen(Screens.DetailScreen)
            DetailPage(viewModel = mainViewModel, savedViewModel = savedViewModel, homeViewModel = homeViewModel,  navController = navController)
        }
        composable(Screens.DetailScreen2.route){
            mainViewModel.setCurrentScreen(Screens.DetailScreen2)
            DetailPage2(viewModel = mainViewModel, savedViewModel = savedViewModel, homeViewModel = homeViewModel,  navController = navController)
        }
    }
}
package com.harshjoshi.animemangatoons.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.harshjoshi.animemangatoons.navigation.Screens
import com.harshjoshi.animemangatoons.roomDatabase.Webtoon
import com.harshjoshi.animemangatoons.roomDatabase.dummyWebtoon

class MainViewModel:ViewModel() {
    private val _currentScreen = mutableStateOf<Screens>(Screens.HomeScreen)
    val currentScreen: MutableState<Screens> = _currentScreen

    private val _selectedState = mutableStateOf<Webtoon>(dummyWebtoon)
    val selectedState: MutableState<Webtoon> = _selectedState

    fun setCurrentScreen(screen: Screens){
        _currentScreen.value = screen
    }

    fun selectWebtoon(webtoon: Webtoon, navigate: ()-> Unit){
        _selectedState.value = webtoon
        navigate()
    }

    fun getSelectedWebtoon(): Webtoon{
        return _selectedState.value
    }
}
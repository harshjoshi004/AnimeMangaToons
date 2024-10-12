package com.harshjoshi.animemangatoons.saved

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.harshjoshi.animemangatoons.MainActivity
import com.harshjoshi.animemangatoons.roomDatabase.Webtoon
import com.harshjoshi.animemangatoons.roomDatabase.WebtoonDao
import com.harshjoshi.animemangatoons.roomDatabase.WebtoonDatabase
import com.harshjoshi.animemangatoons.roomDatabase.dummyWebtoon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedViewModel(context: Context):ViewModel() {
    private var db: WebtoonDatabase = Room
        .databaseBuilder(context, WebtoonDatabase::class.java, "saved-webtoons")
        .build()
    private var myDao :WebtoonDao = db.webtoonDao()

    private val _listState = mutableStateListOf<Webtoon>()
    val listState: List<Webtoon> = _listState

    fun getWebtoons() {
        viewModelScope.launch(Dispatchers.IO) {
            myDao.getWebtoons().collect{ webtoons->
                _listState.clear()
                _listState.addAll(webtoons)
            }
        }
    }

    fun upsertWebtoon(webtoon: Webtoon){
        viewModelScope.launch(Dispatchers.IO) {
            myDao.upsertWebtoon(webtoon)
        }
    }

    fun removeWebtoon(webtoon: Webtoon){
        viewModelScope.launch(Dispatchers.IO) {
            myDao.deleteWebtoon(webtoon)
        }
    }
}
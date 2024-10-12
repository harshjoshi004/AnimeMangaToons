package com.harshjoshi.animemangatoons.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.harshjoshi.animemangatoons.detail.MainViewModel
import com.harshjoshi.animemangatoons.displayCards.CompactWebtoonCard
import com.harshjoshi.animemangatoons.navigation.Screens
import com.harshjoshi.animemangatoons.roomDatabase.Webtoon
import com.harshjoshi.animemangatoons.saved.SavedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    viewModel: HomeViewModel,
    viewModel2: SavedViewModel,
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    val listState = viewModel.listState
    val onClick:(Webtoon)->Unit = { webtoon->
        mainViewModel.selectWebtoon(webtoon) {
            navController.navigate(Screens.DetailScreen.route)
        }
    }
    var searchStr by remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = Unit){
        viewModel.getWebtoonsFromServer()
    }

    if(listState.isNotEmpty())
        LazyColumn(modifier = Modifier
            .animateContentSize()
        ) {
            item {
                OutlinedTextField(
                    value = searchStr,
                    onValueChange = { it-> searchStr = it },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp),
                    label = { Text(text = "Search webtoons")},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.secondaryContainer
                    ),
                    trailingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) }
                )
            }

            item { Spacer(modifier = Modifier.size(16.dp)) }

            listState.filter { webtoon->
                webtoon.title.contains(searchStr) ||
                        webtoon.rating.toString().contains(searchStr) ||
                        webtoon.description.contains(searchStr)
            }.forEach { webtoon->
                item {
                    CompactWebtoonCard(webtoon = webtoon) {
                        onClick(webtoon)
                    }
                }
            }
        }
    else
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.wrapContentSize()) {
                CircularProgressIndicator()
                Text(
                    text = "Make sure your device is connected to the Internet!",
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 30.dp)
                )
            }
        }
}
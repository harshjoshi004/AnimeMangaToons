package com.harshjoshi.animemangatoons.detail

import android.media.Rating
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.harshjoshi.animemangatoons.home.HomeViewModel
import com.harshjoshi.animemangatoons.saved.SavedViewModel

@Composable
fun DetailPage(
    viewModel: MainViewModel,
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    savedViewModel: SavedViewModel
) {
    val context = LocalContext.current
    val webtoon = viewModel.getSelectedWebtoon()
    val dialogOpen = remember {
        mutableStateOf(false)
    }

    RatingDialogue(dialogOpen = dialogOpen) { rating ->
        homeViewModel.giveRating(webtoon, rating, context)
        navController.popBackStack()
    }

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Image(
            rememberAsyncImagePainter(model = webtoon.imageUrl),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(8.dp))

        Text(text = webtoon.title,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            softWrap = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )

        Text(text = "Rating: " + webtoon.rating.toString(),
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(text = "Saved By " + webtoon.savedByCount + " People",
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(text = "Date Created: " + webtoon.dateCreated,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(8.dp))

        Text(text = webtoon.description, modifier = Modifier.padding(horizontal = 8.dp))
        Spacer(modifier = Modifier.size(8.dp))

        Row(modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = {
                savedViewModel.upsertWebtoon(webtoon)
                homeViewModel.updateSave(webtoon, context)
                navController.popBackStack()
            }) {
                Text(text = "Add to Saved")
            }
            ElevatedButton(onClick = { dialogOpen.value = true }) {
                Text(text = "Give Rating")
            }
        }

        Spacer(modifier = Modifier.size(16.dp))
    }
}
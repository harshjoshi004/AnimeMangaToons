package com.harshjoshi.animemangatoons.displayCards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.harshjoshi.animemangatoons.roomDatabase.Webtoon

@Composable
fun WebtoonDetailCard(webtoon: Webtoon) {
    val onClick = {}
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 8.dp, bottom = 8.dp)
    ) {
        val (title, rating, date, savedBy) = createRefs()

        Text(text = webtoon.title,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            softWrap = true,
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .widthIn(max = 200.dp)
        )

        Text(text = "Rating: " + webtoon.rating.toString(),
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            modifier = Modifier.constrainAs(rating) {
                top.linkTo(title.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Text(text = "Saved By " + webtoon.savedByCount + " People",
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            modifier = Modifier.constrainAs(savedBy) {
                top.linkTo(rating.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Text(text = "Date Created: " + webtoon.dateCreated,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            modifier = Modifier.constrainAs(date) {
                top.linkTo(savedBy.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}
package com.harshjoshi.animemangatoons.displayCards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.harshjoshi.animemangatoons.roomDatabase.Webtoon

@Composable
fun CompactWebtoonCard(webtoon: Webtoon, onClick:()->Unit){
    Column {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 8.dp, bottom = 8.dp)
        ) {
            val (image, title, rating, date, button) = createRefs()

            IconButton(onClick = { onClick() }, modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }) {
                Icon(Icons.Default.ArrowForwardIos, contentDescription = null)
            }

            Image(
                painter = rememberAsyncImagePainter(model = webtoon.imageUrl),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(15))
                    .clickable { onClick() }
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                contentDescription = null
            )

            Text(text = webtoon.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                softWrap = true,
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                        start.linkTo(image.end, margin = 8.dp)
                    }
                    .widthIn(max = 200.dp)
            )

            Text(text = "Rating: " + webtoon.rating.toString(),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                modifier = Modifier.constrainAs(rating) {
                    top.linkTo(title.bottom)
                    start.linkTo(title.start)
                }
            )

            Text(text = "Date Created: " + webtoon.dateCreated,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
                modifier = Modifier.constrainAs(date) {
                    top.linkTo(rating.bottom)
                    start.linkTo(rating.start)
                }
            )
        }

        Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = MaterialTheme.colorScheme.secondaryContainer)

        Spacer(modifier = Modifier.size(8.dp))
    }
}
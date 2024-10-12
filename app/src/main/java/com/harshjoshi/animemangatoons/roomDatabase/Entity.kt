package com.harshjoshi.animemangatoons.roomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved-webtoons")
data class Webtoon(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    val title: String = "",
    val description: String = "",

    @ColumnInfo(name = "image_url")
    val imageUrl: String = "",

    val rating: Float = 0f,

    @ColumnInfo(name = "rating_count")
    val ratingCount: Int = 0,

    @ColumnInfo(name = "saved-by-count")
    val savedByCount: Int = 0,

    @ColumnInfo(name = "date_created")
    val dateCreated: String = ""
)

val dummyWebtoon = Webtoon(
    id = 1, // You can assign any ID you want
    title = "Sample Webtoon",
    description = "This is a sample webtoon description.",
    imageUrl = "", // Empty image URL
    rating = 4.5f,
    ratingCount = 1234,
    savedByCount = 5678,
    dateCreated = "2023-11-16"
)
package com.harshjoshi.animemangatoons.roomDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WebtoonDao {
    @Query("SELECT * FROM `saved-webtoons`")
    fun getWebtoons(): Flow<List<Webtoon>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertWebtoon(webtoon: Webtoon)

    @Delete
    suspend fun deleteWebtoon(webtoon: Webtoon)
}
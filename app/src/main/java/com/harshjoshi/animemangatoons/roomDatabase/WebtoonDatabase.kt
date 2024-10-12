package com.harshjoshi.animemangatoons.roomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Webtoon::class], version = 1, exportSchema = false)
abstract class WebtoonDatabase: RoomDatabase() {
    abstract fun webtoonDao(): WebtoonDao
}

//Usage:
//database = Room.databaseBuilder(context, WebtoonDatabase::class.java, "saved-webtoons").build()
//val myDao = db.userDao()
//val list: List<Webtoons> = userDao.getWebtoons()
package edu.skku.cs.translatorclone.room

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.skku.cs.translatorclone.room.data.Translate


@Database(entities = [Translate::class], version = 1)
abstract class RoomDB : RoomDatabase(){

    abstract val dao : RoomDao
}
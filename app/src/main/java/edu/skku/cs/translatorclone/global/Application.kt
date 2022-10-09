package edu.skku.cs.translatorclone.global

import androidx.room.Room
import dagger.hilt.android.HiltAndroidApp
import edu.skku.cs.translatorclone.room.RoomDB
import edu.skku.cs.translatorclone.room.RoomDao
import edu.skku.cs.translatorclone.viewmodel.TranslatorViewModel

@HiltAndroidApp
class Application : android.app.Application() {

    override fun onCreate() {
        super.onCreate()


    }
}
package edu.skku.cs.translatorclone.global

import androidx.room.Room
import edu.skku.cs.translatorclone.room.RoomDB
import edu.skku.cs.translatorclone.room.RoomDao
import edu.skku.cs.translatorclone.viewmodel.TranslatorViewModel
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.core.context.startKoin
import org.koin.dsl.module


class Application : android.app.Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                module {

                    viewModel {
                        TranslatorViewModel()
                    }
                }
            ,


                module{
                    single<RoomDao> {
                        Room.databaseBuilder(get(), RoomDB::class.java, "translate.db").build().dao
                    }

                }
            )

        }
    }
}
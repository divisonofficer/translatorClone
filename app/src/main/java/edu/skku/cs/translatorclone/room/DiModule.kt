package edu.skku.cs.translatorclone.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context : Context) : RoomDB{
        return Room.databaseBuilder(context,RoomDB::class.java,RoomDB.DB_NAME).build()
    }

    @Singleton
    @Provides
    fun provideDAO(db : RoomDB) : RoomDao{
        return db.dao
    }


}
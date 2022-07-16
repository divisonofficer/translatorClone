package edu.skku.cs.translatorclone.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.skku.cs.translatorclone.room.data.Translate


@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTranslate(data : Translate)

    @Query("SELECT * FROM TRANSLATE where IS_HISTORY is 'false'")
    suspend fun getHistoryTranslate() : MutableList<Translate>

    @Query("SELECT * FROM TRANSLATE where IS_BOOKMARKED is 'true'")
    suspend fun getBookmarkTranslate() : MutableList<Translate>



}

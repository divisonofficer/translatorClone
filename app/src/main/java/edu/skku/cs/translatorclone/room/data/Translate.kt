package edu.skku.cs.translatorclone.room.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(
    tableName = "TRANSLATE"
)
data class Translate(
    @PrimaryKey
    @ColumnInfo(name = "TEXT") val text : String,
    @ColumnInfo(name = "TRANSLATED") val translated : String,
    @ColumnInfo(name = "COUNT") val count : Int = 0,
    @ColumnInfo(name = "IS_HISTORY") val isHistory : Boolean = false,
    @ColumnInfo(name = "IS_BOOKMARKED") val isBookmarked : Boolean = false
)
package org.android.daggerhilt.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movies")
@Parcelize
data class MovieEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Long = 0L,

    @ColumnInfo(name = "trackName")
    var trackName : String,

    @ColumnInfo(name = "collectionPrice")
    var collectionPrice : String,

    @ColumnInfo(name = "genre")
    var genre : String,

    @ColumnInfo(name = "image")
    var image : String,

    @ColumnInfo(name = "description")
    var description : String,

    @ColumnInfo(name = "favorite")
    var isFavorite : Boolean = false
) :Parcelable
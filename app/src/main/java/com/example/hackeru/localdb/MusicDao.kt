package com.example.hackeru.localdb

import android.content.Context
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MusicDao {

    @Query("delete from albums WHERE id = :album")
    suspend fun deleteAlbumFavorite(album: Long)

    @Query("delete from artists WHERE id = :artist")
    suspend fun deleteArtistFavorite(artist: Long)


    companion object {

        var musicDao: MusicDao? = null

       /* fun get(context: Context): MusicDao {
            if (musicDao == null)
                musicDao = AppDatabase.get(context).dao()
            return musicDao!!
        }*/
    }
}
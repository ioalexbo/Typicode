package com.softvision.alexlepadatu.typicode.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.softvision.alexlepadatu.typicode.data.model.entity.AlbumEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg obj: AlbumEntity)

    @Query("SELECT * FROM albums ORDER BY title ASC")
    fun getAlbums(): Flow<List<AlbumEntity>>
}

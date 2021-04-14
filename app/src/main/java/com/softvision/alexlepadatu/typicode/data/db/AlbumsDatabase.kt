package com.softvision.alexlepadatu.typicode.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.softvision.alexlepadatu.typicode.data.db.dao.AlbumsDao
import com.softvision.alexlepadatu.typicode.data.model.entity.AlbumEntity

@Database(entities = [AlbumEntity::class], version = 1, exportSchema = false)
abstract class AlbumsDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumsDao
}

package com.example.myapplication.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.local.entity.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class FavoriteRoomDatabase : RoomDatabase() {
    abstract fun favDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavoriteRoomDatabase {
            if (INSTANCE == null) {
                synchronized(FavoriteRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteRoomDatabase::class.java, "fav_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE as FavoriteRoomDatabase
        }
    }

}
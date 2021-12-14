package com.dmadunts.samples.mvpsample.app

import android.app.Application
import androidx.room.Room
import com.dmadunts.samples.mvpsample.model.room.CreatureDatabase

class ApplicationLoader : Application() {
    companion object {
        lateinit var database: CreatureDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, CreatureDatabase::class.java, "creature_database").build()
    }
}
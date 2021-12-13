package com.dmadunts.samples.mvpsample.app

import android.app.Application
import com.dmadunts.samples.mvpsample.model.room.CreatureDatabase

class ApplicationLoader : Application() {
    companion object {
        lateinit var database: CreatureDatabase
    }

    override fun onCreate() {
        super.onCreate()
        // TODO: init database
    }
}
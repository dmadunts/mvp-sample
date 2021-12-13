package com.dmadunts.samples.mvpsample.model.room
import android.os.AsyncTask
import com.dmadunts.samples.mvpsample.app.ApplicationLoader
import com.dmadunts.samples.mvpsample.model.Creature
import com.dmadunts.samples.mvpsample.model.CreatureRepository
import com.dmadunts.samples.mvpsample.model.room.CreatureDao

class RoomRepository : CreatureRepository {
    private val creatureDao: CreatureDao = ApplicationLoader.database.creatureDao()

    private class InsertAsyncTask internal constructor(private val dao: CreatureDao) :
        AsyncTask<Creature, Void, Void>() {
        override fun doInBackground(vararg params: Creature): Void? {
            return null
        }
    }

    private class DeleteAsyncTask internal constructor(private val dao: CreatureDao) :
        AsyncTask<Creature, Void, Void>() {
        override fun doInBackground(vararg params: Creature): Void? {
            return null
        }
    }
}
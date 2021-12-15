package com.dmadunts.samples.mvpsample.model.room

import com.dmadunts.samples.mvpsample.app.ApplicationLoader
import com.dmadunts.samples.mvpsample.model.Creature
import com.dmadunts.samples.mvpsample.model.CreatureRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RoomRepository : CreatureRepository {
    private val creatureDao: CreatureDao by lazy { ApplicationLoader.database.creatureDao() }
    private val allCreatures: Flow<List<Creature>> by lazy { creatureDao.getAllCreatures() }

    override suspend fun saveCreature(creature: Creature) {
        withContext(Dispatchers.IO) {
            creatureDao.insert(creature)
        }
    }

    override suspend fun getAllCreatures(): Flow<List<Creature>> {
        return withContext(Dispatchers.IO) {
            allCreatures
        }
    }

    override suspend fun clearAllCreatures() {
        withContext(Dispatchers.IO) {
            creatureDao.clearCreatures()
        }
    }
}
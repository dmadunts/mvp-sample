package com.dmadunts.samples.mvpsample.model.room

import com.dmadunts.samples.mvpsample.app.ApplicationLoader
import com.dmadunts.samples.mvpsample.model.Creature
import com.dmadunts.samples.mvpsample.model.CreatureRepository
import kotlinx.coroutines.flow.Flow

class RoomRepository : CreatureRepository {
    private val creatureDao: CreatureDao by lazy { ApplicationLoader.database.creatureDao() }
    private val allCreatures: Flow<List<Creature>> by lazy { creatureDao.getAllCreatures() }

    override suspend fun saveCreature(creature: Creature) {
        creatureDao.insert(creature)
    }

    override suspend fun getAllCreatures(): Flow<List<Creature>> {
        return allCreatures
    }

    override suspend fun clearAllCreatures() {
        creatureDao.clearCreatures()
    }
}
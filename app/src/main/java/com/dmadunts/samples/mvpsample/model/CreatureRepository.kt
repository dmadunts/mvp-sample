package com.dmadunts.samples.mvpsample.model

import kotlinx.coroutines.flow.Flow

interface CreatureRepository {
    suspend fun saveCreature(creature: Creature)
    suspend fun getAllCreatures(): Flow<List<Creature>>
    suspend fun clearAllCreatures()
}
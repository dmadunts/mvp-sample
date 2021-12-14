package com.dmadunts.samples.mvpsample.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dmadunts.samples.mvpsample.model.Creature
import kotlinx.coroutines.flow.Flow

@Dao
interface CreatureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(creature: Creature)

    @Query("DELETE FROM creature_table")
    fun clearCreatures()

    @Query("SELECT * FROM creature_table ORDER BY name ASC")
    fun getAllCreatures(): Flow<List<Creature>>
}
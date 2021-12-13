package com.dmadunts.samples.mvpsample.model.room
import androidx.room.RoomDatabase

abstract class CreatureDatabase : RoomDatabase() {
  abstract fun creatureDao(): CreatureDao
}
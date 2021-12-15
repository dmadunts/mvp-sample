package com.dmadunts.samples.mvpsample.presenter

import com.dmadunts.samples.mvpsample.model.Creature
import kotlinx.coroutines.flow.Flow

interface AllCreaturesContract {
    interface Presenter {
        suspend fun getAllCreatures(): Flow<List<Creature>>
        suspend fun clearAllCreatures()
    }

    interface View {
        fun showCreaturesCleared()
    }
}

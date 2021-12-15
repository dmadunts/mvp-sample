package com.dmadunts.samples.mvpsample.presenter

import com.dmadunts.samples.mvpsample.model.Creature
import com.dmadunts.samples.mvpsample.model.CreatureRepository
import com.dmadunts.samples.mvpsample.model.room.RoomRepository
import kotlinx.coroutines.flow.Flow

class AllCreaturesPresenter(private val repository: CreatureRepository = RoomRepository()) :
    BasePresenter<AllCreaturesContract.View>(),
    AllCreaturesContract.Presenter {
    override suspend fun getAllCreatures(): Flow<List<Creature>> {
        return repository.getAllCreatures()
    }

    override suspend fun clearAllCreatures() {
        repository.clearAllCreatures()
        getView()?.showCreaturesCleared()
    }

}

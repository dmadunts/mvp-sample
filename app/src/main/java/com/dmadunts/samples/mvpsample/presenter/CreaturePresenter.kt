package com.dmadunts.samples.mvpsample.presenter

import com.dmadunts.samples.mvpsample.model.*
import com.dmadunts.samples.mvpsample.model.room.RoomRepository

class CreaturePresenter(
    private val creatureGenerator: CreatureGenerator = CreatureGenerator(),
    private val repository: CreatureRepository = RoomRepository()
) :
    BasePresenter<CreatureContract.View>(), CreatureContract.Presenter {
    private lateinit var creature: Creature
    private var name = ""
    private var intelligence = 0
    private var strength = 0
    private var endurance = 0
    private var drawable = 0

    private fun updateCreature() {
        val attributes = CreatureAttributes(intelligence, strength, endurance)
        creature = creatureGenerator.generateCreature(attributes, name, drawable)
        getView()?.showHitPoints(creature.hitPoints.toString())
    }

    override fun updateName(name: String) {
        this.name = name
        updateCreature()
    }

    override fun attributeSelected(attributeType: AttributeType, position: Int) {
        when (attributeType) {
            AttributeType.INTELLIGENCE -> intelligence = AttributeStore.INTELLIGENCE[position].value
            AttributeType.STRENGTH -> strength = AttributeStore.STRENGTH[position].value
            AttributeType.ENDURANCE -> endurance = AttributeStore.ENDURANCE[position].value
        }
        updateCreature()
    }

    override fun drawableSelected(drawable: Int) {
        this.drawable = drawable
        getView()?.showAvatarDrawable(drawable)
        updateCreature()
    }

    override fun isDrawableSelected(): Boolean {
        return drawable != 0
    }

    private fun canSaveCreature(): Boolean {
        return intelligence != 0 && strength != 0 && endurance != 0 && name.isNotEmpty() && drawable != 0
    }

    override suspend fun saveCreature() {
        if (canSaveCreature()) {
            repository.saveCreature(creature)
            getView()?.showCreatureSaved()
        } else {
            getView()?.showCreatureSavedError()
        }
    }
}
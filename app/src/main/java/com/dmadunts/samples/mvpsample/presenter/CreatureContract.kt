package com.dmadunts.samples.mvpsample.presenter

import androidx.annotation.DrawableRes
import com.dmadunts.samples.mvpsample.model.AttributeType

interface CreatureContract {
    interface Presenter {
        fun updateName(name: String)
        fun attributeSelected(attributeType: AttributeType, position: Int)
        fun drawableSelected(drawable: Int)
        fun isDrawableSelected(): Boolean
        suspend fun saveCreature()
    }

    interface View {
        fun showHitPoints(hitPoints: String)
        fun showAvatarDrawable(@DrawableRes resourceId: Int)
        fun showCreatureSaved()
        fun showCreatureSavedError()
    }
}
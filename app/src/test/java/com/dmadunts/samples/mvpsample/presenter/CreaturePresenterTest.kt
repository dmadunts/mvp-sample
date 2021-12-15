package com.dmadunts.samples.mvpsample.presenter

import com.dmadunts.samples.mvpsample.model.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CreaturePresenterTest {
    private lateinit var presenter: CreaturePresenter

    @Mock
    lateinit var view: CreatureContract.View

    @Mock
    lateinit var mockGenerator: CreatureGenerator

    @Mock
    lateinit var mockRepository: CreatureRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = CreaturePresenter(mockGenerator, mockRepository)
        presenter.setView(view)
    }

    @Test
    fun testIntelligenceSelected() {
        val attributes = CreatureAttributes(10, 0, 0)
        val stubCreature = Creature(attributes, 50)
        Mockito.`when`(mockGenerator.generateCreature(attributes)).thenReturn(stubCreature)

        presenter.attributeSelected(AttributeType.INTELLIGENCE, 3)

        Mockito.verify(view, Mockito.times(1)).showHitPoints("50")
    }

    @Test
    fun testStrengthSelected() {
        val attributes = CreatureAttributes(0, 10, 0)
        val stubCreature = Creature(attributes, 30)
        Mockito.`when`(mockGenerator.generateCreature(attributes)).thenReturn(stubCreature)

        presenter.attributeSelected(AttributeType.STRENGTH, 3)

        Mockito.verify(view, Mockito.times(1)).showHitPoints("30")
    }

    @Test
    fun testEnduranceSelected() {
        val attributes = CreatureAttributes(0, 0, 10)
        val stubCreature = Creature(attributes, 40)
        Mockito.`when`(mockGenerator.generateCreature(attributes)).thenReturn(stubCreature)

        presenter.attributeSelected(AttributeType.ENDURANCE, 3)

        Mockito.verify(view, Mockito.times(1)).showHitPoints("40")
    }
}
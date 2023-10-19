package com.example.plantcare.data.model

import com.example.plantcare.fakePlantsList
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PlantsTest {

    private lateinit var plant: Plants

    private val plantsList = fakePlantsList()

    @Before
    fun setUp() {
        plant = plantsList[0]
    }

    @Test
    fun test_default_values() {
        val defaultPlant = plantsList[1]
        Assert.assertEquals(2, defaultPlant.plantsId)
        Assert.assertEquals("summer", defaultPlant.currentSeason)
    }

//    @Test
//    fun test_shouldBeWatered() {
//        Calendar.getInstance().let { now ->
//            // Generate lastWateringDate from being as copy of now.
//            val lastWateringDate = Calendar.getInstance()
//
//            // Test for lastWateringDate is today.
//            lastWateringDate.time = now.time
//            Assert.assertFalse(
//                plant.shouldBeWatered(
//                    now,
//                    lastWateringDate.apply { add(Calendar.DAY_OF_YEAR, -0) })
//            )
//
//            // Test for lastWateringDate is yesterday.
//            lastWateringDate.time = now.time
//            Assert.assertFalse(
//                plant.shouldBeWatered(
//                    now,
//                    lastWateringDate.apply { add(Calendar.DAY_OF_YEAR, -1) })
//            )
//
//            // Test for lastWateringDate is the day before yesterday.
//            lastWateringDate.time = now.time
//            Assert.assertFalse(
//                plant.shouldBeWatered(
//                    now,
//                    lastWateringDate.apply { add(Calendar.DAY_OF_YEAR, -2) })
//            )
//
//            // Test for lastWateringDate is some days ago, three days ago, four days ago etc.
//            lastWateringDate.time = now.time
//            Assert.assertTrue(
//                plant.shouldBeWatered(
//                    now,
//                    lastWateringDate.apply { add(Calendar.DAY_OF_YEAR, -3) })
//            )
//        }
//    }

    @Test
    fun test_toString() {
        Assert.assertEquals("cactus", plant.type.toString())
    }
}
package com.example.plantcare.ui.utils

enum class TypesOfPlants {
    Flower,
    Cactus,
    Succulent,
    Carnivorous,
    Orchid,
    Fern,
    Tree,
    Bulbs,
    Climber,
    Shrub,
    Herbs,
    Vegetable,
    Fruit
}
fun getTypesOfPlantsList(): List<TypesOfPlants> {
    val typesList = mutableListOf<TypesOfPlants>()
    TypesOfPlants.entries.forEach{
        typesList.add(it)
    }
    return typesList
}
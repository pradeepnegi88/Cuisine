package com.example.cuisine.domain

interface CuisineUseCase {
    suspend fun getCuisine(query:String): List<Cuisine>
}
package com.example.cuisine.domain

interface CuisineRepository {
    suspend fun getCuisine(query:String): List<Cuisine>
}
package com.example.cuisine.domain

class CuisineUseCaseImpl(private val cuisineRepository: CuisineRepository) : CuisineUseCase {
    override suspend fun getCuisine(query: String) = cuisineRepository.getCuisine(query)
}





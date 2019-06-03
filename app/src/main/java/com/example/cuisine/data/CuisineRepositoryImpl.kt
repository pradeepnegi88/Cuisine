package com.example.cuisine.data

import com.contentful.java.cda.CDAAsset
import com.contentful.java.cda.CDAClient
import com.contentful.java.cda.CDAEntry
import com.contentful.java.cda.LocalizedResource
import com.example.cuisine.domain.Cuisine
import com.example.cuisine.domain.CuisineRepository
import com.example.cuisine.presenter.util.Constants

class CuisineRepositoryImpl(private val cdaClient: CDAClient, val query: String) :
    CuisineRepository {
    override suspend fun getCuisine(query: String): List<Cuisine> {
        val globalList = ArrayList<Cuisine>()
        cdaClient.fetch(CDAEntry::class.java)
            .withContentType(query /*"RECIPE"*/)
            .all()?.items()?.map { it as LocalizedResource }?.forEach {
                globalList.add(
                    Cuisine(
                        title = it.getField<String>(Constants.TITILE),
                        photo = "https://" + (it.getField<Any>(Constants.PHOTO) as CDAAsset).url(),
                        description = it.getField<String>(Constants.DESCRIPTION) ?: "",
                        calorie = it.getField<Double>(Constants.CALORIES) ?: 0.0,
                        chef = if (it.rawFields().containsKey(Constants.CHEF)) {
                            (it.getField<Any>(Constants.CHEF) as CDAEntry)?.getField<String>(Constants.NAME)
                        } else {
                            null
                        },
                        tags = if (it.rawFields().containsKey(Constants.TAGS)) {
                            tagsExtraction((it.getField<Any>(Constants.TAGS) as ArrayList<CDAEntry>))
                        } else {
                            null
                        }

                    )
                )

            }
        return globalList
    }

    private fun tagsExtraction(arrayList: ArrayList<CDAEntry>): List<String>? {
        var listTags = mutableListOf<String>()
        arrayList.forEach {
            listTags.add(it.getField<String>(Constants.NAME))
        }
        return listTags
    }

}
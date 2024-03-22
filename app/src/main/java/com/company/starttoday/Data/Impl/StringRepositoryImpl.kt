package com.company.starttoday.Data.Impl

import com.company.starttoday.Core.Network.API.StringAPI
import com.company.starttoday.Data.ThingOnData.ThingOn
import com.company.starttoday.Data.ThingOnData.ThingOnDao
import com.company.starttoday.Data.stringEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class StringRepositoryImpl @Inject constructor(
    private val stringAPI: StringAPI,
    private val dao: ThingOnDao
) {

    private val _strings = MutableStateFlow<List<stringEntity>>(emptyList())

    val strings: StateFlow<List<stringEntity>>
        get() = _strings


    private val _categories = MutableStateFlow<List<String>>(emptyList())

    val categories: StateFlow<List<String>>
        get() = _categories

    suspend fun getTweets(category: String) {
        val result = stringAPI.getTweets("HiltStudy[?(@.category==\"$category\")]")
        if (result.isSuccessful && result.body() != null) {
            _strings.emit(result.body()!!)
        }
    }

    suspend fun getCategories() {
        val result = stringAPI.getCategories()
        val resultBody = result.body() ?: emptyList()

        if (result.isSuccessful && result.body() != null) {
//            _categories.emit(result.body()!!)

            withContext(Dispatchers.IO) {
                resultBody.forEach {
                    // parameter로 Thin
                    dao.upsertStrings(ThingOn(thingOn = it))

                }
            }

        }

    }

    suspend fun updateUi() {
        dao.getAll().collect {
            _categories.value = it.map {
                it.thingOn
            }
        }
    }

    fun getThingOn() {
        dao.getAllList()
    }

}
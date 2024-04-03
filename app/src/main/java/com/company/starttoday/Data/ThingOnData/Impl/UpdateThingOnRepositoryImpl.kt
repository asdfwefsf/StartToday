package com.company.starttoday.Data.ThingOnData.Impl

import com.company.starttoday.Data.ThingOnData.Room.ThingOnDao
import com.company.starttoday.Domain.ThingOn.Repository.UpdateThingOnRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class UpdateThingOnRepositoryImpl @Inject constructor(
    private val dao: ThingOnDao
) : UpdateThingOnRepository {

//    private val _categories = MutableStateFlow<List<String>>(emptyList())
//
//    val categories: StateFlow<List<String>>
//        get() = _categories

//    override suspend fun updateString(): Flow<List<String>> {
//        dao.getAll().collect {
//            _categories.value = it.map {
//                it.thingOn
//            }
//        }
//    }

    override suspend fun updateThingOn(): Flow<List<String>> {
        return dao.getAll().map { entities ->
            entities.map { it.thingOn }
        }
    }

}
package com.company.starttoday.Data.ThingOnData.Impl

import com.company.starttoday.Data.ThingOnData.Room.ThingOnDao
import com.company.starttoday.Domain.ThingOn.Repository.UpdateThingOnRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


class UpdateThingOnRepositoryImpl @Inject constructor(
    private val dao: ThingOnDao
) : UpdateThingOnRepository {

    private val _categories = MutableStateFlow<List<String>>(emptyList())

    val categories: StateFlow<List<String>>
        get() = _categories

    override suspend fun updateString() {
        dao.getAll().collect {
            _categories.value = it.map {
                it.thingOn
            }
        }
    }

}
package com.company.starttoday.data.ThingOnData.Impl

import com.company.starttoday.Core.Network.API.ThingOnAPI
import com.company.starttoday.data.ThingOnData.Room.ThingOn
import com.company.starttoday.data.ThingOnData.Room.ThingOnDao
import com.company.starttoday.Domain.ThingOn.Repository.GetThingOnRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetThingOnRepositoryImpl @Inject constructor(
    private val stringAPI: ThingOnAPI,
    private val dao: ThingOnDao
) : GetThingOnRepository {
    override suspend fun getString() {
        val result = stringAPI.getCategories()
        val resultBody = result.body() ?: emptyList()

        if (result.isSuccessful && result.body() != null) {
            withContext(Dispatchers.IO) {
                resultBody.forEach {
                    dao.upsertStrings(ThingOn(thingOn = it))
                }
            }

        }
    }
}
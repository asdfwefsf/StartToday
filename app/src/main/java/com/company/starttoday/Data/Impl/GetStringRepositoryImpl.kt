package com.company.starttoday.Data.Impl

import com.company.starttoday.Core.Network.API.StringAPI
import com.company.starttoday.Data.ThingOnData.Room.ThingOn
import com.company.starttoday.Data.ThingOnData.Room.ThingOnDao
import com.company.starttoday.Repository.GetStringRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetStringRepositoryImpl @Inject constructor(
    private val stringAPI: StringAPI,
    private val dao: ThingOnDao
) : GetStringRepository {
    override suspend fun getString() {
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
}
package com.company.starttoday.data.ThingOnData.Impl

import com.company.starttoday.data.ThingOnData.Room.ThingOnDao
import com.company.starttoday.Domain.ThingOn.Repository.UpdateThingOnRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class UpdateThingOnRepositoryImpl @Inject constructor(
    private val dao: ThingOnDao
) : UpdateThingOnRepository {
    override suspend fun updateThingOn(): Flow<List<String>> {
        return dao.getAll().map { entities ->
            // List<String> 순회하면서 각각의 thingOn만 리스팅한 Flow 반환.
            // Flow로 반환하니까 실시간 업데이트 가능.
            entities.map {
                it.thingOn
            }
        }
    }

}
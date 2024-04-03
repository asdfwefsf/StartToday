package com.company.starttoday.Domain.ThingOn.UseCases

import com.company.starttoday.Domain.ThingOn.Repository.UpdateThingOnRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateThingOnUseCase @Inject constructor (
    private val updateThingOnRepository : UpdateThingOnRepository
) {
    suspend operator fun invoke(): Flow<List<String>> {
        return updateThingOnRepository.updateThingOn()
    }
//    suspend fun updateString() {
//        stringRepository.updateString()
//    }



}
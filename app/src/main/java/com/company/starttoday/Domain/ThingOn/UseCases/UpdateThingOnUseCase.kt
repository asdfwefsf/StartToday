package com.company.starttoday.Domain.ThingOn.UseCases

import com.company.starttoday.Domain.ThingOn.Repository.UpdateThingOnRepository
import javax.inject.Inject

class UpdateThingOnUseCase @Inject constructor (
    private val stringRepository : UpdateThingOnRepository
) {

    suspend fun updateString() {
        stringRepository.updateString()
    }


}
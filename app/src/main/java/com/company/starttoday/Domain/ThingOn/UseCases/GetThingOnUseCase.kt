package com.company.starttoday.Domain.ThingOn.UseCases

import com.company.starttoday.Repository.GetThingOnRepository
import javax.inject.Inject

class GetThingOnUseCase @Inject constructor(
    private val getStringRepository : GetThingOnRepository
) {
    suspend fun getString() {
        getStringRepository.getString()
    }
}

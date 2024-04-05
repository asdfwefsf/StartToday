package com.company.starttoday.Domain.ThingOn.UseCases

import com.company.starttoday.Domain.ThingOn.Repository.GetThingOnRepository
import javax.inject.Inject

class GetThingOnUseCase @Inject constructor(
    private val getStringRepository : GetThingOnRepository
) {
    suspend operator fun invoke() {
        getStringRepository.getString()
    }
}

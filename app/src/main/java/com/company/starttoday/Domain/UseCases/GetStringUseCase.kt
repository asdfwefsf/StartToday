package com.company.starttoday.Domain.UseCases

import com.company.starttoday.Repository.GetStringRepository
import javax.inject.Inject

class GetStringUseCase @Inject constructor(
    private val getStringRepository : GetStringRepository
) {
    suspend fun getString() {
        getStringRepository.getString()
    }
}

package com.company.starttoday.Domain.UseCases

import com.company.starttoday.Repository.UpdateStringRepository
import javax.inject.Inject

class UpdateStringUseCase @Inject constructor (
    private val stringRepository : UpdateStringRepository
) {

    suspend fun updateString() {
        stringRepository.updateString()
    }


}
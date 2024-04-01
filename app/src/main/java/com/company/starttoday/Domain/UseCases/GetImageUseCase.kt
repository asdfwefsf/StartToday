package com.company.starttoday.Domain.UseCases

import com.company.starttoday.Repository.GetImageRepository
import javax.inject.Inject

class GetImageUseCase @Inject constructor(
    private val repository: GetImageRepository
) {
    suspend fun getImage() {
        repository.getImage()
    }
}
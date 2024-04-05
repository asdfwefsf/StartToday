package com.company.starttoday.Domain.Image.UseCases

import com.company.starttoday.Domain.Image.Repository.GetImageRepository
import javax.inject.Inject

class GetImageUseCase @Inject constructor(
    private val repository: GetImageRepository
) {
    suspend operator fun invoke() {
        repository.getImage()
    }
}
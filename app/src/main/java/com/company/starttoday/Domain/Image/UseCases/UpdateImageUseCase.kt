package com.company.starttoday.Domain.Image.UseCases

import com.company.starttoday.Domain.Image.Repository.UpdateImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateImageUseCase @Inject constructor(
    private val updateImageRepository: UpdateImageRepository
) {
    suspend operator fun invoke() : Flow<List<String>> {
        return updateImageRepository.updateImage()
    }
}
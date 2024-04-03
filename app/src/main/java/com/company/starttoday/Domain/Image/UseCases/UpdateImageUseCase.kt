package com.company.starttoday.Domain.Image.UseCases

import com.company.starttoday.Domain.Image.Repository.UpdateImageRepository
import javax.inject.Inject

class UpdateImageUseCase @Inject constructor(
    private val updateImageRepository: UpdateImageRepository
) {

}
package com.company.starttoday.Domain.Image.Repository

import kotlinx.coroutines.flow.Flow

interface UpdateImageRepository {
    suspend fun updateImage() : Flow<List<String>>
}
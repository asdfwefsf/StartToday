package com.company.starttoday.Domain.ThingOn.Repository

import kotlinx.coroutines.flow.Flow

interface UpdateThingOnRepository {
    suspend fun updateThingOn() : Flow<List<String>>
}
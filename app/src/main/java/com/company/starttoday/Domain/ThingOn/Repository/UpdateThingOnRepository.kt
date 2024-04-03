package com.company.starttoday.Domain.ThingOn.Repository

import kotlinx.coroutines.flow.Flow

interface UpdateThingOnRepository {
    suspend fun updateString() : Flow<List<String>>
}
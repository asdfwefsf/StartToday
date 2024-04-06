package com.company.starttoday.data.ImageLinkData.Impl

import com.company.starttoday.Core.Network.API.ImageLinkAPI
import com.company.starttoday.data.ImageLinkData.Room.ImageLink
import com.company.starttoday.data.ImageLinkData.Room.ImageLinkDao
import com.company.starttoday.Domain.Image.Repository.GetImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetImageRepositoryImpl @Inject constructor(
    private val imageLinkAPI : ImageLinkAPI,
    private val dao : ImageLinkDao
) : GetImageRepository {
    override suspend fun getImage() {
        val result = imageLinkAPI.getImages()
        val resultBody = result.body() ?: emptyList()
        if (result.isSuccessful && result.body() != null) {
            withContext(Dispatchers.IO) {
                resultBody.forEach {
                    dao.upsertImageLink(
                        ImageLink(imageLink = it)
                    )
                }
            }
        }
    }

}
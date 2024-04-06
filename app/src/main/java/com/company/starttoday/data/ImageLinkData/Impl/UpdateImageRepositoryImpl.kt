package com.company.starttoday.data.ImageLinkData.Impl

import com.company.starttoday.Core.Network.API.ImageLinkAPI
import com.company.starttoday.data.ImageLinkData.Room.ImageLinkDao
import com.company.starttoday.Domain.Image.Repository.UpdateImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UpdateImageRepositoryImpl @Inject constructor(
    private val imageLinkAPI: ImageLinkAPI,
    private val dao : ImageLinkDao
) : UpdateImageRepository{
    override suspend fun updateImage() : Flow<List<String>> {
        return dao.getImageLink().map{ ImageLinks ->
            // List<String>을 순회하면서 각각의 imageLink만 리스팅한 Flow 반환
            ImageLinks.map{
                it.imageLink
            }
        }

    }
}
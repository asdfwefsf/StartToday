package com.company.starttoday.Data.Impl

import com.company.starttoday.Core.Network.API.ImageLinkAPI
import com.company.starttoday.Data.ImageLinkData.ImageLink
import com.company.starttoday.Data.ImageLinkData.ImageLinkDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageLinkImpl @Inject constructor(
    private val imageLinkAPI : ImageLinkAPI,
    private val dao : ImageLinkDao
) {

    private val _imageLinks = MutableStateFlow<List<String>>(emptyList())

    val imageLinks: StateFlow<List<String>>
        get() = _imageLinks

    suspend fun getImageLink() {
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

    suspend fun updateUi() {
        dao.getImageLink().collect {
            _imageLinks.value = it.map {
                it.imageLink
            }
        }
    }


}
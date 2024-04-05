package com.company.starttoday.Presentation.Image

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.starttoday.Data.ImageLinkData.Room.ImageLinkDatabase
import com.company.starttoday.Data.Impl.ImageLinkImpl
import com.company.starttoday.Domain.Image.UseCases.UpdateImageUseCase
import com.company.starttoday.Presentation.ViewModel.ImageCounter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageLinkViewModel @Inject constructor(
    private val repository : ImageLinkImpl,
    private val database : ImageLinkDatabase,
    private val updateImageUseCase: UpdateImageUseCase,
    private val counter : ImageCounter,

    ) : ViewModel() {

    private val _imageLinks = MutableStateFlow<List<String>>(emptyList())
    val imageLinks : StateFlow<List<String>> = _imageLinks.asStateFlow()
    private suspend fun updateImage() = viewModelScope.launch {
        updateImageUseCase().collect { imageList ->
            _imageLinks.value = imageList
        }
    }


    val page = counter.count
    fun save(pageNum : Int) {
        counter.save(pageNum)
    }


    suspend fun updateImageLink() {
        database.dao.getImageLink().collect {
            _imageLinks.value = it.map {
                it.imageLink
            }
        }


    }

    init {
        viewModelScope.launch {
            updateImage()
        }
    }
}
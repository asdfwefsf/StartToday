package com.company.starttoday.Presentation.Image

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.starttoday.Data.ImageLinkData.Room.ImageLinkDatabase
import com.company.starttoday.Data.Impl.ImageLinkImpl
import com.company.starttoday.Domain.Image.UseCases.UpdateImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageLinkViewModel @Inject constructor(
    private val repository : ImageLinkImpl,
    private val database : ImageLinkDatabase,
    private val updateImageUseCase: UpdateImageUseCase

) : ViewModel() {

    val imageLinkFlow = database.dao.getImageLink()

    private val _imageLinks = MutableStateFlow<List<String>>(emptyList())

    val imageLinks : StateFlow<List<String>> = repository.imageLinks




    suspend fun updateImageLink() {
        database.dao.getImageLink().collect {
            _imageLinks.value = it.map {
                it.imageLink
            }
        }


    }

    init {
        viewModelScope.launch {

//            repository.getImageLink()

            repository.updateUi()




        }
    }
}
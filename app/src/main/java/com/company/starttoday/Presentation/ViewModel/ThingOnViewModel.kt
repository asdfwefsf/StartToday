package com.company.starttoday.Presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.starttoday.Domain.ThingOn.UseCases.UpdateThingOnUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThingOnViewModel @Inject constructor(
    private val updateThingOnUseCase : UpdateThingOnUseCase,
//    private val repository: UpdateThingOnRepositoryImpl,
//    private val counter : ImageCounter,
) : ViewModel() {

//    val categories: StateFlow<List<String>> = repository.categories

    // ThingOnUpdate
    private val _thingOn = MutableStateFlow<List<String>>(emptyList())
    val thingOn: StateFlow<List<String>> = _thingOn.asStateFlow()
    private suspend fun updateThingOn() = viewModelScope.launch {
        updateThingOnUseCase().collect { categoriesList ->
            _thingOn.value = categoriesList
        }
    }

    // 이미지 관련 카운터 : ThingOnViewModel -> ImageLinkViewModel 이동
//    val page = counter.count
//    fun save(pageNum : Int) {
//        counter.save(pageNum)
//    }

    init {
        viewModelScope.launch {
            updateThingOn()
        }
    }



}


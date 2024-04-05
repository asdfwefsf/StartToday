package com.company.starttoday.Presentation.ThingOn_Image.ViewModel

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
) : ViewModel() {

    // ThingOnUpdate
    private val _thingOn = MutableStateFlow<List<String>>(emptyList())
    val thingOn: StateFlow<List<String>> = _thingOn.asStateFlow()
    private suspend fun updateThingOn() = viewModelScope.launch {
        updateThingOnUseCase().collect { thingOnList ->
            _thingOn.value = thingOnList
        }
    }

    // 이미지 관련 카운터 : ThingOnViewModel -> ImageLinkViewModel 이동
    init {
        viewModelScope.launch {
            updateThingOn()
        }
    }
}


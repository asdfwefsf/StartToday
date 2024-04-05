package com.company.starttoday.Presentation.ThingOn_Image.ViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageCounter @Inject constructor() {
    private val _count = MutableStateFlow(0)
    val count = _count.asStateFlow()

    fun save(pageNum : Int) {
        _count.value = pageNum
    }

}

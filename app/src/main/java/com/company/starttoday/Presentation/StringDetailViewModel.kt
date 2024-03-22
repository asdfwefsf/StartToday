package com.company.starttoday.Presentation

import androidx.lifecycle.ViewModel
import com.company.starttoday.Data.Impl.StringRepositoryImpl
import javax.inject.Inject

class StringDetailViewModel  @Inject constructor(
    private val repository: StringRepositoryImpl
) : ViewModel() {
}
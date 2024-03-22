package com.company.starttoday.Presentation.ViewModel

import androidx.lifecycle.ViewModel
import com.company.starttoday.Data.Impl.StringRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class forecastViewModel  @Inject constructor(
    private val repository: StringRepositoryImpl
) : ViewModel() {




}
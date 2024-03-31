package com.company.starttoday.Presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.starttoday.Data.Impl.StringRepositoryImpl
import com.company.starttoday.Data.ThingOnData.ThingOnDatabase
import com.company.starttoday.Domain.UseCases.UpdateStringUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StringAllViewModel @Inject constructor(
    private val useCase: UpdateStringUseCase,
    private val repository: StringRepositoryImpl,
    private val counter : ImageCounter,
    private val database: ThingOnDatabase

) : ViewModel() {

    val categories: StateFlow<List<String>> = repository.categories

    val page = counter.count

    fun save(pageNum : Int) {
        counter.save(pageNum)
    }


    val thionOnFlow = database.dao.getAll()

    fun updateUI() {
        database.dao.getAll()
    }
    init {
        viewModelScope.launch {


//            repository.updateUi()

            useCase.updateString()
        }
    }






}


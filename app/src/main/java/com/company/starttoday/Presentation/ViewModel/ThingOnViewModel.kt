package com.company.starttoday.Presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.starttoday.Core.WorkManager.APICoroutineWorker
import com.company.starttoday.Data.ThingOnData.Impl.UpdateThingOnRepositoryImpl
import com.company.starttoday.Data.ThingOnData.Room.ThingOnDatabase
import com.company.starttoday.Domain.ThingOn.UseCases.UpdateThingOnUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThingOnViewModel @Inject constructor(
    private val useCase: UpdateThingOnUseCase,
    private val repository: UpdateThingOnRepositoryImpl,
    private val counter : ImageCounter,
    private val database: ThingOnDatabase,
    private val apiCoroutineWorker : APICoroutineWorker

) : ViewModel() {

    val categories: StateFlow<List<String>> = repository.categories

    val page = counter.count

    fun save(pageNum : Int) {
        counter.save(pageNum)
    }


//    val thionOnFlow = database.dao.getAll()
//
//    fun updateUI() {
//        database.dao.getAll()
//    }
    init {
        viewModelScope.launch {

            useCase.updateString()
        }
    }






}


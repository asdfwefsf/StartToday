package com.company.starttoday.Presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.starttoday.Data.RoutineData.Routine
import com.company.starttoday.Data.RoutineData.RoutineDao
import com.company.starttoday.Data.RoutineData.RoutineState
import com.company.starttoday.Data.RoutineData.RoutineType
import com.company.starttoday.Domain.Routine.RoutineEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutineViewModel @Inject constructor(
    private val dao : RoutineDao
) : ViewModel() {

    private val _sortType = MutableStateFlow(RoutineType.TODAY)

    private val _contacts = _sortType

        .flatMapLatest { sortType ->
            when(sortType) {
                RoutineType.TODAY -> dao.getRotuineTimeToday("일간")
                RoutineType.WEEK -> dao.getRotuineTimeToday("주간")
                RoutineType.MONTH -> dao.getRotuineTimeToday("월간")
                RoutineType.YEAR -> dao.getRotuineTimeToday("연간")
                else -> throw IllegalArgumentException("Unknown SortType")

            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(RoutineState())


    val state = combine(_state, _sortType, _contacts) { state, sortType, contacts ->
        state.copy(
            routines = contacts,
            sortType = sortType.typeName
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), RoutineState())


    fun onEvent(event: RoutineEvent) {
        when(event) {
            is RoutineEvent.DeleteRoutine -> {
                viewModelScope.launch {
                    dao.deleteRoutine(event.routine)
                }
            }
            RoutineEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingContact = false
                ) }
            }

            RoutineEvent.SaveRoutine -> {
                val routineTime = state.value.routineTime
                val routineName = state.value.routineName

                if(routineTime.isBlank() || routineName.isBlank()) {
                    return
                }

                val routine = Routine(
                    routineTime = routineTime,
                    routineName = routineName,
                )
                viewModelScope.launch {
                    dao.upsertRoutine(routine)
                }
                _state.update { it.copy(
                    isAddingContact = false,
                    routineTime = "",
                    routineName = "",
                ) }
            }

            is RoutineEvent.SetRoutineTime -> {
                _state.update { it.copy(
                    routineTime = event.routineTime
                ) }
            }
            is RoutineEvent.SetRoutineName -> {
                _state.update { it.copy(
                    routineName = event.routineName
                ) }
            }
//            is RoutineEvent.SetPhoneNumber -> {
//                _state.update { it.copy(
//                    phoneNumber = event.phoneNumber
//                ) }
//            }

            RoutineEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingContact = true
                ) }
            }
            is RoutineEvent.RoutineTimeType -> {
                _sortType.value = event.routineType
            }

            else -> {}
        }
    }










}

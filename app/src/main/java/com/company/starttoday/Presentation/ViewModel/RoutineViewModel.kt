package com.company.starttoday.Presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.starttoday.Domain.Routine.Entity.RoutineState
import com.company.starttoday.Domain.Routine.Model.RoutineDomain
import com.company.starttoday.Domain.Routine.Model.RoutineType
import com.company.starttoday.Domain.Routine.RoutineEvent
import com.company.starttoday.Domain.Routine.UseCases.DeleteRoutineUseCase
import com.company.starttoday.Domain.Routine.UseCases.SaveRoutineUseCase
import com.company.starttoday.Domain.Routine.UseCases.SetRoutineTimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    private val setRoutineTimeUseCase: SetRoutineTimeUseCase,
    private val saveRoutineTimeUseCase : SaveRoutineUseCase,
    private val deleteRoutineTimeUseCase: DeleteRoutineUseCase
) : ViewModel() {

    private val _routineTimeType = MutableStateFlow(RoutineType.TODAY)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _contacts = _routineTimeType

        .flatMapLatest { _routineTimeType ->
            setRoutineTimeUseCase(_routineTimeType)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(RoutineState())

    val state = combine(_state, _routineTimeType, _contacts) { state, sortType, contacts ->
        state.copy(
            routines = contacts,
            sortType = sortType.typeName
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), RoutineState())


    fun onEvent(event: RoutineEvent) {
        when(event) {
            is RoutineEvent.DeleteRoutine -> {
                viewModelScope.launch {
                    deleteRoutineTimeUseCase(event.routine)
//                    dao.deleteRoutine(event.routine)

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

                val routine = RoutineDomain(
                    routineTime = routineTime,
                    routineName = routineName,
                )
                viewModelScope.launch {
//                    dao.upsertRoutine(routine)
                    saveRoutineTimeUseCase(routine)
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

            RoutineEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingContact = true
                ) }
            }
            is RoutineEvent.RoutineTimeType -> {
                _routineTimeType.value = event.routineType
            }

            else -> {}
        }
    }
}


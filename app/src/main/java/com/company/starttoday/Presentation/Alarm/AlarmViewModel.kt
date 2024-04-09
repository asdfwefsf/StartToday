package com.company.starttoday.Presentation.Alarm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.starttoday.Domain.Alarm.Model.DomainDTO
import com.company.starttoday.Domain.Alarm.UseCases.AlarmCancelUseCase
import com.company.starttoday.Domain.Alarm.UseCases.AlarmScheduleUseCase
import com.company.starttoday.Domain.Alarm.UseCases.UpdateAlarmUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val alarmScheduleUseCase: AlarmScheduleUseCase,
    private val alarmCancelUseCase: AlarmCancelUseCase,
    private val updateAlarmUseCase: UpdateAlarmUseCase
) : ViewModel() {


    private val _alarm = MutableStateFlow(
        DomainDTO(
            time = LocalDateTime.now(),
            startH = 0,
            startM = 0,
            term = 0,
            endH = 0,
            endM = 0
        )
    )
    val alarm: StateFlow<DomainDTO> = _alarm.asStateFlow()
    private suspend fun updateAlarm() = viewModelScope.launch {
        updateAlarmUseCase().collect { alarm ->
            _alarm.value = alarm
        }
    }
    init {
        viewModelScope.launch {
            updateAlarm()
        }
    }

    fun setAlarm(list: List<Int>) {
        viewModelScope.launch {
            val alarm = DomainDTO(
                startH = list[0], startM = list[1], term = list[2],
                endH = list[3], endM = list[4] , time = LocalDateTime.now())
            alarmScheduleUseCase(alarm)
            Log.d("gonees" , LocalDateTime.now().toString())

        }
    }

    fun cancelAlarm() {
        viewModelScope.launch {
        }
    }
}


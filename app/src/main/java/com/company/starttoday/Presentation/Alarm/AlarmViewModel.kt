package com.company.starttoday.Presentation.Alarm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.starttoday.Data.AlarmData.Alarm
import com.company.starttoday.Data.AlarmData.AlarmDao
import com.company.starttoday.Presentation.ViewModel.AlarmSaver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val alarmSaver : AlarmSaver,
    private val dao : AlarmDao,
) : ViewModel() {

    private val _alarmList = MutableStateFlow<List<Int>>(emptyList())

    var alarmList = alarmSaver.alarmList

    fun setAlarm(list : List<Int>) {
        viewModelScope.launch {
            val alarm = Alarm(startH = list[0], startM = list[1], term = list[2], endH = list[3], endM = list[4])
            dao.upsertAlarm(alarm)
        }
        alarmSaver.setAlarm(list)
    }

    fun getAlarm() {
        viewModelScope.launch {
            dao.getAlarm()
        }
//        return dao.getAlarm()
    }

    val alarmsFlow = dao.getAlarm()




}


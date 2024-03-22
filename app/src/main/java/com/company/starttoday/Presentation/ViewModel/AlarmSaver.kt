package com.company.starttoday.Presentation.ViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlarmSaver @Inject constructor() {
    private val _alarmList = MutableStateFlow<List<Int>>(emptyList())

    var alarmList = _alarmList.asStateFlow()

    fun setAlarm(list : List<Int>)  {
        _alarmList.value = list
    }

}

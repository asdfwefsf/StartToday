package com.company.starttoday.Presentation.Alarm

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.company.starttoday.R

// Parameter에 넣어주기.
@Composable
fun AlarmScreen(navController: NavController) {

//    val androidAlarmScheduler : AndroidAlarmScheduler = AndroidAlarmScheduler(LocalContext.current)

    val alarmViewModel: AlarmViewModel = hiltViewModel()
//    val alarmListState = alarmViewModel.alarmList.collectAsState().value
    val alarmListState = alarmViewModel.alarmsFlow.collectAsState(
        initial = null
    ).value

    var startHour = rememberSaveable {
        ""
    }
    var startMinute = rememberSaveable {
        ""
    }
    var alarmTerm = rememberSaveable {
        ""
    }
    var endHour = rememberSaveable {
        ""
    }
    var endMinute = rememberSaveable {
        ""
    }
    ConstraintLayout(
        modifier = Modifier.fillMaxSize() // ConstraintLayout을 화면 전체로 확장
    ) {

        val (alarm, addIcon, startH, startM, alarmterm, endH, endM, real, deleteImage) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.alarm_icon),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .constrainAs(alarm) {
                    start.linkTo(parent.start, margin = 20.dp)
                    end.linkTo(parent.end, margin = 20.dp)
                    top.linkTo(parent.top, margin = 20.dp)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.add_icon),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .constrainAs(addIcon) {
                    end.linkTo(alarm.end, margin = 20.dp)
                    bottom.linkTo(alarm.bottom, margin = 20.dp)
                }
                .clickable {
                    navController.navigate("setAlarm")
                },

            )

        if (alarmListState != null) {
            startHour = alarmListState.startH.toString()
            startMinute = alarmListState.startM.toString()
            alarmTerm = alarmListState.term.toString()
            endHour = alarmListState.endH.toString()
            endMinute = alarmListState.endM.toString()
        }


        if(alarmListState != null)
        {
            Text(
                "$startHour : $startMinute 부터 ${alarmTerm}분 간격마다 $endHour : $endMinute 까지 알람이 울립니다.",
                modifier = Modifier.constrainAs(real) {
                    top.linkTo(alarm.bottom, margin = 50.dp)
                },
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Image(
            painter = painterResource(id = R.drawable.delete_icon),
            modifier = Modifier
                .clickable {
//                    androidAlarmScheduler.cancel()
                }
                .constrainAs(deleteImage) {
                    top.linkTo(alarm.bottom, margin = 70.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                }
                .size(30.dp)
                .clip(CircleShape),
            contentDescription = "alarm delete"
        )

    }


}

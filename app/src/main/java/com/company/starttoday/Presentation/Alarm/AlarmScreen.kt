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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.company.starttoday.R
import com.company.starttoday.Theme.Sizes

// Parameter에 넣어주기.
@Composable
fun AlarmScreen(navController: NavController) {

    val alarmViewModel: AlarmViewModel = hiltViewModel()
    // state 객체에서 값을 바로 사용하기 위해서 .value()호출
    val alarmState = alarmViewModel.alarm.collectAsState().value


    val startHour = alarmState.startH.toString()
    val startMinute = alarmState.startM.toString()
    val alarmTerm = alarmState.term.toString()
    val endHour = alarmState.endH.toString()
    val endMinute = alarmState.endM.toString()

    ConstraintLayout(
        modifier = Modifier.fillMaxSize() // ConstraintLayout을 화면 전체로 확장
    ) {

        val (alarm, addIcon, real, deleteImage) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.alarm_icon),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .constrainAs(alarm) {
                    start.linkTo(parent.start, margin = Sizes.xxlarge)
                    end.linkTo(parent.end, margin = Sizes.xxlarge)
                    top.linkTo(parent.top, margin = Sizes.xxlarge)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.add_icon),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .constrainAs(addIcon) {
                    end.linkTo(alarm.end, margin = Sizes.xxlarge)
                    bottom.linkTo(alarm.bottom, margin = Sizes.xxlarge)
                }
                .clickable {
                    navController.navigate("setAlarm")
                },

            )
            Text(
                "$startHour : $startMinute 부터 ${alarmTerm}분 간격마다 $endHour : $endMinute 까지 알람이 울립니다.",
                modifier = Modifier.constrainAs(real) {
                    start.linkTo(parent.start , margin = Sizes.large)
                    top.linkTo(alarm.bottom, margin = 50.dp)
                },
                color = MaterialTheme.colorScheme.onSurface
            )



        Image(
            painter = painterResource(id = R.drawable.delete_icon),
            modifier = Modifier
                .clickable {
//                    androidAlarmScheduler.cancel()
                    alarmViewModel.cancelAlarm()
                }
                .constrainAs(deleteImage) {
                    top.linkTo(alarm.bottom, margin = 70.dp)
                    end.linkTo(parent.end, margin = Sizes.medium)
                }
                .size(30.dp)
                .clip(CircleShape),
            contentDescription = "alarm delete"
        )

    }


}

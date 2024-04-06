package com.company.starttoday.Presentation.Alarm

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.company.starttoday.data.AlarmData.AndroidAlarmScheduler
import com.company.starttoday.Domain.Alarm.Entity.AlarmItem
import com.company.starttoday.R
import com.company.starttoday.Theme.Sizes
import java.time.LocalDateTime

@Composable
fun setAlarmScreen(navController: NavController) {


    TimePickerSlider(navController)


}


@Composable
fun TimePickerSlider(navController: NavController) {
    // State를 정의합니다.

    val alarmViewModel : AlarmViewModel = hiltViewModel()

    var startHours by remember { mutableStateOf(0f) }
    var startMinutes by remember { mutableStateOf(0f) }
    var term by remember { mutableStateOf(0f) }
    var endHours by remember { mutableStateOf(0f) }
    var endMinutes by remember { mutableStateOf(0f) }


    val scheduler = AndroidAlarmScheduler(LocalContext.current)



    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Sizes.xlarge)
    ) {
        val (timeSlider, confirmButton) = createRefs()



        Column(
            modifier = Modifier.constrainAs(timeSlider){

            }
        ) {
            // 시간을 선택하는 슬라이더
            Text(text = "알람 시작 시간" , color = MaterialTheme.colorScheme.onSurface)

            Spacer(Modifier.size(Sizes.medium))

            Text(text = "시간: ${startHours.toInt()}" , color = MaterialTheme.colorScheme.onSurface)
            Slider(
                value = startHours,
                onValueChange = { startHours = it },
                valueRange = 0f..23f,
                steps = 23
            )

            // 분을 선택하는 슬라이더
            Text(text = "분: ${startMinutes.toInt()}" , color = MaterialTheme.colorScheme.onSurface)
            Slider(
                value = startMinutes,
                onValueChange = { startMinutes = it },
                valueRange = 0f..59f,
                steps = 59
            )

            Text(text = "알람간격: ${term.toInt()}" , color = MaterialTheme.colorScheme.onSurface)
            Slider(
                value = term,
                onValueChange = { term = it },
                valueRange = 0f..59f,
                steps = 59
            )

            Text(text = "알람 종료 시간" , color = MaterialTheme.colorScheme.onSurface)

            Spacer(Modifier.size(Sizes.medium))

            Text(text = "시간: ${endHours.toInt()}" , color = MaterialTheme.colorScheme.onSurface)
            Slider(
                value = endHours,
                onValueChange = { endHours = it },
                valueRange = 0f..23f,
                steps = 23
            )

            Text(text = "분: ${endMinutes.toInt()}" , color = MaterialTheme.colorScheme.onSurface)
            Slider(
                value = endMinutes,
                onValueChange = { endMinutes = it },
                valueRange = 0f..59f,
                steps = 59
            )



        }

        Image(
            painter = painterResource(id = R.drawable.confirmbutton),
            contentDescription = "",
            modifier = Modifier
                .clickable {
                    alarmViewModel.setAlarm(
                        listOf(startHours.toInt(), startMinutes.toInt(), term.toInt() ,
                            endHours.toInt() , endMinutes.toInt())
                    )

                    val alarmItem = AlarmItem(
                        LocalDateTime.now(),"dd"
                        ,startHours.toInt(), startMinutes.toInt(), term.toInt() ,
                        endHours.toInt() , endMinutes.toInt()
                    )

                    alarmItem.let(scheduler::schedule)

                    navController.navigate("Alarm")

                }
                .clip(CircleShape)
                .size(50.dp)
                .constrainAs(confirmButton) {
                    top.linkTo(timeSlider.bottom, margin = Sizes.large)
                    end.linkTo(timeSlider.end, margin = Sizes.large)
                }
        )
    }

}

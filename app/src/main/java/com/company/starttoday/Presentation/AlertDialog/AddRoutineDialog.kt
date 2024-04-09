package com.company.starttoday.Presentation.AlertDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.company.starttoday.Domain.Routine.Model.RoutineState
import com.company.starttoday.Domain.Routine.DTO.RoutineType
import com.company.starttoday.Domain.Routine.RoutineEvent


//enum class RoutineTimeTypes(val displayName: String) {
//    DAILY("일간"),
//    WEEKLY("주간"),
//    MONTHLY("월간"),
//    YEARLY("연간")
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRoutineDialog(
    state: RoutineState,
    onEvent: (RoutineEvent) -> Unit,
    modifier: Modifier = Modifier

) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(RoutineEvent.HideDialog)
        },

        title = { Text(text = "일정 등록하기", color = MaterialTheme.colorScheme.onSurface) },

        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.background(MaterialTheme.colorScheme.background)
            ) {

                // RoutineTimeType RadioButton 그룹
                RoutineType.values().forEach { timeType ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (timeType.typeName == state.routineTime),
                                onClick = { onEvent(RoutineEvent.SetRoutineTime(timeType.typeName)) }
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (timeType.typeName == state.routineTime),
                            onClick = { onEvent(RoutineEvent.SetRoutineTime(timeType.typeName)) }
                        )
                        Text(text = timeType.typeName, color = MaterialTheme.colorScheme.onSurface)
                    }
                }



                TextField(
                    value = state.routineTime,
                    onValueChange = {
                        onEvent(RoutineEvent.SetRoutineTime(it))
                    },
                    placeholder = {
                        Text("기간", color = MaterialTheme.colorScheme.onSurface)
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                        cursorColor = MaterialTheme.colorScheme.onSurface
                    )
                )

                TextField(
                    value = state.routineName,
                    onValueChange = {
                        onEvent(RoutineEvent.SetRoutineName(it))
                    },
                    placeholder = {
                        Text("할일", color = MaterialTheme.colorScheme.onSurface)
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                        cursorColor = MaterialTheme.colorScheme.onSurface

                    )

                )
            }

        },

        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(
                    onClick = {
                        onEvent(RoutineEvent.SaveRoutine)
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.background)
                ) {
                    Text("저장", color = MaterialTheme.colorScheme.onSurface)
                }
            }
        },

        containerColor = Color.Gray,
//        textContentColor = MaterialTheme.colorScheme.secondary

    )
}

package com.company.starttoday.Presentation.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.company.starttoday.Domain.Routine.DTO.RoutineType
import com.company.starttoday.Domain.Routine.Model.RoutineState
import com.company.starttoday.Domain.Routine.RoutineEvent
import com.company.starttoday.Presentation.AlertDialog.AddRoutineDialog
import com.company.starttoday.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutineScreen(
    state: RoutineState,
    onEvent: (RoutineEvent) -> Unit
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(RoutineEvent.ShowDialog)
                },
                modifier = Modifier
                    .padding(bottom = 80.dp)
                    .clip(CircleShape),
                containerColor = Color.White
            ) {
                Image(
                    painter = painterResource(R.drawable.add_icon),
                    contentDescription = "Add contact",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)

                )
            }
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        it
        if (state.isAddingContact) {
            AddRoutineDialog(state = state, onEvent = onEvent)
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RoutineType.values().forEach { routineType ->
                        Row(
                            modifier = Modifier
                                .clickable {
                                    onEvent(RoutineEvent.RoutineTimeType(routineType))
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = state.sortType == routineType.typeName,
                                onClick = {
                                    onEvent(RoutineEvent.RoutineTimeType(routineType))
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = MaterialTheme.colorScheme.primary,
                                    unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                            Text(
                                text = routineType.typeName,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            items(state.routines) { routine ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
//                            text = "${routine.routineTime} ${routine.routineName}",
                            text = routine.routineName,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    IconButton(onClick = {
                        onEvent(RoutineEvent.DeleteRoutine(routine))
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.delete_icon),
                            contentDescription = "Delete contact",
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(30.dp)
                        )
                    }
                }
            }
        }

    }
}



package com.company.starttoday.Presentation.Screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.company.starttoday.Presentation.Alarm.AlarmScreen
import com.company.starttoday.Presentation.Alarm.setAlarmScreen
import com.company.starttoday.Presentation.Screen.Screens.BottomNavItem
import com.company.starttoday.Presentation.Screen.Screens.Screen
import com.company.starttoday.Presentation.ThingOn_Image.Screens.MainScreen
import com.company.starttoday.Presentation.ThingOn_Image.ViewModel.ImageLinkViewModel
import com.company.starttoday.Presentation.ViewModel.RoutineViewModel
import com.company.starttoday.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun BottomNav() {
    val navController = rememberNavController()

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val bottomNavVisibleRoutes =
        listOf(
            Screen.MainScreen.route , Screen.AlarmScreen.route,
               Screen.RoutineScreen.route)

    val items = listOf(

        BottomNavItem(
            title = "알람",
            selectedIcon = R.drawable.alarm_icon,
            unselectedIcon = R.drawable.alarm_icon,

            ),

        BottomNavItem(
            title = "홈",
            selectedIcon = R.drawable.main_icon,
            unselectedIcon = R.drawable.main_icon,
            ),

        BottomNavItem(
            title = "일정",
            selectedIcon = R.drawable.routine_icon,
            unselectedIcon = R.drawable.routine_icon,
        ),
    )

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(1)
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            if (currentRoute in bottomNavVisibleRoutes) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.secondary
                ) {
                    items.forEachIndexed { index, bottomNavItem ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(bottomNavItem.title) {
                                    launchSingleTop = true
                                }
                            },
                            label = {
                                Text(
                                    text = bottomNavItem.title,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            },
                            alwaysShowLabel = true,
                            icon = {
                                Image(
                                    painter = painterResource(id = bottomNavItem.selectedIcon),
                                    contentDescription = "test",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape)
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(MaterialTheme.colorScheme.background)
                        )
                    }
                }
            }

        }
    ) {
        it
        NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
            composable(route = Screen.MainScreen.route) {
                val viewModel = hiltViewModel<ImageLinkViewModel>()
                val count by viewModel.page.collectAsStateWithLifecycle()
                MainScreen(count)
            }

            composable(route = Screen.AlarmScreen.route) {
                AlarmScreen(navController)
            }

            composable(route = Screen.setAlarmScreen.route) {
                setAlarmScreen(navController)
            }

            composable(route = Screen.RoutineScreen.route) {
                val viewModel = hiltViewModel<RoutineViewModel>()
                val state by viewModel.state.collectAsState()
                RoutineScreen(state = state, onEvent = viewModel::onEvent)
            }
        }
    }
}


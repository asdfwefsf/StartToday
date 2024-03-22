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
import com.company.starttoday.Presentation.Screens.BottomNavItem
import com.company.starttoday.Presentation.Screens.Screen
import com.company.starttoday.Presentation.ViewModel.RoutineViewModel
import com.company.starttoday.Presentation.ViewModel.StringAllViewModel
import com.company.starttoday.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun BottomNav() {
    val navController = rememberNavController()

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val bottomNavVisibleRoutes =
        listOf(Screen.MainScreen.route , Screen.AlarmScreen.route,
               Screen.RoutineScreen.route)


    val items = listOf(
//        BottomNavItem(
//            title = "Main",
//            selectedIcon = Icons.Filled.Home,
//            unselectedIcon = Icons.Outlined.Home,
//        ),

        BottomNavItem(
            title = "Alarm",
            selectedIcon = R.drawable.alarm_icon,
            unselectedIcon = R.drawable.alarm_icon,

            ),

        BottomNavItem(
            title = "Main",
            selectedIcon = R.drawable.main_icon,
            unselectedIcon = R.drawable.main_icon,

            ),

        BottomNavItem(
            title = "Routine",
            selectedIcon = R.drawable.routine_icon,
            unselectedIcon = R.drawable.routine_icon,
        ),

//        BottomNavItem(
//            title = "Weather",
//            selectedIcon = R.drawable.weatherscreen,
//            unselectedIcon = R.drawable.weatherscreen
//        )

    )

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(1)
    }

//    val stringAllViewModel: StringAllViewModel = hiltViewModel()

//    var selectedItemIndex by rememberSaveable {
//        stringAllViewModel.pagerState
//    }

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
//                                BadgedBox(
//                                    badge = {
//                                        if(bottomNavItem.badgeCount != null) {
//                                            Badge {
//                                                Text(text = bottomNavItem.badgeCount.toString())
//                                            }
//                                        } else if(bottomNavItem.alarm) {
//                                            Badge()
//                                        }
//                                    }
//                                ){

                                // 기존 Icon 코드
//                                Icon(
//                                    imageVector = if (index == selectedItemIndex) {
//                                        bottomNavItem.selectedIcon
//                                    } else {
//                                        bottomNavItem.unselectedIcon
//                                    },
//                                    contentDescription = bottomNavItem.title
//                                )
                                // 기존 Icon 코드

                                Image(
                                    painter = painterResource(id = bottomNavItem.selectedIcon),
                                    contentDescription = "test",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape)
                                )

//                                }


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
                val viewModel = hiltViewModel<StringAllViewModel>()
                val count by viewModel.page.collectAsStateWithLifecycle()

                MainScreen(navController, count)
            }

            composable(route = Screen.AlarmScreen.route) {
                val viewModel = hiltViewModel<StringAllViewModel>()
                val count by viewModel.page.collectAsStateWithLifecycle()

                AlarmScreen(navController)
            }

            composable(route = Screen.setAlarmScreen.route) {
                setAlarmScreen(navController)
            }

            composable(route = Screen.RoutineScreen.route) {
                val viewModel = hiltViewModel<RoutineViewModel>()
                val state by viewModel.state.collectAsState()

                RoutineScreen(navController, state = state, onEvent = viewModel::onEvent)
            }

//            composable(route = Screen.WeatherScreen.route) {
//
//                WeatherScreen()
//            }

        }


    }
}


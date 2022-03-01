package com.BFCAI.encryptionapp.android.Presentation.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.BFCAI.encryptionapp.android.Presentation.Navigation.Screens
import com.BFCAI.encryptionapp.android.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        Screens.HomeScreen,
        Screens.MyFilesScreen,
        Screens.SentFilesScreen,
        Screens.SendScreen,
        Screens.ProfileScreen
    )
    Box(
        modifier = Modifier
            .padding(start = 7.dp , end = 7.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(1.dp)
                .background(
                    color = MaterialTheme.colors.onBackground,
                    shape = MaterialTheme.shapes.large
                )
        ) {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.onBackground,
                contentColor = Color.White,
                elevation = 0.dp,
                modifier = Modifier.padding(3.dp)
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { item ->
                    BottomNavigationItem(
                        icon = { Icon(painterResource(id = item.icon!!), contentDescription = item.title) },
                        label = { Text(text = item.title , maxLines = 1) },
                        selectedContentColor = MaterialTheme.colors.secondary,
                        unselectedContentColor = Color.White.copy(0.4f),
                        alwaysShowLabel = false,
                        selected = currentRoute == item.rout,
                        onClick = {
                            navController.navigate(item.rout) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    }


}
package com.BFCAI.encryptionapp.android.Presentation.Navigation

import android.app.Activity
import android.widget.Toast
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.BackHandler
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.BFCAI.encryptionapp.android.Presentation.Navigation.EncryptionScreen.EncryptionScreen
import com.BFCAI.encryptionapp.android.Presentation.Navigation.EncryptionScreen.EncryptionViewModel
import com.BFCAI.encryptionapp.android.Presentation.Navigation.HomeScreen.HomeScreen
import com.BFCAI.encryptionapp.android.Presentation.Navigation.LoginScreen.LoginScreen
import com.BFCAI.encryptionapp.android.Presentation.Navigation.LoginScreen.LoginViewModel
import com.BFCAI.encryptionapp.android.Presentation.Navigation.MyFileScreen.MyFilesScreen
import com.BFCAI.encryptionapp.android.Presentation.Navigation.ProfileScreen.ProfileScreen
import com.BFCAI.encryptionapp.android.Presentation.Navigation.SendScreen.SendScreen
import com.BFCAI.encryptionapp.android.Presentation.Navigation.SentFileScreen.SentFilesScreen
import com.BFCAI.encryptionapp.android.Presentation.Navigation.SignUpScreen.SignUpScreen
import com.BFCAI.encryptionapp.android.Presentation.Navigation.SignUpScreen.SignUpViewModel
import com.BFCAI.encryptionapp.android.Presentation.Navigation.SplashScreen.SplashScreen
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalStdlibApi
@Composable
fun Navigation (acivity: ViewModelStoreOwner,activity2:Activity){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.HomeScreen.rout) {
        composable(route = Screens.SplashScreen.rout) { navBackStackEntry ->
            SplashScreen()
            LaunchedEffect(Unit) {
                delay(2000)
                activity2.runOnUiThread {
                    navController.navigate(Screens.LoginScreen.rout)
                }
            }
        }
        composable(route = Screens.LoginScreen.rout) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val recipeListViewModel:LoginViewModel= viewModel(acivity,"LoginViewModel",factory)
            LoginScreen(
                state = recipeListViewModel.state.value,
                onTriggerEvent = recipeListViewModel::onTriggerEvent,
                loginSuccess={
                    navController.navigate(Screens.HomeScreen.rout)
                },
                signUpClick = {
                    navController.navigate(Screens.SignUpScreen.rout)
                }
            )
            BackHandler {
                activity2.finish()
            }
        }
        composable(route = Screens.SignUpScreen.rout) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val signupViewModel:SignUpViewModel= viewModel(acivity,"SignUpViewModel",factory)
            SignUpScreen(
                state= signupViewModel.state.value,
                onTriggerEvent = signupViewModel::onTriggerEvent,
                signupSuccess={
                    Toast.makeText(activity2,"We sent to your email please activate it",Toast.LENGTH_LONG).show()
                    navController.navigate(Screens.LoginScreen.rout)
                },
            )
        }
        composable(route = Screens.HomeScreen.rout) { navBackStackEntry ->
            HomeScreen(navController)
        }
        composable(route = Screens.MyFilesScreen.rout) { navBackStackEntry ->
            MyFilesScreen(navController)
        }
        composable(route = Screens.SentFilesScreen.rout) { navBackStackEntry ->
            SentFilesScreen(navController)
        }
        composable(route = Screens.SendScreen.rout) { navBackStackEntry ->
            SendScreen(navController)
        }
        composable(route = Screens.ProfileScreen.rout) { navBackStackEntry ->
            ProfileScreen(navController)
        }
        composable(route = Screens.EncryptionScreen.rout) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val encryptionViewModel:EncryptionViewModel= viewModel(acivity,"EncryptionViewModel",factory)
            EncryptionScreen(
               navController =  navController,
                state = encryptionViewModel.state.value,
                onTriggerEvent = encryptionViewModel::onTriggerEvent,
                uploadfiles = encryptionViewModel:: uploadfiles
            )
        }
    }

}
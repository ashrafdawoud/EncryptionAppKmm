package com.BFCAI.encryptionapp.android.Presentation.Navigation

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.BFCAI.encryptionapp.Domain.Utils.PublicData
import com.BFCAI.encryptionapp.android.Presentation.Navigation.EncryptionScreen.EncryptionScreen
import com.BFCAI.encryptionapp.android.Presentation.Navigation.EncryptionScreen.EncryptionViewModel
import com.BFCAI.encryptionapp.android.Presentation.Navigation.HomeScreen.HomeScreen
import com.BFCAI.encryptionapp.android.Presentation.Navigation.LoginScreen.LoginScreen
import com.BFCAI.encryptionapp.android.Presentation.Navigation.LoginScreen.LoginViewModel
import com.BFCAI.encryptionapp.android.Presentation.Navigation.MyFileScreen.MyFilesScreen
import com.BFCAI.encryptionapp.android.Presentation.Navigation.MyFileScreen.UserFilesViewModel
import com.BFCAI.encryptionapp.android.Presentation.Navigation.ProfileScreen.ProfileScreen
import com.BFCAI.encryptionapp.android.Presentation.Navigation.SearchScreen.SearchScreen
import com.BFCAI.encryptionapp.android.Presentation.Navigation.SearchScreen.SearchScreenViewModel
import com.BFCAI.encryptionapp.android.Presentation.Navigation.ContactScreen.SendScreen
import com.BFCAI.encryptionapp.android.Presentation.Navigation.ContactScreen.SendViewModel
import com.BFCAI.encryptionapp.android.Presentation.Navigation.SentFileScreen.SentFilesScreen
import com.BFCAI.encryptionapp.android.Presentation.Navigation.SentFileScreen.SentFilesViewModel
import com.BFCAI.encryptionapp.android.Presentation.Navigation.ShareFileScreen.ShareFileScreen
import com.BFCAI.encryptionapp.android.Presentation.Navigation.SignUpScreen.SignUpScreen
import com.BFCAI.encryptionapp.android.Presentation.Navigation.SignUpScreen.SignUpViewModel
import com.BFCAI.encryptionapp.android.Presentation.Navigation.SplashScreen.SplashScreen
import com.BFCAI.encryptionapp.android.Presentation.Navigation.SuccessScreen.SuccessScreen
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalStdlibApi
@Composable
fun Navigation(acivity: ViewModelStoreOwner, activity2: Activity) {
    val navController = rememberNavController()
    val pref = LocalContext.current.getSharedPreferences(
        PublicData.GENERAL_PREF,
        AppCompatActivity.MODE_PRIVATE
    )
    NavHost(navController = navController, startDestination = Screens.SplashScreen.rout) {
        composable(route = Screens.SplashScreen.rout) { navBackStackEntry ->
            SplashScreen()
            LaunchedEffect(Unit) {
                delay(2000)
                activity2.runOnUiThread {
                    if (pref.getString("User_Id", null) != null)
                        navController.navigate(Screens.HomeScreen.rout)
                    else
                        navController.navigate(Screens.LoginScreen.rout)
                }
            }
        }
        composable(route = Screens.LoginScreen.rout) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val recipeListViewModel: LoginViewModel = viewModel(acivity, "LoginViewModel", factory)
            LoginScreen(
                state = recipeListViewModel.state.value,
                onTriggerEvent = recipeListViewModel::onTriggerEvent,
                loginSuccess = {
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
            val signupViewModel: SignUpViewModel = viewModel(acivity, "SignUpViewModel", factory)
            SignUpScreen(
                state = signupViewModel.state.value,
                onTriggerEvent = signupViewModel::onTriggerEvent,
                signupSuccess = {
                    Toast.makeText(
                        activity2,
                        "We sent to your email please activate it",
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate(Screens.LoginScreen.rout)
                },
            )
        }
        composable(route = Screens.HomeScreen.rout) { navBackStackEntry ->
            HomeScreen(navController)
        }
        composable(route = Screens.MyFilesScreen.rout) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val userFilesViewModel: UserFilesViewModel =
                viewModel(acivity, "UserFilesViewModel", factory)
            MyFilesScreen(
                navController = navController,
                state = userFilesViewModel.state.value,
                event = userFilesViewModel::onEventTrigger
            )
        }
        composable(route = Screens.SentFilesScreen.rout) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val sentFilesViewModel: SentFilesViewModel =
                viewModel(acivity, "SentFilesViewModel", factory)
            SentFilesScreen(
                navController = navController,
                state = sentFilesViewModel.state.value,
                event = sentFilesViewModel::onEventTrigger
            )
        }
        composable(route = Screens.SendScreen.rout) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val sendViewModel: SendViewModel = viewModel(acivity, "SendViewModel", factory)
            SendScreen(
                navController = navController,
                state = sendViewModel.state.value,
                event = sendViewModel::onTriggerEnvent
            )
        }
        composable(route = Screens.ProfileScreen.rout) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val sendViewModel: SendViewModel = viewModel(acivity, "SendViewModel", factory)
            val userFilesViewModel: UserFilesViewModel = viewModel(acivity, "UserFilesViewModel", factory)
            ProfileScreen(
                navController = navController,
                sendState = sendViewModel.state.value,
                userState = userFilesViewModel.state.value
                )
        }
        composable(route = Screens.EncryptionScreen.rout) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val encryptionViewModel: EncryptionViewModel =
                viewModel(acivity, "EncryptionViewModel", factory)
            EncryptionScreen(
                navController = navController,
                state = encryptionViewModel.state.value,
                onTriggerEvent = encryptionViewModel::onTriggerEvent,
                uploadfiles = encryptionViewModel::uploadfiles
            )
        }
        composable(route = Screens.SuccessScreen.rout+"/{Screen}") { navBackStackEntry ->
            SuccessScreen(navController , navBackStackEntry.arguments?.getString("Screen")!!)
        }
        composable(route = Screens.SearchScreen.rout) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewmodel: SearchScreenViewModel =
                viewModel(acivity, "SearchScreenViewModel", factory)
            SearchScreen(
                navController = navController,
                state = viewmodel.state.value,
                event = viewmodel::onTriggerEvent
            )
        }
        composable(route = Screens.ShareFileScreen.rout+"/{ReciverId}") { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val userFilesViewModel: UserFilesViewModel =
                viewModel(acivity, "UserFilesViewModel", factory)
            ShareFileScreen(navController ,userFilesViewModel.state.value, userFilesViewModel::onEventTrigger , navBackStackEntry.arguments?.getString("ReciverId"))
        }
    }

}
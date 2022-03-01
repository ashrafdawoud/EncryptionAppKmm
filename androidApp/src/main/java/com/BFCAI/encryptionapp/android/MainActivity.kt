package com.BFCAI.encryptionapp.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.BFCAI.encryptionapp.android.Presentation.Navigation.LoginScreen.LoginViewModel
import com.BFCAI.encryptionapp.android.Presentation.Navigation.Navigation
import com.example.food1fork.android.Presentation.Theme.AppTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp


@ExperimentalMaterialApi
@ExperimentalStdlibApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme(displayProgressBar = false, onRemoveHeadMessage = { /*TODO*/ }) {
                Navigation(this,this)
            }
        }

    }
}

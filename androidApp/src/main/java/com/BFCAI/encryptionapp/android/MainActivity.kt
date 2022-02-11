package com.BFCAI.encryptionapp.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.BFCAI.encryptionapp.android.Presentation.Navigation.LoginScreen.LoginViewModel
import com.BFCAI.encryptionapp.android.Presentation.Navigation.Navigation
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp


@ExperimentalStdlibApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation(this,this)
        }

    }
}

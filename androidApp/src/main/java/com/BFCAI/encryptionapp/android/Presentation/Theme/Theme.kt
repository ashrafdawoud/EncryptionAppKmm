package com.example.food1fork.android.Presentation.Theme

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.BFCAI.encryptionapp.android.R
import com.example.food1fork.android.Presentation.Componnents.CircularIndeterminateProgressBar
import com.example.food1fork.android.Presentation.Componnents.ProcessDialogQueue
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Utils.Queue

@SuppressLint("ConflictingOnColor")
private val LightThemeColors = lightColors(
    primary = yello,
    primaryVariant = Blue400,
    onPrimary = Black2,
    secondary = buttons,
    secondaryVariant = Teal300,
    onSecondary = Color.Black,
    error = RedErrorDark,
    onError = RedErrorLight,
    background = background,
    onBackground = componentBackground,
    surface = cards,
    onSurface = Black2,
)

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun AppTheme(
    displayProgressBar: Boolean,
    queue: Queue<GenericMessageInfo> = Queue(mutableListOf()),
    onRemoveHeadMessage:()->Unit,
    content: @Composable () -> Unit,

    ) {
    MaterialTheme(
        colors = LightThemeColors,
        typography = QuickSandTypography,
        shapes = AppShaps
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = background)
        ){
            ProcessDialogQueue(
                queue = queue,
                onRemoveHeadMessage =onRemoveHeadMessage
            )
            content()
            CircularIndeterminateProgressBar(isDisplayed = displayProgressBar, verticalBias = 0.5f)
        }
    }
}
@Composable
fun AppThemeNoArg(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightThemeColors,
        typography = QuickSandTypography,
        shapes = AppShaps
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = background)
        ){
            content()

        }
    }
}
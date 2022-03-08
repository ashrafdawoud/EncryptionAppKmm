package com.BFCAI.encryptionapp.android.Presentation.Navigation.EncryptionScreen.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.BFCAI.encryptionapp.Presentation.EncryptionScreen.EncryptionScreenEvents
import com.BFCAI.encryptionapp.Presentation.EncryptionScreen.EncryptionScreenState
import com.BFCAI.encryptionapp.android.R

@Composable
fun EncryptionTypeMenu(
    state: EncryptionScreenState,
    onTriggerEvent: (EncryptionScreenEvents) -> Unit,
    menuItems: List<String>,
    menuExpandedState: Boolean,
    seletedIndex: Int,
    updateMenuExpandStatus: () -> Unit,
    onDismissMenuView: () -> Unit,
    onMenuItemclick: (Int) -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopStart)
            .background(
                color = MaterialTheme.colors.onBackground,
                shape = MaterialTheme.shapes.large
            )
            .border(0.5.dp, Color.DarkGray, shape = MaterialTheme.shapes.large)
            .clickable(
                onClick = {
                    updateMenuExpandStatus()
                },
            ),

        ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            val (lable, iconView) = createRefs()

            Text(
                text = state.encryType, //menuItems[seletedIndex],
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(lable) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(iconView.start)
                        width = Dimension.fillToConstraints
                    }
            )

            val displayIcon: Painter = painterResource(id = R.drawable.ic_baseline_arrow_drop_down)

            Icon(
                painter = displayIcon,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp, 20.dp)
                    .constrainAs(iconView) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                tint = Color.White
            )

            DropdownMenu(
                expanded = menuExpandedState,
                onDismissRequest = { onDismissMenuView() },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)

            ) {
                menuItems.forEachIndexed { index, title ->
                    DropdownMenuItem(
                        onClick = {
                            onTriggerEvent(EncryptionScreenEvents.choose_Encr(title))
                            onMenuItemclick(index)

                        }) {
                        Text(text = title, color = Color.Gray)
                    }
                }
            }
        }
    }
}
package com.BFCAI.encryptionapp.android.Presentation.Navigation.ShareFileScreen

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.BFCAI.encryptionapp.Domain.Utils.PublicData
import com.BFCAI.encryptionapp.Presentation.UserFilesScreen.UserFilesEvent
import com.BFCAI.encryptionapp.Presentation.UserFilesScreen.UserFilesState
import com.BFCAI.encryptionapp.android.Presentation.Component.NoDataComponent
import com.BFCAI.encryptionapp.android.Presentation.Navigation.MyFileScreen.Component.UserFileCard
import com.BFCAI.encryptionapp.android.Presentation.Navigation.ShareFileScreen.Component.ShareFileCard
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ShareFileListComponent(
    state:UserFilesState,
    event:(UserFilesEvent) ->Unit,
    reciverId:String?
){

    val context = LocalContext.current
    SwipeRefresh(
        state = rememberSwipeRefreshState(state.isloading),
        onRefresh = {
            event(
                UserFilesEvent.refreshListner(
                    context.getSharedPreferences(
                        PublicData.GENERAL_PREF,
                        AppCompatActivity.MODE_PRIVATE
                    ).getString("User_Id", "")!!
                )
            )
        },
        indicator = { state , refreshTrigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger,
                backgroundColor = Color.White
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        if (state.data == null) {
            if (!state.isloading)
                NoDataComponent()
        } else if (state.data!!.results.size == 0) {
            NoDataComponent()
        } else {
            Box(modifier = Modifier.fillMaxSize().padding(10.dp)) {
                if (state.cardisClicked) {
                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        val (progressBar) = createRefs()
                        val topBias = createGuidelineFromTop(0.5f)
                        CircularProgressIndicator(
                            modifier = Modifier.constrainAs(progressBar)
                            {
                                top.linkTo(topBias)
                                end.linkTo(parent.end)
                                start.linkTo(parent.start)
                            },
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
                LazyColumn {
                    itemsIndexed(
                        state.data!!.results
                    ) { index, item ->
                        ShareFileCard(item = item, event = event ,reciverId)
                    }
                }
            }
        }
    }
}
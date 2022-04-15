package com.BFCAI.encryptionapp.Presentation.SendScreen

import com.BFCAI.encryptionapp.Presentation.SharedFilesScreen.SharedFilesEvent

sealed class SendScreenEvent {
    object refreshListner : SendScreenEvent()
    data class deleteContact(val objectId: String) : SendScreenEvent()
}

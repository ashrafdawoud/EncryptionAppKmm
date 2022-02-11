package com.BFCAI.encryptionapp.Interactors.UserReposetories

import com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.UserCalls.UserInterface
import com.BFCAI.encryptionapp.Domain.Model.UserModel
import com.BFCAI.encryptionapp.Domain.Utils.DataState
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Model.UIComponentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository constructor(
    val userInterface: UserInterface
) {
    suspend fun login(email: String, password: String): Flow<DataState<UserModel>> = flow {
        emit(DataState.loading())
        try {
            val userModel = userInterface.login(email, password)
            emit(DataState.data(message = null, data = userModel))
        } catch (e: Exception) {
            emit(
                DataState.error(
                    message = GenericMessageInfo.Builder()
                        .id("Login.ERROR")
                        .title("Error")
                        .description(e.message ?: "UnKnownErroe")
                        .uiComponentType(UIComponentType.SnackBar)
                        .onDismiss {  }
                )
            )

        }
    }
}
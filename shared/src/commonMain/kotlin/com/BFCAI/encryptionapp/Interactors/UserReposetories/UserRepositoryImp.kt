package com.BFCAI.encryptionapp.Interactors.UserReposetories

import com.BFCAI.encryptionapp.DataSource.Network.EntityDto.ResponseDto
import com.BFCAI.encryptionapp.DataSource.Network.KtorInterfaces.UserCalls.UserInterface
import com.BFCAI.encryptionapp.Domain.Model.UserModel
import com.BFCAI.encryptionapp.Domain.Utils.DataState
import com.example.food2fork.Food2ForkKmm.Domain.Model.GenericMessageInfo
import com.example.food2fork.Food2ForkKmm.Domain.Model.UIComponentType
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.statement.*
import io.ktor.utils.io.charsets.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImp constructor(
    val userInterface: UserInterface
) :UserRepository{
     override suspend fun login(email: String, password: String): Flow<DataState<UserModel>> = flow {
        emit(DataState.loading())
        try {
            val userModel = userInterface.login(email, password)
            emit(DataState.data(message = null, data = userModel))
        } catch (e: Exception) {
            if (e is ClientRequestException){
                var res = e.response.receive<ResponseDto>().error
                emit(
                    DataState.error(
                        message = GenericMessageInfo.Builder()
                            .id("Login.ERROR")
                            .title("Error")
                            .description(res ?: "UnKnownErroe")
                            .uiComponentType(UIComponentType.SnackBar)
                            .onDismiss {  }
                    )
                )
            }else{
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
    override suspend fun signUp(email: String, password: String , username:String): Flow<DataState<String>> = flow {
        emit(DataState.loading())
        try {
            val response = userInterface.Signup(email, password , username)
            if (response.equals("Success")){
                emit(DataState.data(message = null, data = response))
            }else{
                emit(
                    DataState.error(
                        message = GenericMessageInfo.Builder()
                            .id("Login.ERROR")
                            .title("Error")
                            .description(response)
                            .uiComponentType(UIComponentType.SnackBar)
                            .onDismiss {  }
                    )
                )
            }
        } catch (e: Exception) {
            if (e is ClientRequestException){
                val res = e.response.receive<ResponseDto>().error
                emit(
                    DataState.error(
                        message = GenericMessageInfo.Builder()
                            .id("Login.ERROR")
                            .title("Error")
                            .description(res.toString() ?: "UnKnownErroe")
                            .uiComponentType(UIComponentType.SnackBar)
                            .onDismiss {  }
                    )
                )
            }


        }
    }
    override suspend fun ActivateEmail(email: String): Flow<DataState<String>> = flow {
        emit(DataState.loading())
        try {
            val response = userInterface.ActivateEmail(email)
            if (response.equals("Success")){
                emit(DataState.data(message = null, data = response))
            }else{
                emit(
                    DataState.error(
                        message = GenericMessageInfo.Builder()
                            .id("Login.ERROR")
                            .title("Error")
                            .description(response)
                            .uiComponentType(UIComponentType.SnackBar)
                            .onDismiss {  }
                    )
                )
            }
        } catch (e: Exception) {
            if (e is ClientRequestException){
                val res = e.response.receive<String>()
                emit(
                    DataState.error(
                        message = GenericMessageInfo.Builder()
                            .id("Login.ERROR")
                            .title("Error")
                            .description(res.toString() ?: "UnKnownErroe")
                            .uiComponentType(UIComponentType.SnackBar)
                            .onDismiss {  }
                    )
                )
            }


        }
    }
}
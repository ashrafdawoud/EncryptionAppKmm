package com.BFCAI.encryptionapp.Presentation.SearchScreen

sealed class SearchScreenEvent (){
    data class Search(val token:String , val email:String) : SearchScreenEvent()
    data class AddContact(val token:String , val contact2:String) : SearchScreenEvent()
}
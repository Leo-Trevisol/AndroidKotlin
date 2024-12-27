package com.devmasterteam.tasks.service.listener

interface APIListener<T> {

    fun onSucess(response : T)
    fun onFailure(message : String)
}
package com.devmasterteam.tasks.service.repository

import android.location.GnssAntennaInfo.Listener
import com.devmasterteam.tasks.service.constants.TaskConstants
import com.devmasterteam.tasks.service.listener.APIListener
import com.google.gson.Gson
import retrofit2.Response

open class BaseRepository {

    private fun failResponse(str : String) : String{
        return Gson().fromJson(str, String::class.java)
    }

    fun <T> handleResponse(response : Response<T>, listener : APIListener<T>){
        if(response.code() == TaskConstants.HTTP.SUCCESS){
            response.body()?.let { listener.onSucess(it) }
        }else{
            listener.onFailure(failResponse(response.errorBody()!!.string()))
        }
    }
}
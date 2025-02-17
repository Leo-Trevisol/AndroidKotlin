package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.constants.TaskConstants
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.PriorityModel
import com.devmasterteam.tasks.service.repository.local.TaskDatabase
import com.devmasterteam.tasks.service.repository.remote.PersonService
import com.devmasterteam.tasks.service.repository.remote.PriorityService
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PriorityRepository(context : Context) : BaseRepository(context){

    private val remote = RetrofitClient.getService(PriorityService::class.java)

    private val database = TaskDatabase.getDatabase(context).priorityDAO()

    companion object{
        private val cache = mutableMapOf<Int, String>()

        fun getDescription(id : Int) : String{
            return cache[id] ?: ""
        }

        fun setDescription(id : Int, description : String){
            cache[id] = description
        }
    }

    fun getDescription(id : Int) : String{

        val cached = PriorityRepository.getDescription(id)

        return if(cached == ""){
            val description = database.getDescription(id)
            PriorityRepository.setDescription(id, description)
             description
        }else{
            cached
        }

        return database.getDescription(id)
    }

    fun list(listener : APIListener<List<PriorityModel>>){
            if (!isConnectionAvailable()) {
                listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
                return
            }
        executeCall(remote.list(), listener)
    }

    fun list() : List<PriorityModel>{
        return database.list()
    }
    fun save(list : List<PriorityModel>){
        database.clear()
        database.save(list)
    }


}
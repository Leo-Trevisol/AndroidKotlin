package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.constants.TaskConstants
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.PersonModel
import com.devmasterteam.tasks.service.model.PriorityModel
import com.devmasterteam.tasks.service.model.TaskModel
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import com.devmasterteam.tasks.service.repository.remote.TaskService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskRepository(context: Context) : BaseRepository(context){

    private val remote = RetrofitClient.getService(TaskService::class.java)

    fun list(listener : APIListener<List<TaskModel>>){
        val call = remote.list()
        list(call, listener)
    }

    fun listNext(listener : APIListener<List<TaskModel>>){
        val call = remote.listNext()
        list(call, listener)
    }

    fun listOverdue(listener : APIListener<List<TaskModel>>){
        val call = remote.listOverdue()
        list(call, listener)
    }

    fun list(call : Call<List<TaskModel>>, listener : APIListener<List<TaskModel>>){
        executeCall(call, listener)
    }

    fun create(task : TaskModel, listener : APIListener<Boolean>) {

        val call = remote.create(task.priorityId, task.description, task.dueDate, task.complete)
        executeCall(call, listener)
    }

    fun load(id : Int, listener : APIListener<TaskModel>){
        executeCall(remote.load(id), listener)
    }

    fun delete(id : Int, listener : APIListener<Boolean>){
        executeCall(remote.delete(id), listener)
    }

    fun complete(id : Int, listener : APIListener<Boolean>){
        executeCall(remote.complete(id), listener)
    }

    fun undo(id : Int, listener : APIListener<Boolean>){
        executeCall(remote.undo(id), listener)
    }

}
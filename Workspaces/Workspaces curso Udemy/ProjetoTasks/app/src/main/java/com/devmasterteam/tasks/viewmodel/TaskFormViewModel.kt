package com.devmasterteam.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.PriorityModel
import com.devmasterteam.tasks.service.model.TaskModel
import com.devmasterteam.tasks.service.model.ValidationModel
import com.devmasterteam.tasks.service.repository.PriorityRepository
import com.devmasterteam.tasks.service.repository.TaskRepository

class TaskFormViewModel(application: Application) : AndroidViewModel(application) {

    private val priorityRepository = PriorityRepository(application.applicationContext)
    private val taskRepository = TaskRepository(application.applicationContext)

    private val _priorityList = MutableLiveData<List<PriorityModel>>()
    val priorityList : LiveData<List<PriorityModel>> = _priorityList

    private val _taskSave = MutableLiveData<ValidationModel>()
    val taskSave : LiveData<ValidationModel> = _taskSave

    private val _task = MutableLiveData<TaskModel>()
    val task : LiveData<TaskModel> = _task

    private val _taskLoad = MutableLiveData<ValidationModel>()
    val taskLoad : LiveData<ValidationModel> = _taskLoad

    fun loadPriorities(){
        _priorityList.value =  priorityRepository.list()
    }

    fun load(id : Int){
        taskRepository.load(id, object : APIListener<TaskModel>{
            override fun onSucess(response: TaskModel) {
                _task.value = response
            }

            override fun onFailure(message: String) {
                _taskLoad.value = ValidationModel(message)
            }

        })
    }

    fun save(task : TaskModel){
        taskRepository.create(task, object : APIListener<Boolean>{
            override fun onSucess(response: Boolean) {
               _taskSave.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _taskSave.value = ValidationModel(message)
            }

        })
    }

}
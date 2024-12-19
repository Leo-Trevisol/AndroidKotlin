package com.project.projetoconvidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.projetoconvidados.model.GuestModel
import com.project.projetoconvidados.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)

    private val listGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listGuests


     fun getAll(){
         listGuests.value =  repository.getAll()
    }

    fun getPresent(){
        listGuests.value =  repository.getListPresent()
    }

    fun getAbsent(){
        listGuests.value =  repository.getListAbsent()
    }

    fun delete(id : Int){
        repository.delete(id)
    }
}
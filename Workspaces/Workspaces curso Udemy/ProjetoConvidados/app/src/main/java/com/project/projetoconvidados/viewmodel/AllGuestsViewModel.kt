package com.project.projetoconvidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.projetoconvidados.model.GuestModel
import com.project.projetoconvidados.repository.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)

    private val listGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listGuests


     fun getAll(){
         listGuests.value =  repository.getAll()
    }
}
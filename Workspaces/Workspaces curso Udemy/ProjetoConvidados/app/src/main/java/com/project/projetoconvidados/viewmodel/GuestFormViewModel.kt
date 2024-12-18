package com.project.projetoconvidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.project.projetoconvidados.model.GuestModel
import com.project.projetoconvidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)

    fun insert(guest : GuestModel){
        repository.insert(guest)
    }

    fun get(id : Int){
        repository.get(id)
    }



}
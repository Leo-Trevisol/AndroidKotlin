package com.project.modelomvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){

    private var textWelcome = MutableLiveData<String>()
    private var isLogin = MutableLiveData<Boolean>()
    private val personRepository = PersonRepository()

    fun welcome() : LiveData<String>{
        return textWelcome
    }

    fun login() : LiveData<Boolean>{
        return isLogin
    }

    fun doLogin(email : String, senha : String){
        isLogin.value = personRepository.login(email, senha)
    }


}
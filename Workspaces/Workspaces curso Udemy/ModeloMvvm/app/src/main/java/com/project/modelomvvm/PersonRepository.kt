package com.project.modelomvvm

class PersonRepository {

    fun login(email : String, senha : String) : Boolean{
        return (email != "" && senha != "")
    }
}
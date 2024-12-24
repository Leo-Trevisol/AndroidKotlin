package com.devmasterteam.tasks.service.repository.remote

import com.devmasterteam.tasks.service.model.PersonModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PersonService {

    //POST http://devmasterteam.com/CursoAndroidAPI/Authentication/Login
    @POST("Authentication/Login")
    @FormUrlEncoded
    fun login(@Field("email") email : String,
              @Field("passowrd") password : String
    ) : Call<PersonModel>

    //POST http://devmasterteam.com/CursoAndroidAPI/Authentication/Create
    @POST("Authentication/Create")
    @FormUrlEncoded
    fun create(@Field("name") name : String,
               @Field("email") email : String,
               @Field("passowrd") password : String
    ) : Call<PersonModel>
}
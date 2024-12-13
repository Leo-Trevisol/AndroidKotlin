package com.project.projetoconvidados.repository

import android.content.ContentValues
import android.content.Context
import com.project.projetoconvidados.constants.DataBaseConstants
import com.project.projetoconvidados.model.GuestModel

//MANIPULAÇÃO DE DADOS DO BANCO
class GuestRepository private constructor(context : Context){

    private val guestDataBase = GuestDataBase(context)

    //SINGLETON
    companion object{
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context) : GuestRepository {
            if(!Companion::repository.isInitialized){
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun insert(guest : GuestModel): Boolean {

        return try {
            //pega a conexão weitable, dizendo que vamos inserir
            val db = guestDataBase.writableDatabase

            val presence = if(guest.presence) 1 else 0

            //Lista de valores com chave e valor
            val values = ContentValues()
            values.put(DataBaseConstants.COLUMS.NAME, guest.name)
            values.put(DataBaseConstants.COLUMS.PRESENCE, presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)
            true
        }catch (e : Exception){
            false
        }
    }

    fun update(){

    }
}
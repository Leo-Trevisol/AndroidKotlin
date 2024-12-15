package com.project.projetoconvidados.repository

import android.annotation.SuppressLint
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

    fun update(guest : GuestModel): Boolean {

        return try {
            //pega a conexão weitable, dizendo que vamos inserir
            val db = guestDataBase.writableDatabase

            val presence = if(guest.presence) 1 else 0

            //Lista de valores com chave e valor
            val values = ContentValues()
            values.put(DataBaseConstants.COLUMS.NAME, guest.name)
            values.put(DataBaseConstants.COLUMS.PRESENCE, presence)

            val selection = DataBaseConstants.GUEST.TABLE_NAME + " = ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, values,selection, args)
            true
        }catch (e : Exception){
            false
        }
    }

    fun delete(id : Int): Boolean {

        return try {
            //pega a conexão weitable, dizendo que vamos inserir
            val db = guestDataBase.writableDatabase

            val selection = DataBaseConstants.GUEST.TABLE_NAME + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true
        }catch (e : Exception){
            false
        }
    }

    @SuppressLint("Range")
    fun getAll() : List<GuestModel>{

        val list = mutableListOf<GuestModel>()

        try {

            val db = guestDataBase.readableDatabase

            val selection = arrayOf(
                DataBaseConstants.COLUMS.ID,
                DataBaseConstants.COLUMS.NAME,
                DataBaseConstants.COLUMS.PRESENCE
            )

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, selection,
                null, null, null, null, null
            )

            if(cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUMS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.COLUMS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUMS.PRESENCE))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }

        }catch (e : Exception){
            return list
        }

        return list
    }

}
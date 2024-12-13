package com.project.projetoconvidados.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.project.projetoconvidados.constants.DataBaseConstants

//FORNECE A CONEXÃO COM O BANCO
class GuestDataBase(context : Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object{
        private const val NAME = "guestdb"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        //Criação do banco, uma única vez

        db.execSQL("CREATE TABLE " + DataBaseConstants.GUEST.TABLE_NAME + " (" +
               DataBaseConstants.COLUMS.ID + " integer primary key autoincrement," +
               DataBaseConstants.COLUMS.NAME +  " text," +
               DataBaseConstants.COLUMS.PRESENCE + " integer" +
                ")")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion == 1){
            if(newVersion == 2){
                //ATUALIZAÇÃO
            }
        }
    }
}
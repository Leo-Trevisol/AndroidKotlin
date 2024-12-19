package com.project.projetoconvidados.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.project.projetoconvidados.constants.DataBaseConstants
import com.project.projetoconvidados.model.GuestModel

@Database(entities = [GuestModel::class], version = 1)
//FORNECE A CONEXÃO COM O BANCO
abstract class GuestDataBase() : RoomDatabase() {

    //Singleton
    companion object{
        private lateinit var INSTANCE : GuestDataBase
        fun getDataBase(context: Context) : GuestDataBase{
            if(!::INSTANCE.isInitialized){
                synchronized(GuestDataBase::class){
                    INSTANCE = Room.databaseBuilder(context, GuestDataBase::class.java, "guestdb")
                        .addMigrations(MIGRATION_1_2)
                        .allowMainThreadQueries()
                        .build()

                }
            }
            return INSTANCE
        }

        private val MIGRATION_1_2 : Migration = object : Migration(1,2){
            override fun migrate(db: SupportSQLiteDatabase) {

            }
    }

    }


}

//class GuestDataBase(context : Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {
//
//    companion object{
//        private const val NAME = "guestdb"
//        private const val VERSION = 1
//    }
//
//    override fun onCreate(db: SQLiteDatabase) {
//        //Criação do banco, uma única vez
//
//        db.execSQL("CREATE TABLE " + DataBaseConstants.GUEST.TABLE_NAME + " (" +
//               DataBaseConstants.COLUMS.ID + " integer primary key autoincrement," +
//               DataBaseConstants.COLUMS.NAME +  " text," +
//               DataBaseConstants.COLUMS.PRESENCE + " integer" +
//                ")")
//    }
//
//    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        if(oldVersion == 1){
//            if(newVersion == 2){
//                //ATUALIZAÇÃO
//            }
//        }
//    }
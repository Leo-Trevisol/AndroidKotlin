package com.project.projetoconvidados.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.project.projetoconvidados.model.GuestModel

@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDatabase : RoomDatabase() {

    abstract fun guestDAO(): GuestDAO

    companion object {
        @Volatile
        private var INSTANCE: GuestDatabase? = null

        fun getDatabase(context: Context): GuestDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GuestDatabase::class.java,
                    "guestdb"
                )
                    .addMigrations(MIGRATION_1_2)
                    .allowMainThreadQueries() // Use isso apenas para testes!
                    .build()
                INSTANCE = instance
                instance
            }
        }

        /**
         * Atualização de versão de banco de dados
         */
        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DELETE FROM Guest")
            }
        }

    }
}
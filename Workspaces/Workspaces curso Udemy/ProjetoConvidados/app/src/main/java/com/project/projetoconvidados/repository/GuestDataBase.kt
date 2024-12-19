package com.project.projetoconvidados.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.project.projetoconvidados.constants.DataBaseConstants

// Classe GuestDataBase fornece a conexão com o banco de dados SQLite.
class GuestDataBase(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    // 'companion object' é usado para declarar constantes que são compartilhadas entre todas as instâncias da classe.
    companion object {
        private const val NAME = "guestdb"  // Nome do banco de dados
        private const val VERSION = 1       // Versão do banco de dados
    }

    // Método onCreate é chamado quando o banco de dados é criado pela primeira vez.
    // Aqui, criamos a tabela de convidados.
    override fun onCreate(db: SQLiteDatabase) {
        // Criação da tabela usando SQL
        db.execSQL("CREATE TABLE " + DataBaseConstants.GUEST.TABLE_NAME + " (" +
                DataBaseConstants.COLUMS.ID + " integer primary key autoincrement," +  // ID é a chave primária e autoincrementada
                DataBaseConstants.COLUMS.NAME + " text," +                             // Nome do convidado
                DataBaseConstants.COLUMS.PRESENCE + " integer" +                       // Presença do convidado (pode ser 0 ou 1)
                ")")
    }

    // Método onUpgrade é chamado quando a versão do banco de dados é atualizada.
    // Ele pode ser usado para modificar o banco de dados, como adicionar novas tabelas ou alterar estruturas existentes.
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion == 1) {
            if (newVersion == 2) {
                // Aqui seria o código de atualização, por exemplo, adicionar colunas ou novas tabelas.
            }
        }
    }
}

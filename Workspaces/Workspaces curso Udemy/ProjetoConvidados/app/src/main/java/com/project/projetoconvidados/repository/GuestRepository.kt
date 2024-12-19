package com.project.projetoconvidados.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.project.projetoconvidados.constants.DataBaseConstants
import com.project.projetoconvidados.model.GuestModel

// Classe GuestRepository: Responsável por manipular os dados do banco (CRUD) relacionados aos convidados.
class GuestRepository private constructor(context: Context){

    // Instância da classe GuestDataBase para fornecer conexão com o banco de dados
    private val guestDataBase = GuestDataBase(context)

    // Padrão Singleton: Garante que somente uma instância de GuestRepository seja criada.
    companion object {
        private lateinit var repository: GuestRepository

        // Função para obter a instância única do repositório
        fun getInstance(context: Context) : GuestRepository {
            if(!Companion::repository.isInitialized){
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    // Função para inserir um novo convidado no banco de dados
    fun insert(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase  // Obtém conexão com o banco de dados no modo de escrita

            // A presença do convidado é convertida para inteiro: 1 para presente, 0 para ausente
            val presence = if(guest.presence) 1 else 0

            // Adiciona os dados do convidado ao banco usando ContentValues
            val values = ContentValues()
            values.put(DataBaseConstants.COLUMS.NAME, guest.name)
            values.put(DataBaseConstants.COLUMS.PRESENCE, presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)  // Insere os dados na tabela Guest
            true  // Retorna true se a inserção for bem-sucedida
        } catch (e: Exception) {
            false  // Retorna false em caso de erro
        }
    }

    // Função para atualizar os dados de um convidado
    fun update(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase  // Conexão com o banco em modo de escrita

            val presence = if(guest.presence) 1 else 0  // Converte a presença para inteiro

            val values = ContentValues()
            values.put(DataBaseConstants.COLUMS.NAME, guest.name)  // Adiciona o nome atualizado
            values.put(DataBaseConstants.COLUMS.PRESENCE, presence)  // Adiciona a presença atualizada

            val selection = DataBaseConstants.COLUMS.ID + " = ?"  // Condição de seleção para atualizar pelo ID
            val args = arrayOf(guest.id.toString())  // Argumentos para a query

            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, selection, args)  // Atualiza o convidado
            true  // Retorna true se a atualização for bem-sucedida
        } catch (e: Exception) {
            false  // Retorna false em caso de erro
        }
    }

    // Função para deletar um convidado pelo ID
    fun delete(id: Int): Boolean {
        return try {
            val db = guestDataBase.writableDatabase  // Conexão com o banco de dados em modo de escrita

            val selection = DataBaseConstants.COLUMS.ID + " = ?"  // Condição de seleção para deletar pelo ID
            val args = arrayOf(id.toString())  // Argumentos para a query

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)  // Deleta o convidado
            true  // Retorna true se a exclusão for bem-sucedida
        } catch (e: Exception) {
            false  // Retorna false em caso de erro
        }
    }

    // Função para obter um convidado específico pelo ID
    @SuppressLint("Range")
    fun get(id: Int): GuestModel? {
        var guest: GuestModel? = null

        try {
            val db = guestDataBase.readableDatabase  // Conexão com o banco em modo de leitura

            val projection = arrayOf(  // Colunas que queremos recuperar
                DataBaseConstants.COLUMS.ID,
                DataBaseConstants.COLUMS.NAME,
                DataBaseConstants.COLUMS.PRESENCE
            )

            val selection = DataBaseConstants.COLUMS.ID + " = ?"  // Condição de seleção para pegar o convidado pelo ID
            val args = arrayOf(id.toString())  // Argumentos para a query

            val cursor = db.query(  // Executa a query no banco de dados
                DataBaseConstants.GUEST.TABLE_NAME, projection, selection, args, null, null, null
            )

            // Verifica se a consulta retornou algum resultado
            if(cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    // Recupera os dados do convidado
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.COLUMS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUMS.PRESENCE))

                    // Cria e retorna um objeto GuestModel
                    guest = GuestModel(id, name, presence == 1)
                }
            }
        } catch (e: Exception) {
            return guest  // Retorna o convidado ou null caso ocorra um erro
        }

        return guest
    }

    // Função para obter todos os convidados
    @SuppressLint("Range")
    fun getAll(): List<GuestModel> {
        val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase  // Conexão com o banco em modo de leitura

            val projection = arrayOf(  // Colunas que queremos recuperar
                DataBaseConstants.COLUMS.ID,
                DataBaseConstants.COLUMS.NAME,
                DataBaseConstants.COLUMS.PRESENCE
            )

            val cursor = db.query(  // Executa a query para pegar todos os convidados
                DataBaseConstants.GUEST.TABLE_NAME, projection, null, null, null, null, null
            )

            // Verifica se a consulta retornou algum resultado
            if(cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUMS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.COLUMS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUMS.PRESENCE))

                    // Adiciona cada convidado à lista
                    list.add(GuestModel(id, name, presence == 1))
                }
            }
        } catch (e: Exception) {
            return list  // Retorna a lista, mesmo que esteja vazia, em caso de erro
        }

        return list
    }

    // Função para obter uma lista de convidados presentes
    @SuppressLint("Range")
    fun getListPresent(): List<GuestModel> {
        val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase  // Conexão com o banco em modo de leitura

            val cursor = db.rawQuery(  // Query para buscar convidados com presença = 1 (presentes)
                "SELECT id, name, presence FROM Guest WHERE presence = 1", null
            )

            // Verifica se a consulta retornou algum resultado
            if(cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUMS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.COLUMS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUMS.PRESENCE))

                    // Adiciona os convidados presentes à lista
                    list.add(GuestModel(id, name, presence == 1))
                }
            }
        } catch (e: Exception) {
            return list  // Retorna a lista, mesmo que esteja vazia, em caso de erro
        }

        return list
    }

    // Função para obter uma lista de convidados ausentes
    @SuppressLint("Range")
    fun getListAbsent(): List<GuestModel> {
        val list = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase  // Conexão com o banco em modo de leitura

            val cursor = db.rawQuery(  // Query para buscar convidados com presença = 0 (ausentes)
                "SELECT id, name, presence FROM Guest WHERE presence = 0", null
            )

            // Verifica se a consulta retornou algum resultado
            if(cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUMS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.COLUMS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.COLUMS.PRESENCE))

                    // Adiciona os convidados ausentes à lista
                    list.add(GuestModel(id, name, presence == 1))
                }
            }
        } catch (e: Exception) {
            return list  // Retorna a lista, mesmo que esteja vazia, em caso de erro
        }

        return list
    }
}

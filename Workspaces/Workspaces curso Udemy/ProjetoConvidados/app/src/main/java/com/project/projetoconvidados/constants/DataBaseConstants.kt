package com.project.projetoconvidados.constants

// A classe DataBaseConstants serve como um container para as constantes do banco de dados.
class DataBaseConstants {

    // 'object' é usado para criar um singleton, que contém as constantes relacionadas à tabela de convidados.
    object GUEST {
        // Constantes para a tabela 'Guest' e o ID da tabela.
        const val TABLE_NAME = "Guest"   // Nome da tabela
        const val ID = "guestid"         // Nome da coluna de ID do convidado
    }

    // Outro 'object' para armazenar constantes relacionadas às colunas da tabela.
    object COLUMS {

        // Constantes para as colunas da tabela de convidados.
        const val ID = "id"              // Coluna para o ID do convidado
        const val NAME = "name"          // Coluna para o nome do convidado
        const val PRESENCE = "presence"  // Coluna para indicar presença do convidado
    }
}

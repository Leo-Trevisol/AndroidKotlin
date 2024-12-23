package com.project.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Classe responsável por configurar e gerenciar o Retrofit
class RetrofitClient private constructor() { // Construtor privado para evitar múltiplas instâncias

    companion object {

        // Instância singleton do Retrofit
        private lateinit var retrofit: Retrofit

        // URL base da API - Deve sempre terminar com "/" para evitar erros do Retrofit
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        // Método privado para criar ou retornar a instância do Retrofit
        private fun getRetrofitInstance(): Retrofit {
            // Builder do OkHttpClient para configuração de cliente HTTP
            val httpClient = OkHttpClient.Builder()

            // Verifica se a instância do Retrofit já foi inicializada
            if (!::retrofit.isInitialized) {
                // Cria a instância do Retrofit apenas uma vez (padrão Singleton)
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL) // Define a URL base para todas as requisições
                    .client(httpClient.build()) // Adiciona o cliente HTTP configurado
                    .addConverterFactory(GsonConverterFactory.create()) // Adiciona suporte para converter JSON usando Gson
                    .build()
            }
            return retrofit // Retorna a instância do Retrofit
        }

        // Método público para criar uma instância do serviço baseado na interface fornecida
        fun <S> createService(serviceClass: Class<S>): S {
            // Usa o Retrofit para criar uma implementação da interface do serviço
            return getRetrofitInstance().create(serviceClass)
        }
    }
}

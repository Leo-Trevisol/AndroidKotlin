package com.project.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Classe principal da atividade
class MainActivity : AppCompatActivity() {

    // Instância do serviço Retrofit para comunicação com a API
    private val remote = RetrofitClient.createService(PostService::class.java)

    // Método executado ao criar a atividade
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Realiza a chamada para obter a lista de posts
        val call: Call<List<PostModel>> = remote.list()

        // Executa a chamada de forma assíncrona
        call.enqueue(object : Callback<List<PostModel>> {

            // Callback chamado em caso de falha na requisição
            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                // Obtém a mensagem de erro e pode tratá-la (exemplo: exibir em um log)
                val fail = t.message
            }

            // Callback chamado em caso de sucesso na requisição
            override fun onResponse(call: Call<List<PostModel>>, rsp: Response<List<PostModel>>) {
                // Obtém o corpo da resposta (lista de posts)
                val list = rsp.body()
                // Aqui você pode tratar a lista de posts recebida
            }
        })
    }
}

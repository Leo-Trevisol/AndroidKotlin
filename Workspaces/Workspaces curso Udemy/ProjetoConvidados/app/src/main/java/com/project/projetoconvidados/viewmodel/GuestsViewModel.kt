package com.project.projetoconvidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.projetoconvidados.model.GuestModel
import com.project.projetoconvidados.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    // Instância do repositório que gerencia as operações de banco de dados
    private val repository = GuestRepository.getInstance(application)

    // MutableLiveData que mantém a lista de convidados
    private val listGuests = MutableLiveData<List<GuestModel>>()
    // LiveData que expõe a lista de convidados de forma imutável para a UI
    val guests: LiveData<List<GuestModel>> = listGuests

    // Função para recuperar todos os convidados (sem filtro)
    fun getAll() {
        // Atualiza o LiveData com a lista completa de convidados
        listGuests.value = repository.getAll()
    }

    // Função para recuperar apenas os convidados presentes
    fun getPresent() {
        // Atualiza o LiveData com a lista de convidados presentes
        listGuests.value = repository.getListPresent()
    }

    // Função para recuperar apenas os convidados ausentes
    fun getAbsent() {
        // Atualiza o LiveData com a lista de convidados ausentes
        listGuests.value = repository.getListAbsent()
    }

    // Função para deletar um convidado pelo id
    fun delete(id: Int) {
        // Chama o método delete do repositório para remover o convidado
        repository.delete(id)
    }
}

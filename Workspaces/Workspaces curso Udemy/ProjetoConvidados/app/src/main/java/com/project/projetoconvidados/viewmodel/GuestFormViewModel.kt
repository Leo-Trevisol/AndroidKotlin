package com.project.projetoconvidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.projetoconvidados.model.GuestModel
import com.project.projetoconvidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    // Instância do repositório que gerencia as operações no banco de dados
    private val repository = GuestRepository.getInstance(application)

    // MutableLiveData que mantém o modelo do convidado que será observado pela UI
    private val guestModel = MutableLiveData<GuestModel>()
    // LiveData que expõe o modelo de convidado de forma imutável para a UI
    val guest : LiveData<GuestModel> = guestModel

    // MutableLiveData para armazenar a mensagem de sucesso ou erro durante a inserção/atualização
    private val _saveGuest = MutableLiveData<String>()
    // LiveData que expõe a mensagem de resultado de inserção/atualização de forma imutável
    val saveGuest : LiveData<String> = _saveGuest

    // Função para salvar ou atualizar um convidado
    fun saveOrUpdate(guest : GuestModel){
        // Se o id do convidado for 0, significa que é uma inserção
        if(guest.id == 0){
            // Tenta inserir o convidado no banco de dados
            if(repository.insert(guest)){
                _saveGuest.value = "Inserção com sucesso!" // Mensagem de sucesso
            } else {
                _saveGuest.value = "Falha ao inserir!" // Mensagem de erro
            }
        } else {
            // Caso contrário, tenta atualizar o convidado existente
            if(repository.update(guest)){
                _saveGuest.value = "Atualização com sucesso!" // Mensagem de sucesso
            } else {
                _saveGuest.value = "Falha ao atualizar!" // Mensagem de erro
            }
        }
    }

    // Função para obter os dados de um convidado específico pelo id
    fun get(id : Int){
        // Recupera os dados do convidado e atualiza o LiveData
        guestModel.value = repository.get(id)
    }
}

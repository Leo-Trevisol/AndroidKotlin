package com.project.projetoconvidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.projetoconvidados.R
import com.project.projetoconvidados.constants.DataBaseConstants
import com.project.projetoconvidados.databinding.ActivityGuestFormBinding
import com.project.projetoconvidados.model.GuestModel
import com.project.projetoconvidados.viewmodel.GuestFormViewModel

// Activity para o formulário de convidado (edição e criação)
class GuestFormActivity : AppCompatActivity(), OnClickListener {

    lateinit var binding: ActivityGuestFormBinding  // Binding para acessar as views
    lateinit var viewModel: GuestFormViewModel  // ViewModel para gerenciar os dados do convidado

    private var guestId = 0  // ID do convidado (para edição ou criação)

    // Método chamado ao criar a Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o ViewBinding
        binding = ActivityGuestFormBinding.inflate(layoutInflater)

        // Configura a tela com o layout do binding
        setContentView(binding.root)

        // Criação do ViewModel associado à Activity
        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        // Configura o clique do botão de salvar
        binding.buttonSave.setOnClickListener(this)

        // Marca o convidado como presente por padrão
        binding.radioPresence.isChecked = true

        // Observa as mudanças nos dados do convidado e os carrega na interface
        observe()

        // Carrega os dados do convidado se for uma edição
        loadData()
    }

    // Ação executada quando o botão de salvar é clicado
    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            // Obtém os dados do formulário
            val name = binding.editName.text.toString()
            val presence = binding.radioPresence.isChecked

            // Cria ou atualiza o modelo de convidado
            val guest = GuestModel(guestId, name, presence)

            // Chama o ViewModel para salvar ou atualizar o convidado
            viewModel.saveOrUpdate(guest)

            // Finaliza a Activity (volta para a tela anterior)
            finish()
        }
    }

    // Carrega os dados do convidado, se houver um ID
    private fun loadData() {
        // Obtém o ID do convidado passado pela Intent
        val bundle = intent.extras

        // Se houver um ID de convidado, faz a consulta para carregá-lo
        if (bundle != null) {
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            viewModel.get(guestId)  // Solicita o convidado via ViewModel
        }
    }

    // Observa os dados do convidado e realiza ações na UI
    private fun observe() {
        // Observa o convidado para preencher o formulário de edição
        viewModel.guest.observe(this, Observer {
            binding.editName.setText(it.name)
            if (it.presence) {
                binding.radioPresence.isChecked = true
            } else {
                binding.radioAbsent.isChecked = true
            }
        })

        // Observa o resultado da operação de salvar/atualizar
        viewModel.saveGuest.observe(this, Observer {
            if (it != "") {
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                finish()  // Fecha a Activity após salvar
            }
        })
    }
}

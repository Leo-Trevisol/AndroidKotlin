package com.project.projetoconvidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.projetoconvidados.constants.DataBaseConstants
import com.project.projetoconvidados.databinding.FragmentAbsentBinding
import com.project.projetoconvidados.listener.OnGuestListener
import com.project.projetoconvidados.view.adapter.GuestsAdapter
import com.project.projetoconvidados.viewmodel.GuestsViewModel

// Fragmento que exibe os convidados ausentes
class AbsentFragment : Fragment() {

    private var _binding: FragmentAbsentBinding? = null
    private val binding get() = _binding!!

    // Instância do ViewModel para gerenciar os dados
    private lateinit var viewModel: GuestsViewModel

    // Adapter que conecta os dados da lista com a RecyclerView
    private val adapter = GuestsAdapter()

    // Criação da interface do fragmento
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        // Inicializa o ViewModel associado a este Fragmento
        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)

        // Infla o layout do Fragmento
        _binding = FragmentAbsentBinding.inflate(inflater, container, false)

        // Configura o LayoutManager para a RecyclerView
        binding.recyclerGuests.layoutManager = LinearLayoutManager(context)

        // Configura o Adapter para a RecyclerView
        binding.recyclerGuests.adapter = adapter

        // Listener para capturar as ações do usuário (cliques e deletações)
        val listener = object : OnGuestListener {
            // Ação de clique no convidado: abre a tela de edição de convidado
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)  // Passa o ID do convidado
                intent.putExtras(bundle)
                startActivity(intent)  // Inicia a atividade de edição de convidado
            }

            // Ação de deletação de convidado
            override fun onDelete(id: Int) {
                viewModel.delete(id)  // Deleta o convidado através do ViewModel
                viewModel.getAbsent()  // Atualiza a lista de convidados ausentes
            }
        }

        // Anexa o listener ao adapter para tratar as ações do usuário
        adapter.attachListener(listener)

        // Carrega os convidados ausentes
        viewModel.getAbsent()

        // Observa as mudanças nos dados dos convidados
        observe()

        return binding.root
    }

    // Libera a referência do binding quando a View é destruída
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Quando o fragmento retorna ao primeiro plano, recarrega a lista de convidados ausentes
    override fun onResume() {
        super.onResume()
        viewModel.getAbsent()  // Atualiza a lista de convidados ausentes
    }

    // Observa as mudanças nos dados dos convidados e atualiza a interface
    private fun observe() {
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)  // Atualiza a lista no adapter com os novos dados
        }
    }
}

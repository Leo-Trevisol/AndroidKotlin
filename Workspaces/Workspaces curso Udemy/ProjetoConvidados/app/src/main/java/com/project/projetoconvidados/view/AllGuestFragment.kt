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
import com.project.projetoconvidados.databinding.FragmentAllGuestsBinding
import com.project.projetoconvidados.listener.OnGuestListener
import com.project.projetoconvidados.view.adapter.GuestsAdapter
import com.project.projetoconvidados.viewmodel.GuestsViewModel

// Fragmento que exibe todos os convidados
class AllGuestFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null

    // ViewModel que gerencia os dados dos convidados
    private lateinit var viewModel: GuestsViewModel

    // Adapter para a RecyclerView
    private val adapter = GuestsAdapter()

    // Referência de binding, válida entre onCreateView e onDestroyView
    private val binding get() = _binding!!

    // Criação da interface do fragmento
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        // Inicializa o ViewModel associado a este Fragmento
        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)

        // Infla o layout do Fragmento
        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        // Configura o LayoutManager para a RecyclerView (exibição linear)
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
                viewModel.getAll()  // Atualiza a lista de todos os convidados
            }
        }

        // Anexa o listener ao adapter para tratar as ações do usuário
        adapter.attachListener(listener)

        // Carrega todos os convidados
        viewModel.getAll()

        // Observa as mudanças nos dados dos convidados
        observe()

        return binding.root
    }

    // Quando o fragmento retorna ao primeiro plano, recarrega a lista de todos os convidados
    override fun onResume() {
        super.onResume()
        viewModel.getAll()  // Atualiza a lista de todos os convidados
    }

    // Libera a referência do binding quando a View é destruída
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Observa as mudanças nos dados dos convidados e atualiza a interface
    private fun observe() {
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)  // Atualiza a lista no adapter com os novos dados
        }
    }
}

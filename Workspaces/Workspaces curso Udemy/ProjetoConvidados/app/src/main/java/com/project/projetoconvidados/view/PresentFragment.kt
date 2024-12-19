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
import com.project.projetoconvidados.databinding.FragmentPresentBinding
import com.project.projetoconvidados.listener.OnGuestListener
import com.project.projetoconvidados.view.adapter.GuestsAdapter
import com.project.projetoconvidados.viewmodel.GuestsViewModel

class PresentFragment : Fragment() {

    // Variável para o binding, que conecta as views do layout com o código
    private var _binding: FragmentPresentBinding? = null
    // Property que garante que o binding será seguro de ser utilizado
    private val binding get() = _binding!!

    // Declaração da variável do ViewModel que gerencia a lógica dos dados
    private lateinit var viewModel : GuestsViewModel

    // Criação do adaptador que será utilizado para exibir a lista de convidados
    private val adapter = GuestsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        // Obtendo o ViewModel para o gerenciamento dos dados dos convidados
        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)

        // Inflando o layout específico para este Fragment
        _binding = FragmentPresentBinding.inflate(inflater, container, false)

        // Configuração do RecyclerView com um LinearLayoutManager para listar os convidados
        binding.recyclerGuests.layoutManager = LinearLayoutManager(context)

        // Definindo o adaptador para o RecyclerView
        binding.recyclerGuests.adapter = adapter

        // Criando o listener para os eventos de clique e exclusão nos itens
        val listener = object : OnGuestListener {
            // Ação quando um item é clicado, que abre o formulário de edição do convidado
            override fun onClick(id : Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id) // Passando o ID do convidado para a próxima tela
                intent.putExtras(bundle)
                startActivity(intent) // Iniciando a Activity de edição
            }

            // Ação quando um convidado é deletado
            override fun onDelete(id : Int) {
                viewModel.delete(id) // Deleta o convidado
                viewModel.getPresent() // Atualiza a lista de convidados presentes
            }
        }

        // Associando o listener ao adaptador
        adapter.attachListener(listener)

        // Carrega os convidados presentes
        viewModel.getPresent()

        // Observa as mudanças na lista de convidados e atualiza a UI
        observe()

        // Retorna o root view do binding para ser exibido
        return binding.root
    }

    // Método chamado quando a view é destruída, garantindo que o binding seja limpo
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Método chamado quando o fragmento é retomado, garantindo que a lista de convidados presentes seja recarregada
    override fun onResume() {
        super.onResume()
        viewModel.getPresent() // Atualiza a lista de convidados presentes
    }

    // Função para observar as mudanças nos dados do ViewModel
    private fun observe() {
        // Observando as mudanças na lista de convidados presentes
        viewModel.guests.observe(viewLifecycleOwner) {
            // Atualizando a lista de convidados no adaptador
            adapter.updateGuests(it)
        }
    }
}

package com.project.projetoconvidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.projetoconvidados.databinding.RowGuestBinding
import com.project.projetoconvidados.listener.OnGuestListener
import com.project.projetoconvidados.model.GuestModel
import com.project.projetoconvidados.view.viewholder.GuestsViewHolder

// Adapter para gerenciar os dados dos convidados na RecyclerView
class GuestsAdapter : RecyclerView.Adapter<GuestsViewHolder>() {

    // Lista de convidados que será exibida na RecyclerView
    private var guestList: List<GuestModel> = listOf()

    // Listener para detectar interações com os itens da lista
    private lateinit var listener: OnGuestListener

    // Cria a ViewHolder para cada item da lista, inflando o layout da linha
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestsViewHolder {
        // Infla o layout da linha para cada item da RecyclerView
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestsViewHolder(item, listener)  // Cria a ViewHolder passando o binding e o listener
    }

    // Vincula os dados do convidado à ViewHolder para exibição na RecyclerView
    override fun onBindViewHolder(holder: GuestsViewHolder, position: Int) {
        // Chama o método bind() da ViewHolder para vincular os dados do convidado
        holder.bind(guestList[position])
    }

    // Retorna a quantidade de itens na lista
    override fun getItemCount(): Int {
        return guestList.count()  // Retorna o número de convidados na lista
    }

    // Atualiza a lista de convidados com uma nova lista e notifica a RecyclerView para atualizar a exibição
    fun updateGuests(guests: List<GuestModel>) {
        guestList = guests  // Substitui a lista de convidados
        notifyDataSetChanged()  // Notifica que os dados da RecyclerView foram atualizados
    }

    // Configura o listener para interações com os itens da lista
    fun attachListener(guestListener: OnGuestListener) {
        listener = guestListener  // Define o listener para as interações com os itens da lista
    }
}

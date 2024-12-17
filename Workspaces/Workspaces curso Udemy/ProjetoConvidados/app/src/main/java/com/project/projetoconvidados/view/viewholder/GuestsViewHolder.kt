package com.project.projetoconvidados.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.project.projetoconvidados.databinding.RowGuestBinding
import com.project.projetoconvidados.model.GuestModel

class GuestsViewHolder(private val bind: RowGuestBinding) : RecyclerView.ViewHolder(bind.root) {

    fun bind(guest : GuestModel){
        bind.textName.text = guest.name
    }
}
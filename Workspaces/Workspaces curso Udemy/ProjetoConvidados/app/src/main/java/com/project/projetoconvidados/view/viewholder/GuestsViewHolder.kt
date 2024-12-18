package com.project.projetoconvidados.view.viewholder

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.project.projetoconvidados.databinding.RowGuestBinding
import com.project.projetoconvidados.listener.OnGuestListener
import com.project.projetoconvidados.model.GuestModel

class GuestsViewHolder(private val bind: RowGuestBinding, private val listener : OnGuestListener) : RecyclerView.ViewHolder(bind.root) {

    fun bind(guest : GuestModel){
        bind.textName.text = guest.name

        bind.textName.setOnClickListener(View.OnClickListener {
            listener.onClick(guest.id)
        })


        bind.textName.setOnLongClickListener(View.OnLongClickListener {

            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado")
                .setMessage("Certeza que deseja remover?")
                .setPositiveButton("Sim"){dialog, witch ->
                    listener.onDelete(guest.id)
                }
                .setNegativeButton("Não", null)
                .create()
                .show()

            true
        })
    }
}
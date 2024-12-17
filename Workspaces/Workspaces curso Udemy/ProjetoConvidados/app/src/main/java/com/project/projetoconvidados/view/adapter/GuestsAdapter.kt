package com.project.projetoconvidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.projetoconvidados.databinding.RowGuestBinding
import com.project.projetoconvidados.model.GuestModel
import com.project.projetoconvidados.view.viewholder.GuestsViewHolder

class GuestsAdapter : RecyclerView.Adapter<GuestsViewHolder>() {


    private var guestList : List<GuestModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestsViewHolder {
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestsViewHolder(item)
    }

    override fun onBindViewHolder(holder: GuestsViewHolder, position: Int) {
        holder.bind(guestList[position])
    }

    override fun getItemCount(): Int {
        return guestList.count()
    }

    fun updateGuests(guests : List<GuestModel>){
        guestList = guests
        notifyDataSetChanged()
    }
}
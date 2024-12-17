package com.project.projetoconvidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.projetoconvidados.databinding.FragmentAllGuestsBinding
import com.project.projetoconvidados.view.adapter.GuestsAdapter
import com.project.projetoconvidados.viewmodel.AllGuestsViewModel

class AllGuestFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null

    private lateinit var viewModel : AllGuestsViewModel

    private val adapter = GuestsAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,b: Bundle?): View {
         viewModel = ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        //Layout
        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)

        //Adapter
        binding.recyclerAllGuests.adapter = adapter


        viewModel.getAll()

        observe()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe(){

        viewModel.guests.observe(viewLifecycleOwner){
            adapter.updateGuests(it)
        }

    }
}
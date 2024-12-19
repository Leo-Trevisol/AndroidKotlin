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

class AllGuestFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null

    private lateinit var viewModel : GuestsViewModel

    private val adapter = GuestsAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,b: Bundle?): View {
         viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)

        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        //Layout
        binding.recyclerGuests.layoutManager = LinearLayoutManager(context)

        //Adapter
        binding.recyclerGuests.adapter = adapter

        val listener = object : OnGuestListener{
            override fun onClick(id : Int) {
               val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id : Int) {
                viewModel.delete(id)
                viewModel.getAll()
            }
        }

        adapter.attachListener(listener)


        viewModel.getAll()

        observe()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
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
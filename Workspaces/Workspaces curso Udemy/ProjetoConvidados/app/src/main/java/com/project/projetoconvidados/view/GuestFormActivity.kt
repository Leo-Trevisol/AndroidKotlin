package com.project.projetoconvidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.lifecycle.ViewModelProvider
import com.project.projetoconvidados.R
import com.project.projetoconvidados.constants.DataBaseConstants
import com.project.projetoconvidados.databinding.ActivityGuestFormBinding
import com.project.projetoconvidados.model.GuestModel
import com.project.projetoconvidados.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), OnClickListener {

    lateinit var binding : ActivityGuestFormBinding
    lateinit var viewModel : GuestFormViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.buttonSave.setOnClickListener(this)
        binding.radioPresence.isChecked = true

        loadData()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.button_save){
            val name = binding.editName.text.toString()
            val presence = binding.radioPresence.isChecked

            val guest = GuestModel(0, name, presence)

            viewModel.insert(guest)
        }
    }

    private fun loadData(){
        val bundle = intent.extras

        if(bundle != null){
            val guestid = bundle.getInt(DataBaseConstants.GUEST.ID)

            viewModel.get(guestid)
        }
    }
}
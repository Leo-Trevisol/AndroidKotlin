package com.project.projetoconvidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.lifecycle.ViewModelProvider
import com.project.projetoconvidados.R
import com.project.projetoconvidados.databinding.ActivityGuestFormBinding
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

    }

    override fun onClick(v: View) {
        if(v.id == R.id.button_save){

        }
    }
}
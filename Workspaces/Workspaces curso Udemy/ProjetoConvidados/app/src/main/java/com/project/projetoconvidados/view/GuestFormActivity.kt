package com.project.projetoconvidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.projetoconvidados.R
import com.project.projetoconvidados.constants.DataBaseConstants
import com.project.projetoconvidados.databinding.ActivityGuestFormBinding
import com.project.projetoconvidados.model.GuestModel
import com.project.projetoconvidados.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), OnClickListener {

    lateinit var binding : ActivityGuestFormBinding
    lateinit var viewModel : GuestFormViewModel

    private var guestId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.buttonSave.setOnClickListener(this)
        binding.radioPresence.isChecked = true

        observe()
        loadData()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.button_save){
            val name = binding.editName.text.toString()
            val presence = binding.radioPresence.isChecked

            val guest = GuestModel().apply {
                this.id = guestId
                this.name = name
                this.presence = presence
            }

            viewModel.saveOrUpdate(guest)

            finish()
        }
    }

    private fun loadData(){
        val bundle = intent.extras

        if(bundle != null){
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            viewModel.get(guestId)
        }
    }

    private fun observe(){
        viewModel.guest.observe(this, Observer{
            binding.editName.setText(it.name)
            if(it.presence){
                binding.radioPresence.isChecked = true
            }else{
                binding.radioAbsent.isChecked = true
            }
        })

        viewModel.saveGuest.observe(this, Observer{
            if(it != ""){
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }
}
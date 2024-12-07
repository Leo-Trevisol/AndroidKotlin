package com.project.modelomvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.modelomvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.buttonLogin.setOnClickListener(this)

        setObserver()

    }

    override fun onClick(view: View) {
        if(view.id == R.id.buttonLogin){
            val email = binding.editEmail.text.toString()
            val senha = binding.editPassword.text.toString()
            viewModel.doLogin(email, senha)
        }
    }

    private fun setObserver(){
        viewModel.welcome().observe(this, Observer {
            binding.textWelcome.text = it
        })
        viewModel.login().observe(this, Observer {
            if(it){
                Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Falha", Toast.LENGTH_SHORT).show()
            }
        })
    }


}
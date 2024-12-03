package com.project.projetomotivation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.project.projetomotivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.hide()

        handleUserName()

        binding.buttonNewPhrase.setOnClickListener(this)


    }

    override fun onClick(view: View) {
        if(R.id.button_new_phrase == view.id){

        }

    }

    private fun handleUserName(){
        val name = SecurityPreferences(this).getString("USER_NAME")

        binding.textUserName.text = "Olá $name"

    }

}
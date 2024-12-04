package com.project.projetomotivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.project.projetomotivation.MotivationConstraints
import com.project.projetomotivation.R
import com.project.projetomotivation.SecurityPreferences
import com.project.projetomotivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.buttonSave.setOnClickListener(this)

        verifyUserName()
    }

    override fun onClick(view: View) {
       if(view.id == R.id.button_save){
            handeleSave()
       }
    }

    private fun verifyUserName(){
        val name = SecurityPreferences(this).getString(MotivationConstraints.KEY.USER_NAME)

        if(name != ""){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun handeleSave(){
        val name = binding.editName.text.toString()

        SecurityPreferences(this).storeString(MotivationConstraints.KEY.USER_NAME, name)

        if(name != ""){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            Toast.makeText(this,"Preencha o campo", Toast.LENGTH_LONG ).show()
        }
    }
}
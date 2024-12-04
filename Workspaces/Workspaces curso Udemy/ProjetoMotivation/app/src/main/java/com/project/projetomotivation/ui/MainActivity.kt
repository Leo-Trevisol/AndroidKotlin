package com.project.projetomotivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.devmasterteam.motivation.repository.Mock
import com.project.projetomotivation.MotivationConstraints
import com.project.projetomotivation.R
import com.project.projetomotivation.SecurityPreferences
import com.project.projetomotivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding

    private var categoryId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.hide()

        handleUserName()

        //Clicks
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)

        handleFilter(R.id.image_all)
        handleNextPhrase()

    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_new_phrase){
            handleNextPhrase()
        }else if(view.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)){
            handleFilter(view.id)
        }
    }

    private fun handleFilter(id : Int){

        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstraints.PHRASEFILTER.ALL
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstraints.PHRASEFILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstraints.PHRASEFILTER.SUNNY
            }
        }

    }

    private fun handleUserName(){
        val name = SecurityPreferences(this).getString(MotivationConstraints.KEY.USER_NAME)

        binding.textUserName.text = "Olá $name"

    }

    private fun handleNextPhrase(){
        val phrase = Mock().getPhrase(categoryId)
        binding.textPhrase.text = phrase
    }

}
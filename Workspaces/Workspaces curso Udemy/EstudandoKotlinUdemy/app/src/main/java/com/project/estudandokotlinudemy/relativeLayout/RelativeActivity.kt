package com.devmasterteam.relativelayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.estudandokotlinudemy.R

// Sem lógica, somente layout
class RelativeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relative)
    }
}
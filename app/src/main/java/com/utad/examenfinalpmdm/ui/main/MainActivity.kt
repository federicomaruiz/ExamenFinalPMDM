package com.utad.examenfinalpmdm.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.utad.examenfinalpmdm.R
import com.utad.examenfinalpmdm.databinding.ActivityMainBinding
import com.utad.examenfinalpmdm.ui.home.HomeActivity

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val binding: ActivityMainBinding get() = _binding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAcess.setOnClickListener {
            saveUser()
        }

    }

    // Función que va a llamar al viewModel para almacenar el dato ingresado
    private fun saveUser() {
        val name = binding.etName.text.toString().trim()
        if (!name.isNullOrEmpty()) {
            viewModel.saveUserName(this, name)
            goToHome()
        } else {
            Toast.makeText(this, "Introduce un nombre de un departamento", Toast.LENGTH_SHORT)
                .show()
        }
    }

    // Navego a la siguiente activity
    fun goToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
package com.utad.examenfinalpmdm.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.utad.examenfinalpmdm.databinding.ActivityHomeBinding
import android.widget.Toast


class HomeActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityHomeBinding
    private val binding: ActivityHomeBinding get() = _binding

    private val viewModel: HomeViewModel by viewModels()

    // Aqui pasaria funciones por parametro si necesito
    private val adapter = HomeAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding =
            ActivityHomeBinding.inflate(layoutInflater) // Inflar el layout y asignarlo a _binding
        setContentView(binding.root)

        setRecyclerView()
        // Llamamos a la funcion para recibir el nombre de usuario almacenado en local
        viewModel.getUserName(this)
        oberserverViewModel()
    }

    // Seteo la recycler view
    private fun setRecyclerView() {
        binding.rvTask.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvTask.adapter = adapter
    }

    // Funcion que va a estar observando el cambio de estado
    private fun oberserverViewModel() {

        viewModel.userName.observe(this) { userName ->
            if (userName != null) {
                viewModel.getHomeWork(userName)
            }
        }

        viewModel.uiState.observe(this) { uiState ->
            if (uiState.schoolList != null) {
                adapter.submitList(uiState.schoolList)
            }
            if (uiState.isLoading) {
                binding.pbLoading.visibility = View.VISIBLE
            } else {
                binding.pbLoading.visibility = View.INVISIBLE
            }
            if (uiState.isError) {
                Toast.makeText(this, "Ha ocurrido un error.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
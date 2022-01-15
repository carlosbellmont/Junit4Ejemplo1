package com.example.junit4ejjemplo.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.junit4ejjemplo.R
import com.example.junit4ejjemplo.databinding.MainFragmentBinding
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)
        binding.bLogIn.setOnClickListener {
            // Lo ideal sería hacer esto utilizando una variable con liveData que contenta el texto que se debe mostrar.
            if (!viewModel.isEmailValid(binding.etEmail.text.toString())){
                Snackbar.make(binding.root, getString(R.string.snack_wrong_email), Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (!viewModel.isPasswordValid(binding.etPass.text.toString())){
                Snackbar.make(binding.root, getString(R.string.snack_wrong_pass), Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            Snackbar.make(binding.root, getString(R.string.snack_logged), Snackbar.LENGTH_LONG).show()
        }
        binding.etEmail.doAfterTextChanged {
            binding.bLogIn.isEnabled = binding.etEmail.text.isNotEmpty() && binding.etPass.text.isNotEmpty()
        }
        binding.etPass.doAfterTextChanged {
            binding.bLogIn.isEnabled = binding.etEmail.text.isNotEmpty() && binding.etPass.text.isNotEmpty()
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }


}
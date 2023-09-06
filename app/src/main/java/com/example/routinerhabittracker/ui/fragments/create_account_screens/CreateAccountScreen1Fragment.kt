package com.example.routinerhabittracker.ui.fragments.create_account_screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.routinerhabittracker.R
import com.example.routinerhabittracker.databinding.FragmentCreateAccountScreen1Binding
import com.example.routinerhabittracker.databinding.FragmentSignInBinding


class CreateAccountScreen1Fragment : Fragment() {
    private  var _binding: FragmentCreateAccountScreen1Binding?=null
    private val binding get()= _binding!!
    private lateinit var navControler: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navControler = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateAccountScreen1Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.next1.setOnClickListener {
            val action = CreateAccountScreen1FragmentDirections.actionCreateAccountScreen1FragmentToCreateAccountScreen2Fragment()
            navControler.navigate(action)
        }
    }

}
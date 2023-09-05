package com.example.routinerhabittracker.ui.fragments.login_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.routinerhabittracker.R
import com.example.routinerhabittracker.databinding.FragmentSignInBinding
import com.example.routinerhabittracker.databinding.FragmentViewPagerSplashBinding
import kotlinx.coroutines.flow.combine

class SignInFragment : Fragment() {
    private  var _binding: FragmentSignInBinding?=null
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
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.letsCreate.setOnClickListener{
            val action = SignInFragmentDirections.actionSignInFragmentToCreateAccountScreen1Fragment()
            navControler.navigate(action)
        }

    }


}
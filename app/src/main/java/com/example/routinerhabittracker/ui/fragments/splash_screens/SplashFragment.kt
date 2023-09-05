package com.example.routinerhabittracker.ui.fragments.splash_screens

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.routinerhabittracker.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {
    private  var _binding: FragmentSplashBinding?=null
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
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({
                              val action =com.example.routinerhabittracker.ui.fragments.splash_screens.SplashFragmentDirections.actionSplashFragmentToViewPagerSplashFragment()


            navControler.navigate(action)
        } , 9000)
    }

}
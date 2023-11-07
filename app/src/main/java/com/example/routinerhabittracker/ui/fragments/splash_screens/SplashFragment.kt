package com.example.routinerhabittracker.ui.fragments.splash_screens

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.routinerhabittracker.R
import com.example.routinerhabittracker.databinding.FragmentSplashBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private  var _binding: FragmentSplashBinding?=null
    private val binding get()= _binding!!
    private lateinit var navControler: NavController
    private lateinit var auth:FirebaseAuth
     var isLogin:Boolean ?= null

    private val sharedPreferences:SharedPreferences ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
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
        isLogin = sharedPreferences?.getBoolean("isLogin" , false)
        Handler().postDelayed({
            if(isLogin == true){
                val actionToHome = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
                navControler.navigate(actionToHome)
            }else{
                val actionToSplash1 =com.example.routinerhabittracker.ui.fragments.splash_screens.SplashFragmentDirections.actionSplashFragmentToViewPagerSplashFragment()
                navControler.navigate(actionToSplash1 , NavOptions.Builder().setPopUpTo( R.id.splashFragment, true).build())
            }
        } , 5000)
    }

//    override fun onStart() {
//        super.onStart()
//        if(auth.currentUser == null){
//            val action = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
//            navControler.navigate(action)
////            navControler.popBackStack(null , true)
//        }
//    }

}
package com.example.routinerhabittracker.ui.fragments.login_screen

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.routinerhabittracker.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {
    private  var _binding: FragmentSignInBinding?=null
    private val binding get()= _binding!!
    private lateinit var navControler: NavController
    private  val auth:FirebaseAuth?=null

    private val sharedPreferences:SharedPreferences ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        auth = Firebase.auth
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



        binding.loginBtn.setOnClickListener {
            val email = binding.emailField.editText?.text.toString()
            val password = binding.passwordField.editText?.text.toString()
            if(email.isNotEmpty() && password.isNotEmpty()){
            auth!!.signInWithEmailAndPassword(email , password)
                .addOnCompleteListener {
                    if(it.isSuccessful ){
                        verifiedEmailAddress()
                    }else{
                        Toast.makeText(activity , "please check from your data" , Toast.LENGTH_LONG).show()
                    }
                }

            }else{
                Toast.makeText(activity , "please enter your data " , Toast.LENGTH_LONG).show()
            }
        }



    }

    private fun verifiedEmailAddress(){
        val user = auth!!.currentUser
        if (user != null) {
            if(user.isEmailVerified){
                val editor = sharedPreferences?.edit()
                editor?.putBoolean("isLogin" , true )
                editor?.apply()
                Toast.makeText(activity , "login successful  " , Toast.LENGTH_LONG).show()
                val action = SignInFragmentDirections.actionSignInFragmentToCreateAccountScreen2Fragment()
                navControler.navigate(action)
            }else{
                Toast.makeText(activity , "please verified your email...  " , Toast.LENGTH_LONG).show()
            }
        }
    }


}
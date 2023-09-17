package com.example.routinerhabittracker.ui.fragments.splash_screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.routinerhabittracker.R
import com.example.routinerhabittracker.adapter.ViewPagerAdapter
import com.example.routinerhabittracker.databinding.FragmentViewPagerSplashBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
class ViewPagerSplashFragment : Fragment() {
    private  var _binding: FragmentViewPagerSplashBinding?=null
    private val binding get()= _binding!!
    private lateinit var navControler: NavController

    companion object {
        private const val RC_SIGN_IN = 9001
    }

    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        navControler = findNavController()

        val currentUser = auth.currentUser

        if (currentUser != null) {
            val action = ViewPagerSplashFragmentDirections.actionViewPagerSplashFragmentToHomeFragment()
            navControler.navigate(action)
        }

    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentViewPagerSplashBinding.inflate(inflater, container, false)
        val view = binding.root




        val fragmentList = arrayListOf<Fragment>(
                SplashPager1Fragment(),
                SplashPager2Fragment(),
                SplashPager3Fragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        binding.splashViewPager.adapter = adapter


        binding.emailBtn.setOnClickListener{
            val action = ViewPagerSplashFragmentDirections.actionViewPagerSplashFragmentToSignInFragment()
            navControler.navigate(action)
        }
        binding.googleBtn.setOnClickListener {
            signIn()
        }

        binding.facebookBtn.setOnClickListener {

        }

        return view
    }


    private fun signIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }



    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Toast.makeText(activity, "Google sign in failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(activity, "Signed in as ${user?.displayName}", Toast.LENGTH_SHORT).show()
                    val action = ViewPagerSplashFragmentDirections.actionViewPagerSplashFragmentToHomeFragment()
                    navControler.navigate(action)
                } else {
                    Toast.makeText(activity, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
            }
    }



}
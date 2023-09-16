package com.example.routinerhabittracker.ui.fragments.splash_screens


import android.app.Activity
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
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ViewPagerSplashFragment : Fragment() {
    private  var _binding: FragmentViewPagerSplashBinding?=null
    private val binding get()= _binding!!
    private lateinit var navControler: NavController
    private lateinit var googleSignInClient:GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result->
        if(result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResult(task)
        }
    }

    private fun handleResult(task: Task<GoogleSignInAccount>) {
        if(task.isSuccessful){
            val account : GoogleSignInAccount = task.result
            if(account != null){
                updateUI(account)
            }
        }else{
            Toast.makeText(activity , "Sign in failed try again later" , Toast.LENGTH_LONG).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        //progressbar
        val credential = GoogleAuthProvider.getCredential(account.idToken , null)
        auth.signInWithCredential(credential).addOnCompleteListener{
            if(it.isSuccessful){
//                val action = ViewPagerSplashFragmentDirections.actionViewPagerSplashFragmentToHomeFragment()
                Toast.makeText(activity , "success to sign in with google" , Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(activity , "failed to sign in with google" , Toast.LENGTH_LONG).show()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        navControler = findNavController()
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

        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity() , gso)

        binding.emailBtn.setOnClickListener{
            val action = ViewPagerSplashFragmentDirections.actionViewPagerSplashFragmentToSignInFragment()
            navControler.navigate(action)
        }
        binding.googleBtn.setOnClickListener {
            signInWithGoogle()
        }

        binding.facebookBtn.setOnClickListener {

        }

        return view
    }

    private fun signInWithGoogle(){
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }


}
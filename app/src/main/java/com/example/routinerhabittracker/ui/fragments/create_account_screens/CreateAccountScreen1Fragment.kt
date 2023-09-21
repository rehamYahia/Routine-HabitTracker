package com.example.routinerhabittracker.ui.fragments.create_account_screens

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.routinerhabittracker.databinding.FragmentCreateAccountScreen1Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*


class CreateAccountScreen1Fragment : Fragment() {
    private  var _binding: FragmentCreateAccountScreen1Binding?=null
    private val binding get()= _binding!!
    private lateinit var navControler: NavController
    private val calendar = Calendar.getInstance()
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        navControler = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateAccountScreen1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.btnBirthDate.setOnClickListener {
            openDialog()

        }

        binding.next1.setOnClickListener {

            val email:String = binding.registerEmailField.editText?.text.toString()
            val password:String = binding.registerPassField.editText?.text.toString()
            val name:String = binding.registerNameField.editText?.text.toString()
            val birthDate:String = binding.birthDate.text.toString()
            if(email.trim().isNotEmpty() && password.trim().isNotEmpty() &&
                name.trim().isNotEmpty() && birthDate.trim().isNotEmpty()){
                binding.registerProgress.visibility = View.VISIBLE
                auth.createUserWithEmailAndPassword(email , password )
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            binding.registerProgress.visibility = View.INVISIBLE
                            sendEmailVerification()
//                            contentToRealTime(name , email , password , birthDate)
                        }else{
                            Log.d("reham1" , it.exception.toString())
                            binding.error.text = it.exception.toString()
                            binding.registerProgress.visibility = View.INVISIBLE
                            Toast.makeText(activity ,it.exception.toString() , Toast.LENGTH_LONG).show()
                        }
                    }
            }else{
                Toast.makeText(activity , "Please complete your information " , Toast.LENGTH_LONG).show()
            }


        }
    }




    private fun openDialog(){
        val dialog : DatePickerDialog = DatePickerDialog(requireContext(),{
            DatePicker , year:Int , monthOfYear:Int , dayOfYear:Int ->
            val selectedData = Calendar.getInstance()
            selectedData.set(year , monthOfYear , dayOfYear)
            val dateFormate = SimpleDateFormat("dd/mm/yyy" , Locale.getDefault())
            val formateData = dateFormate.format(selectedData.time)
            binding.birthDate.text = "Birth date:"+ formateData

        },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)

        )
        dialog.show()
    }

    fun contentToRealTime( name:String , email:String , password:String , birthDate:String ){

        val database = Firebase.database
        val myRef = database.getReference()

        myRef.child("name").setValue(name)
        myRef.child("email").setValue(email)
        myRef.child("password").setValue(password)
        myRef.child("birthDate").setValue(birthDate)

    }

    private fun sendEmailVerification(){
        val user = auth.currentUser
        user?.sendEmailVerification()?.addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(activity, "we send email massage to verify your account" , Toast.LENGTH_LONG).show()
                val action = CreateAccountScreen1FragmentDirections.actionCreateAccountScreen1FragmentToSignInFragment()
                navControler.navigate(action)
            }else{
                Toast.makeText(activity, it.exception.toString() , Toast.LENGTH_LONG).show()
            }
        }
    }

}
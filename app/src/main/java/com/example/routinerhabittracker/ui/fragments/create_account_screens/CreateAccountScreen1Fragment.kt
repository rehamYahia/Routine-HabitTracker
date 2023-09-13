package com.example.routinerhabittracker.ui.fragments.create_account_screens

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.routinerhabittracker.databinding.FragmentCreateAccountScreen1Binding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*


class CreateAccountScreen1Fragment : Fragment() {
    private  var _binding: FragmentCreateAccountScreen1Binding?=null
    private val binding get()= _binding!!
    private lateinit var navControler: NavController
    private val calendar = Calendar.getInstance()
    private lateinit var firebaseAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
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
                firebaseAuth.createUserWithEmailAndPassword(email , password )
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            binding.registerProgress.visibility = View.INVISIBLE
                            Toast.makeText(activity ,"your profile created " , Toast.LENGTH_LONG).show()
                            val action = CreateAccountScreen1FragmentDirections.actionCreateAccountScreen1FragmentToCreateAccountScreen2Fragment()
                            navControler.navigate(action)
                        }else{
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

}
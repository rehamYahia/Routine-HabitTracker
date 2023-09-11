package com.example.routinerhabittracker.ui.fragments.create_account_screens

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.routinerhabittracker.databinding.FragmentCreateAccountScreen1Binding
import java.text.SimpleDateFormat
import java.util.*


class CreateAccountScreen1Fragment : Fragment() {
    private  var _binding: FragmentCreateAccountScreen1Binding?=null
    private val binding get()= _binding!!
    private lateinit var navControler: NavController
    private val calendar = Calendar.getInstance()
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





        binding.btnBirthDate.setOnClickListener {
            openDialog()

        }

        binding.next1.setOnClickListener {
            val action = CreateAccountScreen1FragmentDirections.actionCreateAccountScreen1FragmentToCreateAccountScreen2Fragment()
            navControler.navigate(action)
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
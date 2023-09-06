package com.example.routinerhabittracker.ui.fragments.create_account_screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.routinerhabittracker.R
import com.example.routinerhabittracker.adapter.GenderAdapter
import com.example.routinerhabittracker.adapter.GenderGridAdapter
import com.example.routinerhabittracker.databinding.FragmentCreateAccountScreen1Binding
import com.example.routinerhabittracker.databinding.FragmentCreateAccountScreen2Binding
import com.example.routinerhabittracker.model.Gender


class CreateAccountScreen2Fragment : Fragment(), AdapterView.OnItemClickListener {
    private  var _binding: FragmentCreateAccountScreen2Binding?=null
    private val binding get()= _binding!!
    private lateinit var navControler: NavController
   private lateinit var genderArray :ArrayList<Gender>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navControler = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateAccountScreen2Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeGenderGrid()
        binding.next2.setOnClickListener {
            val action = CreateAccountScreen2FragmentDirections.actionCreateAccountScreen2FragmentToCreateAccountScreen3Fragment()
            navControler.navigate(action)
        }
    }

    fun initializeGenderGrid(){
        genderArray = ArrayList()
        genderArray.add(Gender(R.drawable.male , "Male") )
        genderArray.add(Gender(R.drawable.female , "Female") )

        binding.genderGridView.adapter = activity?.let { GenderGridAdapter(it, genderArray) }
        binding.genderGridView.onItemClickListener = this
    }



    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
       var gender:Gender = genderArray[position]
        Toast.makeText(activity , gender.genderType , Toast.LENGTH_LONG).show()
    }


}
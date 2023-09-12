package com.example.routinerhabittracker.ui.fragments.create_account_screens

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.routinerhabittracker.R
import com.example.routinerhabittracker.adapter.HabbitAdapter
import com.example.routinerhabittracker.databinding.FragmentCreateAccountScreen1Binding
import com.example.routinerhabittracker.databinding.FragmentCreateAccountScreen3Binding
import com.example.routinerhabittracker.model.Habbit

class CreateAccountScreen3Fragment : Fragment()  , AdapterView.OnItemClickListener{
    private  var _binding: FragmentCreateAccountScreen3Binding?=null
    private val binding get()= _binding!!
    private lateinit var navControler: NavController
     lateinit var habbitArray:ArrayList<Habbit>
    lateinit var habbitAdapter: HabbitAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navControler = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateAccountScreen3Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        habbitArray = ArrayList()
        habbitAdapter = activity?.let { HabbitAdapter(it, habbitArray) }!!

        initHabbitGridData()
        binding.next3.setOnClickListener {
            val action = CreateAccountScreen3FragmentDirections.actionCreateAccountScreen3FragmentToHomeFragment()
            navControler.navigate(action)
        }

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
      habbitAdapter.notifyDataSetChanged()
        if(binding.habbitGridView.isPressed){
//            view = binding.habbitGridView.getChildAt(position)
//            view.setBackgroundColor(Color.RED)
                   var habbit = habbitArray[position]
        Toast.makeText(activity , habbit.habbitName , Toast.LENGTH_LONG).show()

        }
//        else{
//            view = binding.habbitGridView.getChildAt(position)
//            view.setBackgroundColor(Color.GREEN)
//        }
    }

    fun initHabbitGridData(){
        habbitArray.add(Habbit(R.drawable.journal , "Journal"))
        habbitArray.add(Habbit(R.drawable.run , "Run"))
        habbitArray.add(Habbit(R.drawable.drink_water , "Drink Water"))
        habbitArray.add(Habbit(R.drawable.read_books , "Read Books"))
        habbitArray.add(Habbit(R.drawable.meditation , "Meditation"))
        habbitArray.add(Habbit(R.drawable.plant , "Plant"))
        habbitArray.add(Habbit(R.drawable.study , "Study"))
        habbitArray.add(Habbit(R.drawable.sleep , "Sleep"))
        binding.habbitGridView.adapter = habbitAdapter

    }

}
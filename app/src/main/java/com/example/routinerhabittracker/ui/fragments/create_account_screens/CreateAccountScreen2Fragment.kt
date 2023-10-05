package com.example.routinerhabittracker.ui.fragments.create_account_screens

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.routinerhabittracker.databinding.FragmentCreateAccountScreen2Binding
import dagger.hilt.android.AndroidEntryPoint


class CreateAccountScreen2Fragment : Fragment()  //, AdapterView.OnItemClickListener
{
    private  var _binding: FragmentCreateAccountScreen2Binding?=null
    private val binding get()= _binding!!
    private lateinit var navControler: NavController
//   private lateinit var genderArray :ArrayList<Gender>
//    var genderBoo = Array<Boolean>(2){false}
 lateinit var sharedPreferences:SharedPreferences

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

//        sharedPreferences =
        binding.femaleCard.setOnClickListener {
            binding.femaleCard.isChecked = !binding.femaleCard.isChecked
            if(binding.femaleCard.isChecked){

            }
        }
        binding.maleCard.setOnClickListener {
            binding.maleCard.isChecked = !binding.maleCard.isChecked
            if(binding.maleCard.isChecked){

            }
        }

        binding.next2.setOnClickListener {
            val action = CreateAccountScreen2FragmentDirections.actionCreateAccountScreen2FragmentToCreateAccountScreen3Fragment()
            navControler.navigate(action)
        }
    }

//    fun initializeGenderGrid(){
//        genderArray = ArrayList()
//        genderArray.add(Gender(R.drawable.male , "Male") )
//        genderArray.add(Gender(R.drawable.female , "Female") )
//
//        binding.genderGridView.adapter = activity?.let { GenderGridAdapter(it, genderArray) }
//        binding.genderGridView.onItemClickListener = this
//    }



//    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//        if(position == 0){
//            if(genderBoo[position] == false && genderBoo[position+1] ==false){
//                view?.setBackgroundColor(resources.getColor(R.color.purple_500))
//                genderBoo[position]=true
//            }else if(genderBoo[position] == false && genderBoo[position+1]==true){
//                Toast.makeText(activity , "Can not select more than one gender type" , Toast.LENGTH_LONG).show()
//            }
//        }else if(position ==1){
//            if(genderBoo[position] == false && genderBoo[position-1] ==false){
//                view?.setBackgroundColor(resources.getColor(R.color.purple_500))
//                genderBoo[position]=true
//            }else if(genderBoo[position] == false && genderBoo[position-1]==true){
//                Toast.makeText(activity , "Can not select more than one gender type" , Toast.LENGTH_LONG).show()
//            }
//            if(genderBoo[position]==true){
//                view?.setBackgroundColor(resources.getColor(R.color.white))
//                genderBoo[position] == false
//            }
//        }


//       var gender:Gender = genderArray[position]
//        Toast.makeText(activity , gender.genderType , Toast.LENGTH_LONG).show()
//    }

//    override fun onDestroy() {
//        super.onDestroy()
//        genderBoo[0] = false
//        genderBoo[1] = false
//    }


}
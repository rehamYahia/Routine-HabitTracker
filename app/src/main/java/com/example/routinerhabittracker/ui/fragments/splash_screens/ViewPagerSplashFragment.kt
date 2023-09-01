package com.example.routinerhabittracker.ui.fragments.splash_screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.routinerhabittracker.R
import com.example.routinerhabittracker.adapter.ViewPagerAdapter
import com.example.routinerhabittracker.databinding.FragmentSplashBinding
import com.example.routinerhabittracker.databinding.FragmentViewPagerSplashBinding


class ViewPagerSplashFragment : Fragment() {
    private  var _binding: FragmentViewPagerSplashBinding?=null
    private val binding get()= _binding!!
    private lateinit var navControler: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navControler = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        return view
    }


}
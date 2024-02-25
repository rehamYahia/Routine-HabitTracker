package com.example.routinerhabittracker.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(list:ArrayList<Fragment> , fragment:FragmentManager , lifeCycle: Lifecycle) : FragmentStateAdapter (fragment , lifeCycle){
    val fragmentList :ArrayList<Fragment> = list
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}



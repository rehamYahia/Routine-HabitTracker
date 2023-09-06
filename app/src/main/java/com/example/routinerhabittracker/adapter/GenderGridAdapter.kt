package com.example.routinerhabittracker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.routinerhabittracker.R
import com.example.routinerhabittracker.model.Gender

class GenderGridAdapter(var context: Context, var genderArray:ArrayList<Gender>): BaseAdapter() {
    override fun getCount(): Int {
        return genderArray.size
    }

    override fun getItem(position: Int): Any {
      return genderArray[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
      var view :View = View.inflate(context , R.layout.register_item , null)
        var  icon:ImageView = view.findViewById(R.id.icon)
        var name:TextView = view.findViewById(R.id.name)
        var gender:Gender =  genderArray[position]
        icon.setImageResource(gender.genderImage)
        name.text = gender.genderType
        return view
    }
}
package com.example.routinerhabittracker.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.routinerhabittracker.R
import com.example.routinerhabittracker.model.Habbit

class HabbitAdapter(var context: Context, var habbitArray:ArrayList<Habbit>) :BaseAdapter(){
    override fun getCount(): Int {
       return habbitArray.size
    }

    override fun getItem(position: Int): Any {
        return habbitArray[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
       var view:View = View.inflate(context, R.layout.register_item , null)
        var image = view.findViewById<ImageView>(R.id.icon)
        var name=view.findViewById<TextView>(R.id.name)
        var habbit:Habbit =  habbitArray[position]
        image.setImageResource(habbit.habbitImage)
        name.text = habbit.habbitName
        return view
    }
}
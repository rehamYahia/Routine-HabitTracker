package com.example.routinerhabittracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.routinerhabittracker.R
import com.example.routinerhabittracker.model.Gender

class GenderAdapter(val genderArray:ArrayList<Gender>) : RecyclerView.Adapter<GenderAdapter.GenderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenderViewHolder {
        return GenderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.register_item ,parent , false))
    }

    override fun getItemCount(): Int {
        return genderArray.size
    }

    override fun onBindViewHolder(holder: GenderViewHolder, position: Int) {
        holder.icon.setImageResource(genderArray[position].genderImage)
        holder.name.text = genderArray[position].genderType
    }


     class GenderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val icon: ImageView = itemView.findViewById<ImageView>(R.id.icon)
    val name: TextView = itemView.findViewById<TextView>(R.id.name)
    }
}
package com.example.taskmanagement

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.taskmanagement.CardInfo
import com.example.taskmanagement.databinding.ActivityMainBinding

class Adapter(private val data: List<CardInfo>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cardInfo: CardInfo) {
            with(binding) {
                title.text = cardInfo.title
                priority.text = cardInfo.priority

                // Set background color based on priority
                val color = when (cardInfo.priority.toLowerCase()) {
                    "high" -> "#F05454"
                    "medium" -> "#EDC988"
                    else -> "#00917C"
                }
                layout.setBackgroundColor(Color.parseColor(color))

                // Set click listener
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, UpdateCard::class.java)
                    intent.putExtra("id", adapterPosition)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.view, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        when (data[position].priority.toLowerCase()) {
            "high" -> holder.layout.setBackgroundColor(Color.parseColor("#F05454"))
            "medium" -> holder.layout.setBackgroundColor(Color.parseColor("#EDC988"))
            else -> holder.layout.setBackgroundColor(Color.parseColor("#00917C"))
        }

        holder.title.text = data[position].title
        holder.priority.text = data[position].priority
        holder.itemView.setOnClickListener{
            val intent= Intent(holder.itemView.context,UpdateCard::class.java)
            intent.putExtra("id",position)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }
}
package com.example.stopwatchpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(val lapTimes : List<LapTimeList>) : RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MainViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return lapTimes.size

    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = lapTimes[position]
        holder.apply {
            itemBinder(data)
        }
    }

}
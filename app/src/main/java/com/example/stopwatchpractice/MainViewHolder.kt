package com.example.stopwatchpractice

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*

class MainViewHolder (v: View) : RecyclerView.ViewHolder(v) {
    var itemContainer : View = v

    fun itemBinder(data: LapTimeList) {
        itemContainer.lapNoTextView.text = data.record
    }
}
package com.meetparmar.cricketscorer.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meetparmar.cricketscorer.R
import com.meetparmar.cricketscorer.database.MatchHistory

class HistoryAdapter : RecyclerView.Adapter<HistoryViewHolder>(){

    var data = listOf<MatchHistory?>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder.create(parent)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = data[position]
        if (item != null) {
            (holder as HistoryViewHolder).bind(item)
        }
    }
}
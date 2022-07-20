package com.esgi.scoremanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esgi.scoremanager.R
import com.esgi.scoremanager.models.Game
import kotlinx.android.synthetic.main.fragment_history.view.*
import kotlinx.android.synthetic.main.history_score_cell.view.*
import kotlinx.android.synthetic.main.score_history_player.view.*
import java.time.format.DateTimeFormatter

class HistoryAdapter(private val games: List<Game>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val gameNbr     : TextView = view.game_nbr_str
        val gameDate    : TextView = view.game_date_str
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.history_score_cell, viewGroup, false)





        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.gameNbr.text = "Partie : ${position + 1}"
        holder.gameDate.text = games[position].date.format(formatter)
        holder.itemView.recycler_score_history.layoutManager = LinearLayoutManager(holder.itemView.context)


        val adapter = RecapScoreAdapter(games[position])
        holder.itemView.recycler_score_history.adapter = adapter
    }

    override fun getItemCount(): Int = games.size
}
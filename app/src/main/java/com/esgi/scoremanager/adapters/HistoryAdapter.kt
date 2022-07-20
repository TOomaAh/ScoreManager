package com.esgi.scoremanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esgi.scoremanager.R
import com.esgi.scoremanager.models.Game
import kotlinx.android.synthetic.main.score_history_player.view.*

class HistoryAdapter(private val games: List<Game>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val gameName: TextView = view.player_name_str
        val nbrPlayer : TextView = view.player_score_str
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.score_history_player, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.gameName.text = "Partie ${position + 1}\t - "
        holder.nbrPlayer.text = "${games[position].players.size} joueurs"
    }

    override fun getItemCount(): Int = games.size
}
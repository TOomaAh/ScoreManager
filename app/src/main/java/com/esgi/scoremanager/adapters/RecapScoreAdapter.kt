package com.esgi.scoremanager.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.esgi.scoremanager.models.Game


import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esgi.scoremanager.R
import com.esgi.scoremanager.models.entities.Player
import kotlinx.android.synthetic.main.history_score_cell.view.*
import kotlinx.android.synthetic.main.score_history_player.view.*

class RecapScoreAdapter(private val game : Game) : RecyclerView.Adapter<RecapScoreAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val player_name_str: TextView = view.player_name_str
        val player_score_str: TextView = view.player_score_str
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecapScoreAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.score_history_player, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = game.players[position]
        holder.player_name_str.text = player.name
        var score : Int = 0
        for (round in game.rounds) {
            round?.players?.map {
                if (it.name == player.name) {
                    it.moves.map {
                        score += it.points
                    }
                }
            }
        }

        holder.player_score_str.text = "$score points"
    }

    override fun getItemCount(): Int = game.players.size

}

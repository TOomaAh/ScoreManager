package com.esgi.scoremanager.adapters

import android.provider.Contacts
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.RoomDatabase
import com.esgi.scoremanager.R
import com.esgi.scoremanager.models.Game
import com.esgi.scoremanager.room.GameRepository
import kotlinx.android.synthetic.main.fragment_history.view.*
import kotlinx.android.synthetic.main.history_score_cell.view.*
import kotlinx.android.synthetic.main.score_history_player.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

class HistoryAdapter(private var games: List<Game>, private val repository : GameRepository) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val gameNbr     : TextView = view.game_nbr_str
        val gameDate    : TextView = view.game_date_str
        val deleteGame  : Button = view.delete_card_btn
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


        holder.deleteGame.setOnClickListener {
            GlobalScope.launch {
                repository.delete(games[position])
                this@HistoryAdapter.games = repository.getAll()
                launch(Dispatchers.Main) {
                    this@HistoryAdapter.notifyItemRemoved(position)
                    this@HistoryAdapter.notifyDataSetChanged()
                }

            }
        }

        val adapter = RecapScoreAdapter(games[position])
        holder.itemView.recycler_score_history.adapter = adapter
    }

    override fun getItemCount(): Int = games.size
}
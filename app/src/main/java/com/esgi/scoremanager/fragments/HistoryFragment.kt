package com.esgi.scoremanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.esgi.scoremanager.R
import com.esgi.scoremanager.adapters.HistoryAdapter
import com.esgi.scoremanager.adapters.RecapScoreAdapter
import com.esgi.scoremanager.room.GameRepository
import com.esgi.scoremanager.room.GameRoomDatabase
import kotlinx.android.synthetic.main.fragment_game_recap.view.*
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.fragment_history.view.*
import kotlinx.android.synthetic.main.fragment_menu.view.*
import kotlinx.android.synthetic.main.fragment_score.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val database by lazy { GameRoomDatabase.getDatabase(this.context!!) }
        val repository by lazy { GameRepository(database.bowlingDao()) }

        view.btn_history_back.setOnClickListener {
            val action = HistoryFragmentDirections.actionHistoryFragmentToMenuFragment()
            findNavController().navigate(action)
        }





        view.history_score_recycler.layoutManager = LinearLayoutManager(requireContext())


        GlobalScope.launch {
            val games = repository.getAll()

            GlobalScope.launch(Dispatchers.Main.immediate) {
                if (games.isEmpty()) {
                    no_history.text = "No history found"
                }else {
                    val adapter = HistoryAdapter(games, repository)
                    view.history_score_recycler.adapter = adapter
                }
            }


        }

    }
}
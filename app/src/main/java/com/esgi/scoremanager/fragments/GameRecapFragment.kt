package com.esgi.scoremanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.esgi.scoremanager.R
import com.esgi.scoremanager.adapters.CustomAdapter
import com.esgi.scoremanager.adapters.RecapScoreAdapter
import com.esgi.scoremanager.models.Game
import com.esgi.scoremanager.models.sport.Bowling
import com.esgi.scoremanager.room.GameRepository
import com.esgi.scoremanager.room.GameRoomDatabase
import kotlinx.android.synthetic.main.fragment_game_recap.view.*
import kotlinx.android.synthetic.main.fragment_game_setup.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GameRecapFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_recap, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val game : Game = GameRecapFragmentArgs.fromBundle(requireArguments()).game
        view.recycler_score_history.layoutManager = LinearLayoutManager(requireContext())
        val adapter = RecapScoreAdapter(game)
        view.recycler_score_history.adapter = adapter

        val database by lazy { GameRoomDatabase.getDatabase(this.context!!) }
        val repository by lazy { GameRepository(database.bowlingDao()) }


        val okButton : Button = view.ok_button

        okButton.setOnClickListener {
            GlobalScope.launch {
                repository.insert(game)
            }
            val action : NavDirections = GameRecapFragmentDirections.actionGameRecapFragmentToMenuFragment()
            findNavController().navigate(action)
        }
    }

}
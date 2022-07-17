package com.esgi.scoremanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.esgi.scoremanager.R
import com.esgi.scoremanager.adapters.CustomAdapter
import com.esgi.scoremanager.adapters.RecapScoreAdapter
import com.esgi.scoremanager.models.Game
import com.esgi.scoremanager.models.sport.Bowling
import kotlinx.android.synthetic.main.fragment_game_recap.view.*
import kotlinx.android.synthetic.main.fragment_game_setup.view.*

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
    }

}
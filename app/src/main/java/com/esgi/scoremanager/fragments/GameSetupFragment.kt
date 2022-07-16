package com.esgi.scoremanager.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CursorAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.esgi.scoremanager.R
import com.esgi.scoremanager.adapters.CustomAdapter
import com.esgi.scoremanager.models.Entity
import com.esgi.scoremanager.models.Move
import com.esgi.scoremanager.models.entities.Player
import com.esgi.scoremanager.models.sport.SportBuilder
import kotlinx.android.synthetic.main.fragment_game_setup.view.*

class GameSetupFragment : Fragment() {

    private val bowling : SportBuilder = SportBuilder()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_setup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bowling.addEntity(Player(""))
        view.recycler_player.layoutManager = LinearLayoutManager(requireContext())
        val adapter = CustomAdapter(bowling)
        view.recycler_player.adapter = adapter

        val addPlayerBtn = view.add_player_btn
        val continueBtn = view.continue_btn

        addPlayerBtn.setOnClickListener {
            bowling.addEntity(Player(""))
            adapter.notifyDataSetChanged()

        }

        continueBtn.setOnClickListener {
            val action =  GameSetupFragmentDirections.actionGameSetupFragmentToScoreFragment()
            findNavController().navigate(action)
        }
    }
}
package com.esgi.scoremanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.esgi.scoremanager.R
import com.esgi.scoremanager.adapters.CustomAdapter
import com.esgi.scoremanager.models.Entity
import com.esgi.scoremanager.models.Move
import kotlinx.android.synthetic.main.fragment_game_setup.view.*

class GameSetupFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_setup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.recycler_player.layoutManager = LinearLayoutManager(requireContext())
        view.recycler_player.adapter = CustomAdapter(1)

        val addPlayerBtn = view.add_player_btn
        addPlayerBtn.setOnClickListener{
            //rajouter un element au recycler
        }
    }
}
package com.esgi.scoremanager.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.esgi.scoremanager.R
import com.esgi.scoremanager.adapters.CustomAdapter
import com.esgi.scoremanager.models.entities.Player
import com.esgi.scoremanager.models.sport.SportBuilder
import kotlinx.android.synthetic.main.fragment_game_setup.*
import kotlinx.android.synthetic.main.fragment_game_setup.view.*
import kotlinx.android.synthetic.main.history_score_cell.*

class GameSetupFragment : Fragment() {

    private val bowling : SportBuilder = SportBuilder(maxRounds = 10)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_setup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bowling.addEntity(Player("", maxMove = bowling.maxRounds))
        view.recycler_player.layoutManager = LinearLayoutManager(requireContext())
        val adapter = CustomAdapter(bowling)
        view.recycler_player.adapter = adapter

        val addPlayerBtn = view.add_player_btn
        val continueBtn = view.continue_btn

        addPlayerBtn.setOnClickListener {
            bowling.addEntity(Player("", maxMove = bowling.maxRounds))
            adapter.notifyDataSetChanged()

        }

        back_btn.setOnClickListener {
            val action = GameSetupFragmentDirections.actionGameSetupFragmentToMenuFragment()
            findNavController().navigate(action)
        }

        continueBtn.setOnClickListener {
            val entities = bowling.entities
            for (i in 0 until entities.size) {
                if (entities[i].name.isEmpty()) {
                    showAlertNameEmpty(i + 1).show()
                    return@setOnClickListener
                }
            }
            val action =  GameSetupFragmentDirections.actionGameSetupFragmentToScoreFragment(bowling)
            findNavController().navigate(action)
        }
    }

    private fun showAlertNameEmpty(position : Int) : AlertDialog.Builder {
        return AlertDialog.Builder(this.context)
            .setMessage("User $position has no name")
            .setPositiveButton("ok") { dialog, _ ->
                dialog.cancel()
            }
    }
}
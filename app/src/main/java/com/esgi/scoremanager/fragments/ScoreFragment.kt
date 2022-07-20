package com.esgi.scoremanager.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.esgi.scoremanager.R
import com.esgi.scoremanager.models.Game
import com.esgi.scoremanager.models.Move
import com.esgi.scoremanager.models.entities.Player
import com.esgi.scoremanager.models.iterator.rounds.Rounds
import com.esgi.scoremanager.models.move.Spare
import com.esgi.scoremanager.models.move.Strike
import com.esgi.scoremanager.models.sport.Bowling
import kotlinx.android.synthetic.main.fragment_score.*
import kotlinx.android.synthetic.main.fragment_score.view.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import kotlin.math.log

class ScoreFragment : Fragment() {

    private var bowling : Bowling? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_score, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bowling = ScoreFragmentArgs.fromBundle(requireArguments()).bowling.build() as Bowling
        var round = Rounds(bowling!!.entities)
        bowling?.addRound(round)

        val player: Player = this.bowling?.entities?.get(round.currentPlayerIndex)!!
        player_str.text = player.name

        string_round.text = player.formatCurrentThrowsIndex()

        view.recycler_score_grid.layoutManager = LinearLayoutManager(requireContext())
        strike_btn.setOnClickListener {
            play(Strike())
        }

        spare_btn.setOnClickListener {
            play(Spare())
        }

        hole_btn.setOnClickListener {
            Log.d("PROUT", "HOLE")
        }


    }

    private fun play(move: Move) {
        val round : Rounds = bowling!!.rounds.getCurrent()!!
        val player: Player = round.players[round.currentPlayerIndex]
        val canAddMove = player.addMove(move)

        if (!canAddMove) {
            AlertDialog.Builder(this.context).setMessage("Cannot add move ! Total exceeded").setPositiveButton("Ok") { dialogInterface, i ->
                dialogInterface.dismiss()
            }.show()
            return
        }else {
            Log.d("NEWROUND", "play: $round")
        }
        if (bowling?.rounds!!.isLast() && round.currentPlayerIndex == round.players.size - 1){
            val game : Game = Game(
                id          = 0,
                name        = bowling?.name!!,
                date        = LocalDateTime.now(),
                rounds      = bowling?.rounds!!.toList(),
                players     = bowling?.entities!!
            )
            val action =  ScoreFragmentDirections.actionScoreFragmentToGameRecapFragment(game)
            findNavController().navigate(action)
            return


        }

        round.currentPlayerIndex += 1

        if (round.currentPlayerIndex == round.players.size) {
            Log.d("NEWROUND", "play: $round")
            val newRound = Rounds(bowling!!.entities)
            Log.d("EQUALS", "play: ${round.players.equals(newRound.players)}")
            bowling?.addRound(newRound)
            player_str.text = newRound.players[newRound.currentPlayerIndex].name
        }else {
            player_str.text = round.players[round.currentPlayerIndex].name
        }

    }
}
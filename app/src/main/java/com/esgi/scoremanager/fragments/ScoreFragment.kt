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
import com.esgi.scoremanager.models.iterator.rounds.RoundsIterator
import com.esgi.scoremanager.models.move.Strike
import com.esgi.scoremanager.models.sport.Bowling
import com.esgi.scoremanager.room.GameDatabase
import kotlinx.android.synthetic.main.fragment_score.*
import kotlinx.android.synthetic.main.fragment_score.view.*
import kotlin.math.log

class ScoreFragment : Fragment() {

    private var bowling : Bowling? = null
    private var roundsIndex : Int = 0

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

        bowling?.addRound(Rounds(bowling?.entities!!))
        val round : Rounds = bowling?.rounds?.getCurrent()!! as Rounds
        val player: Player = this.bowling?.entities?.get(round.currentPlayerIndex)!!
        player_str.text = player.name

        string_round.text = player.formatCurrentThrowsIndex()

        view.recycler_score_grid.layoutManager = LinearLayoutManager(requireContext())
        //La j'ai mis la liste en dure en haut mais faut calculer en fonction du score les resutlats possible
        //Si y'a toutes les quilles c'est de 1 à 9
        //Si le mec a fait 7 avant -> il faut afficher 1 et 2. 3 ca voudrait dire spare
        //Je comprends pas pourquoi y'a que 1 chiffre qui apparait
        //Faut cacher le bouton du spare si c'est le premier lancer. -> tu ne peux que Strike ou Hole au 1er
        //Faut cacher le bouton du strike au deuxieme lancer -> tu ne peux que Spare ou Hole au 2eme
        //Dans l'adapateur tu peux gérer le onclick des boutons de score en bas.
        //Dans l'idée un joueur a 2 lancer donc au deuxieme lancer -> R.id.string_round.text = "2nd throw"
        //Quand un joueur a fait ses deux lancer ou un strike, meme écran mais pour le j2, j3 etc ...
        //Quand tous les round sont finis -> 10eme tours ou game.sport.round.size -> Ecran des score final (historique quoi)

        strike_btn.setOnClickListener {
            play(Strike())
        }

        spare_btn.setOnClickListener {
            Log.d("PROUT", "SPARE")
        }

        hole_btn.setOnClickListener {
            Log.d("PROUT", "HOLE")
        }


    }

    private fun play(move: Move) {

        var rounds: Rounds = bowling?.rounds?.getCurrent() as Rounds

        if (bowling?.rounds!!.isLast() && rounds.currentPlayerIndex == rounds.players.size - 1){

            AlertDialog.Builder(this.context).setMessage("Save this game ?").setPositiveButton("Yes") { dialogInterface, i ->
                val game : Game = Game(
                    0,
                    bowling?.name!!,
                    bowling?.rounds!!.toList()
                )
                GameDatabase.getDatabase(this.context!!).gameDao().insertGame(game)
                dialogInterface.dismiss()
            }.setNegativeButton("No") { alert, _ ->
                findNavController().popBackStack()
                alert.dismiss()
            }.show()

            return


        }
        val player: Player = rounds.players[rounds.currentPlayerIndex]
        val canAddMove = player.addMove(move)
        if (!canAddMove) {
            Log.d("TAG", "play: $canAddMove")
            AlertDialog.Builder(this.context).setMessage("Cannot add move ! Total exceeded").setPositiveButton("Ok") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
        }

        rounds.currentPlayerIndex += 1

        if (rounds.currentPlayerIndex == rounds.players.size) {

            bowling?.rounds!!.addItem(Rounds(bowling?.entities!!))
            rounds = bowling?.rounds?.getCurrent() as Rounds
        }
        player_str.text = rounds.players[rounds.currentPlayerIndex].name
    }
}
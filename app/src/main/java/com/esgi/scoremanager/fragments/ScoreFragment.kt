package com.esgi.scoremanager.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.esgi.scoremanager.R
import com.esgi.scoremanager.adapters.ScoreGridAdapter
import com.esgi.scoremanager.models.Bowling
import kotlinx.android.synthetic.main.fragment_score.*
import kotlinx.android.synthetic.main.fragment_score.view.*

class ScoreFragment : Fragment() {

    val scoresNumber : MutableList<Int> = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val scoresView : MutableList<View> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_score, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val entities = ScoreFragmentArgs.fromBundle(requireArguments()).entities
        for (i in entities) {
            Log.d("PROUT", i.toString())
        }
        Bowling.getInstance()
        view.recycler_score_grid.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ScoreGridAdapter(numbers = scoresNumber);
        view.recycler_score_grid.adapter = adapter
        adapter.notifyDataSetChanged()

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
            Log.d("PROUT", "STRIKE")
        }

        spare_btn.setOnClickListener {
            Log.d("PROUT", "SPARE")
        }

        hole_btn.setOnClickListener {
            Log.d("PROUT", "HOLE")
        }


    }
}
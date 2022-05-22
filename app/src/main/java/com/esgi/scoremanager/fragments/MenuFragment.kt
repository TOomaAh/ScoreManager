package com.esgi.scoremanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.esgi.scoremanager.R
import kotlinx.android.synthetic.main.fragment_menu.view.*

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.new_game_btn.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToGameSetupFragment()
            findNavController().navigate(action)
        }
    }
}
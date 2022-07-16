package com.esgi.scoremanager.adapters

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esgi.scoremanager.R
import com.esgi.scoremanager.models.sport.SportBuilder
import kotlinx.android.synthetic.main.item_player.view.*

class CustomAdapter(private val bowling : SportBuilder) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val playerNbrLabel: TextView = view.player_number_input
        val playerNameInput: EditText = view.player_name_input
        val playerBtn : Button = view.button_delete_player

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_player, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        viewHolder.playerNbrLabel.text = String.format("Player %d", position + 1)
        viewHolder.playerNameInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                bowling.entities[position].name = viewHolder.playerNameInput.text.toString()
                Log.d("TAG", "onTextChanged: ${bowling.entities[position]}")
            }

            override fun afterTextChanged(s: Editable?) {}

        })

        if (bowling.entities.size == 1) {
            viewHolder.playerBtn.visibility = View.INVISIBLE
        }else {
            viewHolder.playerBtn.setOnClickListener {
                bowling.entities.removeAt(position)
                this.notifyDataSetChanged()
            }
        }

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //viewHolder.playerNameInput

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = bowling.entities.size

}

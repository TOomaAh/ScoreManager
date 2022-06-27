package com.esgi.scoremanager.adapters

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.esgi.scoremanager.R
import kotlinx.android.synthetic.main.item_player.view.*
import kotlinx.android.synthetic.main.item_score_number.view.*

class ScoreGridAdapter(private val numbers : MutableList<Int>) : RecyclerView.Adapter<ScoreGridAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val scoreBtn : Button = view.item_score_number_item

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_score_number, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        viewHolder.scoreBtn.text = numbers[position].toString();
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //viewHolder.playerNameInput

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = numbers.size

}

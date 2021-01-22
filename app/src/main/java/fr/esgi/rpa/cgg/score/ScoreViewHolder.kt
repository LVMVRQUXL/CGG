package fr.esgi.rpa.cgg.score

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.esgi.rpa.cgg.R

class ScoreViewHolder(inflater: LayoutInflater, parent: ViewGroup, attachToRoot: Boolean = false) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.score_item, parent, attachToRoot)) {

    private var id: TextView? = null
    private var difficulty: TextView? = null
    private var score: TextView? = null

    init {
        this.id = itemView.findViewById(R.id.game_id)
        this.difficulty = itemView.findViewById(R.id.difficulty)
        this.score = itemView.findViewById(R.id.score)
    }

    fun bind(score: Score) {
        this.id?.text = score.id.toString()
        this.difficulty?.text = score.difficulty()
        this.score?.text = score.value()
    }

}
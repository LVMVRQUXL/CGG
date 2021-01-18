package fr.esgi.rpa.cgg.score

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ScoresAdapter (private val scores: List<Score>) : RecyclerView.Adapter<ScoreViewHolder>() {

    override fun getItemCount(): Int = this.scores.size

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val score: Score = this.scores[position]
        holder.bind(score)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ScoreViewHolder(inflater, parent)
    }

}
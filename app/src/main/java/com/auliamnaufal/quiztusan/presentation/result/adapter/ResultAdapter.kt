package com.auliamnaufal.quiztusan.presentation.result.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.auliamnaufal.quiztusan.databinding.ItemLeaderboardBinding
import com.auliamnaufal.quiztusan.model.Player

class ResultAdapter:RecyclerView.Adapter<ResultAdapter.ViewHolder>() {
    var listPlayer = arrayListOf<Player>()
    class ViewHolder(val binding: ItemLeaderboardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder(ItemLeaderboardBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            tvName.text = listPlayer[position].name
            tvScore.text = listPlayer[position].score
        }
    }

    override fun getItemCount() = listPlayer.size
}
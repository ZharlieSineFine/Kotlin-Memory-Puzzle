package au.edu.jcu.zhaochanglinmemorygame

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import au.edu.jcu.zhaochanglinmemorygame.database.Score
import au.edu.jcu.zhaochanglinmemorygame.databinding.RowLeaderboardBinding


class LeaderboardAdapter : RecyclerView.Adapter<LeaderboardAdapter.MyViewHolder>() {

    private var scoreList = emptyList<Score>()

    class MyViewHolder(private val binding: RowLeaderboardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(score: Score, rank: Int) {
            binding.idTxt.text = rank.toString()
            binding.difficultyTxt.text = score.difficulty
            binding.userNameTxt.text = score.username
            binding.movesNumTxt.text = binding.root.context.getString(R.string.moves_label, score.moves)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowLeaderboardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return scoreList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = scoreList[position]
        holder.bind(currentItem, position + 1)    // Pass position + 1 as rank
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(score: List<Score>) {
        this.scoreList = score
        this.scoreList = score.sortedBy{ it.moves }
        notifyDataSetChanged()
    }

}
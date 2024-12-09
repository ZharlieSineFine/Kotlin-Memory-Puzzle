package au.edu.jcu.zhaochanglinmemorygame

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.edu.jcu.zhaochanglinmemorygame.viewmodel.ScoreViewModel

class LeaderboardFragment : Fragment() {

    private lateinit var mScoreViewModel: ScoreViewModel
    private lateinit var adapter: LeaderboardAdapter

    companion object {
        private const val TAG = "LeaderboardFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_leaderboard, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Leaderboard"
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup RecyclerView
        adapter = LeaderboardAdapter()
        val recyclerView: RecyclerView = view.findViewById(R.id.leaderboard_recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        // Initialize UserViewModel
        mScoreViewModel = ViewModelProvider(requireActivity())[ScoreViewModel::class.java]

        // Observe user data for changes
        mScoreViewModel.readAllData.observe(viewLifecycleOwner) { scores ->
            if (scores.isEmpty()) {
                Toast.makeText(requireContext(), "No high scores available", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setData(scores)
                Log.d(TAG, "High scores updated: ${scores.map { "${it.username}: ${it.moves}" }}")
            }
        }

    }
}
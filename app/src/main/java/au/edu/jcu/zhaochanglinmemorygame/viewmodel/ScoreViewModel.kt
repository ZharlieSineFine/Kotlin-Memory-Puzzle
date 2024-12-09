package au.edu.jcu.zhaochanglinmemorygame.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import au.edu.jcu.zhaochanglinmemorygame.database.AppDatabase
import au.edu.jcu.zhaochanglinmemorygame.database.Score
import au.edu.jcu.zhaochanglinmemorygame.database.ScoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScoreViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        private const val TAG = "ScoreViewModel"
    }

    val readAllData: LiveData<List<Score>>
    private val repository: ScoreRepository

    private val _currentScore = MutableLiveData<Score?>()

    init {
        val scoreDao = AppDatabase.getDatabase(application).scoreDao()
        repository = ScoreRepository(scoreDao)
        readAllData = repository.readAllData
    }

    fun addScore(score: Score) {
        viewModelScope.launch(Dispatchers.IO) {
            // Check if the user already exists
            val existingScore = repository.getScoreByName(score.username)
            if (existingScore == null) {
                // If the user doesn't exist, add the new score
                repository.addScore(score)
                _currentScore.postValue(score) // Set the current score after adding
                Log.d(TAG, "Score recorded: ${score.username} with moves: ${score.moves}")
            } else {
                // If the user exists and the new score is better (lower), update the score
                if (score.moves <= existingScore.moves) {
                    // Update the existing record with the new score
                    existingScore.moves = score.moves
                    existingScore.difficulty = score.difficulty
                    repository.updateScore(existingScore)
                    _currentScore.postValue(existingScore) // Update current score
                    Log.d(TAG, "Score updated: ${existingScore.username} to new moves: ${existingScore.moves}")
                } else {
                    Log.d(TAG, "Existing score for ${score.username} is better or same, not updating to ${score.moves}")
                }
            }
        }
    }

}














package au.edu.jcu.zhaochanglinmemorygame.database

import androidx.lifecycle.LiveData

class ScoreRepository(private val scoreDao: ScoreDao) {
    // LiveData to observe all users
    val readAllData: LiveData<List<Score>> = scoreDao.readAllData()

    // Method to add a new user
    suspend fun addScore(score: Score) {
        scoreDao.insertScore(score)
    }

    // Method to update an existing user
    suspend fun updateScore(score: Score) {
        scoreDao.updateScore(score)
    }

    // Method to get a user by username
    suspend fun getScoreByName(username: String): Score? {
        return scoreDao.getScoreByName(username) // Ensure this method is defined in ScoreDao
    }

}
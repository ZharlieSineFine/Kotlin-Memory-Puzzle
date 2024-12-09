package au.edu.jcu.zhaochanglinmemorygame.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ScoreDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertScore(score: Score)

    @Update
    suspend fun updateScore(score: Score)

    @Query("SELECT * FROM leaderboard ORDER BY difficulty ASC, moves ASC")
    fun sortScoresByDifficultyAndMoves(): LiveData<List<Score>>

    @Query("SELECT * FROM leaderboard ORDER BY id ASC")
    fun readAllData(): LiveData<List<Score>>

    @Query("SELECT * FROM leaderboard WHERE username = :username LIMIT 1")
    suspend fun getScoreByName(username: String): Score?

    @Query("DELETE FROM leaderboard")
    suspend fun clearData()

}
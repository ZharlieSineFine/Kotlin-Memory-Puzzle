package au.edu.jcu.zhaochanglinmemorygame.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a single game score entry.
 * Note: Lower scores are better as they indicate the game was completed in fewer moves.
 */
@Entity(tableName = "leaderboard")
data class Score(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var difficulty: String,
    val username: String,
    var moves: Int,

)
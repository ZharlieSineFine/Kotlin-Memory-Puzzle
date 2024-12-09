package au.edu.jcu.zhaochanglinmemorygame.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class ScoreDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: AppDatabase
    private lateinit var scoreDao: ScoreDao

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        scoreDao = db.scoreDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndRetrieveScore() = runBlocking {
        val score = Score(username = "testUser", difficulty = "Easy", moves = 10)
        scoreDao.insertScore(score)

        val retrievedScores = scoreDao.readAllData().getOrAwaitValue()
        assertTrue(retrievedScores.isNotEmpty())
        assertEquals("testUser", retrievedScores[0].username)
        assertEquals("Easy", retrievedScores[0].difficulty)
        assertEquals(10, retrievedScores[0].moves)
    }

    fun <T> LiveData<T>.getOrAwaitValue(time: Long = 2, timeUnit: TimeUnit = TimeUnit.SECONDS): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = Observer<T> { o ->
            data = o
            latch.countDown()
        }

        observeForever(observer)
        try {
            if (!latch.await(time, timeUnit)) {
                throw TimeoutException("LiveData value was never set.")
            }
        } finally {
            removeObserver(observer)
        }

        @Suppress("UNCHECKED_CAST")
        return data as T
    }

}
package au.edu.jcu.zhaochanglinmemorygame.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()  // Allow main thread queries just for testing.
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testDatabaseCreation() {
        assertNotNull(db)
    }

    @Test
    fun testDaoCreation() {
        val scoreDao = db.scoreDao()
        assertNotNull(scoreDao)
    }
}
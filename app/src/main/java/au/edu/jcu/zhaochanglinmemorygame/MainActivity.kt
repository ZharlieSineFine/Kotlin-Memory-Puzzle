package au.edu.jcu.zhaochanglinmemorygame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import au.edu.jcu.zhaochanglinmemorygame.databinding.ActivityMainBinding
import android.media.MediaPlayer
import androidx.navigation.ui.AppBarConfiguration


class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var mediaPlayer: MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        drawerLayout = binding.drawerLayout
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        // Set up ActionBar with NavController
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        // Set up NavigationView with NavController
        NavigationUI.setupWithNavController(binding.navView, navController)

        // Customize navigation item click behavior
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_music_settings -> {
                    navController.navigate(R.id.action_landingFragment_to_settingsFragment)
                    drawerLayout.closeDrawers() // Close drawer when item is tapped
                    true
                }
                R.id.nav_share_this_game -> {  // share game menu item
                    navController.navigate(R.id.action_landingFragment_to_shareFragment)
                    drawerLayout.closeDrawers()
                    true
                }
                R.id.nav_leaderboard -> {
                    // Navigate to LeaderboardFragment
                    if (navController.currentDestination?.id != R.id.leaderboardFragment) {
                        navController.navigate(R.id.action_landingFragment_to_leaderboardFragment)
                    }
                    drawerLayout.closeDrawers()
                    true
                }
                else -> false
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    fun playMusic() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.background_music)
            mediaPlayer?.isLooping = true // Set looping
        }
        mediaPlayer?.start() // Start playing
    }

    fun stopMusic() {
        mediaPlayer?.stop() // Stop playing
        mediaPlayer?.release() // Release resources
        mediaPlayer = null // Set mediaPlayer to null
    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this)
        if (sharedPreferences.getBoolean("background_music", true)) {
            playMusic() // Play music if the setting is enabled
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.pause() // Pause the music when the app is not visible
    }

}
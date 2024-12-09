package au.edu.jcu.zhaochanglinmemorygame

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        // Handler with a delay to transition to the LandingActivity after 3 seconds
        Handler().postDelayed({
            // Create an intent that will start the LandingActivity.
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // Finish the SplashActivity so it can't be returned to.
            finish()
        }, 3000)  // 3000 milliseconds or 3 seconds delay
    }
}
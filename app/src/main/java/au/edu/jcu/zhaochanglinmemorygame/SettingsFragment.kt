package au.edu.jcu.zhaochanglinmemorygame

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)


        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Music Settings"

        return view
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val switchMusic = view.findViewById<Switch>(R.id.switch_music)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        // Set the switch based on saved preferences
        switchMusic.isChecked = sharedPreferences.getBoolean("background_music", true)

        switchMusic.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean("background_music", isChecked).apply()

            if (isChecked) {
                // Code to start playing background music
                (activity as MainActivity).playMusic()
            } else {
                // Code to stop playing background music
                (activity as MainActivity).stopMusic()
            }
        }
    }
}
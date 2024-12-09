package au.edu.jcu.zhaochanglinmemorygame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LandingFragment : Fragment() {

    private lateinit var usernameEditText: TextInputEditText
    private lateinit var usernameInputLayout: TextInputLayout
    private lateinit var startButton: MaterialButton
    private lateinit var leaderboardIcon: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_landing, container, false)

        usernameEditText = view.findViewById(R.id.usernameEditText)
        usernameInputLayout = view.findViewById(R.id.usernameTextInputLayout)
        startButton = view.findViewById(R.id.startButton)
        leaderboardIcon = view.findViewById(R.id.leaderBoardIcon)  // Initialize the ImageView


        startButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            if (username.isEmpty()) {
                usernameInputLayout.error = "Username cannot be empty"
            } else {
                usernameInputLayout.error = null // Clear any previous error
                val action = LandingFragmentDirections.actionLandingFragmentToGameFragment(username)
                findNavController().navigate(action)
                Toast.makeText(context, "Welcome, $username!", Toast.LENGTH_SHORT).show()
            }
        }

        // Set the OnClickListener for the leaderboard icon
        leaderboardIcon.setOnClickListener {
            findNavController().navigate(R.id.action_landingFragment_to_leaderboardFragment)
        }

        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Create user"

        return view
    }

    override fun onResume() {
        super.onResume()
        usernameEditText.setText("")  // Clear the text field when the view resumes
    }

}

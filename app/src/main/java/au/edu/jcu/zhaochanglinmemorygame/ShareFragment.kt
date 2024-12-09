package au.edu.jcu.zhaochanglinmemorygame

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class ShareFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_share_game, container, false)

        val imgShare = view.findViewById<ImageView>(R.id.imgShare)
        imgShare.setOnClickListener {
            shareApp()  // Implement this method to handle sharing
        }

        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "About this game"

        return view
    }

    private fun shareApp() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Goku Memory Game")
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Ready to test your memory skills? See if you can beat my top score in the Memory Puzzle Game! \uD83C\uDF89 Let’s see what you've got! Download here: [Link]")
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
}
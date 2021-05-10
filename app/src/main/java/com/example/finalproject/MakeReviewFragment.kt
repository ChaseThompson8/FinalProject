package com.example.finalproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_make_review.*
import kotlinx.android.synthetic.main.fragment_make_review.review_rating
import kotlinx.android.synthetic.main.fragment_make_review.view.*
import kotlinx.android.synthetic.main.review_item.*

//

import kotlinx.android.synthetic.main.fragment_reviews.view.*

import android.*
import android.media.MediaPlayer
import android.nfc.Tag
import androidx.constraintlayout.motion.utils.Oscillator.TAG

import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.fragment_appointment.*
//

class MakeReviewFragment : Fragment() {
    var myMediaPlayer : MediaPlayer? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_make_review, container, false)

        root.submit_review_button.setOnClickListener {
            val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            val review = user_review_text.text.toString()
            val rating = review_rating.rating.toString()
            //

            val db = FirebaseFirestore.getInstance()
            val data=hashMapOf(
                    "Details" to user_review_text.text.toString(),
                    "Rating" to review_rating.numStars.toFloat()
            )
            db.collection("Make_Review").add(data)
                    .addOnSuccessListener{
                        documentReference -> Log.d(TAG,"DocumentSnapshot written with ID:${documentReference.id}")
                    }
                    .addOnFailureListener{
                        e->
                        Log.w(TAG,"Error adding document",e)
                    }

            //
            if (review.isEmpty() || rating.isEmpty()){
                showDialogue("Missing Info", "Please select a rating and give a " +
                        "description for your rating then click submit")
                playSound(root)
            }
            else {
                showDialogue("Thank You!", "Thank you for leaving a review\n" +
                            "You will now be redirected back to the home page.")
                editor.apply()
                val navController = Navigation.findNavController(root)
                navController.navigate(R.id.navigation_home)
            }

        }
        return root
    }
    private fun showDialogue(title: String, message: String) {
        val builder = activity?.let { AlertDialog.Builder(it) }
        builder?.setTitle(title)
        builder?.setMessage(message)
        builder?.setIcon(android.R.drawable.ic_menu_info_details)
        builder?.setPositiveButton("OKAY"){ dialog, which ->
        }
        val dialog = builder?.create()
        dialog?.show()
    }
    fun playSound(view: View) {
        if (myMediaPlayer == null){
            myMediaPlayer = MediaPlayer.create(activity, R.raw.ehooga)
        }
        myMediaPlayer?.start()
    }
}

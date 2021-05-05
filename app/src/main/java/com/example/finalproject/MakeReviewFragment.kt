package com.example.finalproject

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_appointment.*
import kotlinx.android.synthetic.main.fragment_make_review.*
import kotlinx.android.synthetic.main.fragment_make_review.view.*

class MakeReviewFragment : Fragment() {

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
            val rating = rating_bar.rating.toString()

            if (review.isEmpty() || rating.isEmpty()){
                showDialogue("Missing Info", "Please select a rating and give a " +
                        "description for your rating then click submit")
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
}
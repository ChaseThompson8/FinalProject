package com.example.finalproject.ui.profile

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.Navigation
import com.example.finalproject.R
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_edit_profile.view.*
import kotlinx.android.synthetic.main.fragment_edit_profile.view.profile_email
import kotlinx.android.synthetic.main.fragment_edit_profile.view.profile_img
import kotlinx.android.synthetic.main.fragment_edit_profile.view.profile_name
import kotlinx.android.synthetic.main.fragment_profile.view.*

class EditProfileFragment : Fragment() {

    private val PICK_IMAGE = 1
    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_edit_profile, container, false)

        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        var gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)

        root.edit_profile_pic_btn.setOnClickListener {
            gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, PICK_IMAGE)
    }
        root.save_button.setOnClickListener {

            val newName = edit_name.text.toString()
            val newEmail = edit_email.text.toString()
            val newPic = gallery.toString()

            if (newName.isNotEmpty()){
                editor.putString("Name", newName)
            }
            if (newEmail.isNotEmpty()){
                editor.putString("Email", newEmail)
            }
            if (newPic.isNotEmpty()){
                editor.putString("Pic", newPic)
            }

            editor.apply()

            val navController = Navigation.findNavController(root)
            navController.navigate(R.id.navigation_profile)
        }

        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data?.data
            profile_img.setImageURI(imageUri)

        }

    }

}
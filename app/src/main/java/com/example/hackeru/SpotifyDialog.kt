package com.example.hackeru

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class SpotifyDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alert = android.app.AlertDialog.Builder(requireContext())
        alert.setTitle("Spotify not installed")
        alert.setMessage("Spotify is not installed on your device. Would you like to install it?")
        alert.setPositiveButton("Yes") { dialog, which ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data =
                Uri.parse("https://play.google.com/store/apps/details?id=com.spotify.music")
            startActivity(intent)
        }
        alert.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }
        return alert.create()
    }
}
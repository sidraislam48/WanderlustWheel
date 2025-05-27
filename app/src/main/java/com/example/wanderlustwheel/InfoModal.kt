package com.example.wanderlustwheel

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class InfoModal : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireActivity())
            .setTitle("How to Use")
            .setMessage(
                "Welcome to Wanderlust Wheel!\n\n" +
                        "1. Tap a destination vibe: Beach or Forest 🌴🌲\n" +
                        "2. Select your mood: Sunny or Rainy ☀️🌧\n" +
                        "3. Tap 'Reveal My Destination' to discover your fantasy travel spot!"
            )
            .setPositiveButton("Got it!") { dialog, _ -> dialog.dismiss() }
            .create()
    }
}

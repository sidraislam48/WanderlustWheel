package com.example.wanderlustwheel

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private lateinit var vibeSpinner: Spinner
    private lateinit var moodSpinner: Spinner
    private lateinit var revealButton: Button

    private lateinit var destinationsArray: JSONArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vibeSpinner = findViewById(R.id.vibeSpinner)
        moodSpinner = findViewById(R.id.moodSpinner)
        revealButton = findViewById(R.id.btnReveal)

        // Load JSON data from assets
        destinationsArray = loadJsonArrayFromAsset("data.json")

        // Get unique vibes and moods from JSON
        val vibes = getUniqueValuesFromJson("vibe")
        val moods = getUniqueValuesFromJson("mood")

        // Setup adapters for Spinners
        vibeSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, vibes)
        moodSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, moods)

        revealButton.setOnClickListener {
            val selectedVibe = vibeSpinner.selectedItem as String
            val selectedMood = moodSpinner.selectedItem as String

            var destination = findDestination(selectedVibe, selectedMood)

            if (destination == null) {
                // Fallback to a random destination from all
                val randomIndex = (0 until destinationsArray.length()).random()
                destination = destinationsArray.getJSONObject(randomIndex).getJSONObject("destination")
                Toast.makeText(this, "No exact match found — showing a random destination!", Toast.LENGTH_SHORT).show()
            }

            destination?.let {
                val intent = Intent(this, ResultActivity::class.java).apply {
                    putExtra("name", it.getString("name"))
                    putExtra("image", it.getString("image"))
                    putExtra("description", it.getString("description"))
                }
                startActivity(intent)
            }
        }
    }

    private fun loadJsonArrayFromAsset(filename: String): JSONArray {
        val inputStream = assets.open(filename)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val jsonString = bufferedReader.use { it.readText() }
        return JSONArray(jsonString)
    }

    private fun getUniqueValuesFromJson(key: String): List<String> {
        val set = mutableSetOf<String>()
        for (i in 0 until destinationsArray.length()) {
            val obj = destinationsArray.getJSONObject(i)
            set.add(obj.getString(key))
        }
        return set.toList().sorted()
    }

    private fun findDestination(vibe: String, mood: String): JSONObject? {
        for (i in 0 until destinationsArray.length()) {
            val obj = destinationsArray.getJSONObject(i)
            if (obj.getString("vibe") == vibe && obj.getString("mood") == mood) {
                return obj.getJSONObject("destination")
            }
        }
        return null
    }
}

package com.example.taskmanagement

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Onboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if the app has been opened before
        val sharedPref = getSharedPreferences("com.example.taskmanagement.PREFERENCES", Context.MODE_PRIVATE)
        val isFirstOpen = sharedPref.getBoolean("isFirstOpen", true)

        if (isFirstOpen) {
            // Set the content view to the onboard layout
            setContentView(R.layout.activity_onboard)

            // Find the button by its ID
            val buttonNavigate = findViewById<Button>(R.id.bt_started)

            // Set a click listener on the button
            buttonNavigate.setOnClickListener {
                // Create an Intent to start the MainActivity
                val mainActivityIntent = Intent(this, MainActivity::class.java)
                // Start the MainActivity
                startActivity(mainActivityIntent)

                // Set the flag to indicate that the app has been opened before
                with (sharedPref.edit()) {
                    putBoolean("isFirstOpen", false)
                    apply()
                }

                // Finish the onboard activity so the user cannot go back
                finish()
            }
        } else {
            // If the app has been opened before, directly start the MainActivity
            val mainActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(mainActivityIntent)
            finish()
        }
    }
}

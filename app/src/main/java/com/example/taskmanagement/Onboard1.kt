package com.example.taskmanagement

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Onboard1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if onboarding is already completed
        if (onboardingCompleted()) {
            startMainActivity()
            finish()
            return
        }

        setContentView(R.layout.activity_onboard)
        val buttonNavigate = findViewById<Button>(R.id.bt_started)

        buttonNavigate.setOnClickListener {
            markOnboardingCompleted()
            startMainActivity()
        }
    }

    private fun onboardingCompleted(): Boolean {
        val sharedPrefs = getSharedPreferences("OnboardingPrefs", Context.MODE_PRIVATE)
        return sharedPrefs.getBoolean("OnboardingCompleted", false)
    }

    private fun markOnboardingCompleted() {
        val sharedPrefs = getSharedPreferences("OnboardingPrefs", Context.MODE_PRIVATE)
        sharedPrefs.edit().putBoolean("OnboardingCompleted", true).apply()
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

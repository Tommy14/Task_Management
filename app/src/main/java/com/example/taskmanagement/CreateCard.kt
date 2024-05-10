package com.example.taskmanagement


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.example.taskmanagement.databinding.ActivityCreateTaskBinding
import com.example.taskmanagement.databinding.ActivityMainBinding
import com.example.taskmanagement.myDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CreateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()

        binding.saveButton.setOnClickListener {
            val title = binding.createTitle.text.toString().trim()
            val priority = binding.createPriority.text.toString().trim()

            if (title.isNotEmpty() && priority.isNotEmpty()) {
                GlobalScope.launch {
                    val newTaskId = database.dao().insertTask(Entity(0, title, priority))
                    // Handle successful insertion
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                // Display error message if fields are empty
            }
        }
    }
}
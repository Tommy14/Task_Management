package com.example.taskmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import com.example.taskmanagement.databinding.ActivityMainBinding
import com.example.taskmanagement.myDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    private lateinit var createTitle: EditText
    private lateinit var createPriority: EditText
    private lateinit var deleteButton: Button
    private lateinit var updateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_task)

        // Initialize views
        createTitle = findViewById(R.id.create_title)
        createPriority = findViewById(R.id.create_priority)
        deleteButton = findViewById(R.id.delete_button)
        updateButton = findViewById(R.id.update_button)

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()

        val pos = intent.getIntExtra("id", -1)
        if (pos != -1) {
            val title = DataObject.getData(pos).title
            val priority = DataObject.getData(pos).priority
            createTitle.setText(title)
            createPriority.setText(priority)

            deleteButton.setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    database.dao().deleteTask(
                        Entity(
                            pos + 1,
                            createTitle.text.toString(),
                            createPriority.text.toString()
                        )
                    )
                }
                myIntent()
            }

            updateButton.setOnClickListener {
                DataObject.updateData(
                    pos,
                    createTitle.text.toString(),
                    createPriority.text.toString()
                )
                GlobalScope.launch {
                    database.dao().updateTask(
                        Entity(
                            pos + 1, createTitle.text.toString(),
                            createPriority.text.toString()
                        )
                    )
                }
                myIntent()
            }
        }
    }

    private fun myIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}

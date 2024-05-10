package com.example.taskmanagement


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.taskmanagement.CreateCard
import com.example.taskmanagement.databinding.ActivityMainBinding
import com.example.taskmanagement.DataObject
import com.example.taskmanagement.myDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


// entity - table
// dao - queries
// database

class MainActivity : AppCompatActivity() {
    private lateinit var database: myDatabase
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()
        binding.add.setOnClickListener {
            val intent = Intent(this, CreateCard::class.java)
            startActivity(intent)
        }
        binding.deleteAll.setOnClickListener {
            DataObject.deleteAll()
            GlobalScope.launch {
                database.dao().deleteAll()
            }
            setRecycler()
        }

        setRecycler()

    }

    fun setRecycler() {
        binding.recyclerView.adapter = Adapter(DataObject.getAllData())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
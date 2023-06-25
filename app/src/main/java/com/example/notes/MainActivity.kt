package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), INotesRVAdapter {
    lateinit var viewModel: NoteViewModel
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()


        recyclerView = findViewById(R.id.rv)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        val adapter = MyAdapter(this, this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[(NoteViewModel::class.java)]
        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                adapter.update(it)
            }




        })





    }



    override fun onItemClicked(note: Note) {
            viewModel.deleteNote(note)

        }

    fun submitButton(view: View) {
        val textVi = findViewById<TextView>(R.id.tV)
        val noteText = textVi.text.toString()
        if (noteText.isNotEmpty()) {

            viewModel.insertNote(Note(noteText))
        }


    }
}
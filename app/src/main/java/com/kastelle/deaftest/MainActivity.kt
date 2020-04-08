package com.kastelle.deaftest

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kastelle.deaftest.database.Song

/** UI controller for the main interface. */
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var songsCountTextView: TextView

    private var databaseCount = 0
    private var filterCount = 0

    private lateinit var listView: ListView
    private lateinit var searchView: SearchView
    private lateinit var adapter: ArrayAdapter<Song>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songsCountTextView = findViewById(R.id.songs_count_text)

        listView = findViewById(R.id.list_view)
        searchView = findViewById(R.id.search_view)
        adapter = ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, listOf())
        listView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter.filter(newText) {
                    filterCount = it
                    updateSongsCountView()
                }
                return true
            }
        })

        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
        viewModel.getSongs().observe(this, Observer {
            databaseCount = it.size
            filterCount = databaseCount
            adapter = ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, it)
            listView.adapter = adapter
            updateSongsCountView()
        })
    }

    private fun updateSongsCountView() {
        songsCountTextView.text = getString(R.string.songs_count, databaseCount, filterCount)
    }
}

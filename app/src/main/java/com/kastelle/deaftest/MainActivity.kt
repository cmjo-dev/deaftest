package com.kastelle.deaftest

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.kastelle.deaftest.database.Song

/** UI controller for the main interface. */
class MainActivity : AppCompatActivity(), SongsListAdapter.RecyclerViewClickListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var songsCountTextView: TextView

    private var databaseCount = 0
    private var filterCount = 0

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: SongsListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songsCountTextView = findViewById(R.id.songs_count_text)
        searchView = findViewById(R.id.search_view)

        viewManager = LinearLayoutManager(this)
        viewAdapter = SongsListAdapter(mutableListOf(), this)

        recyclerView = findViewById<RecyclerView>(R.id.songs_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
            isClickable = true
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewAdapter.filter.filter(newText) {
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
            viewAdapter = SongsListAdapter(it.toMutableList(), this)
            recyclerView.adapter = viewAdapter
            updateSongsCountView()
        })
    }

    private fun updateSongsCountView() {
        songsCountTextView.text = getString(R.string.songs_count, databaseCount, filterCount)
    }


    override fun onRecyclerViewClicked(position: Int) {
        if (position != NO_POSITION) {
            val intent = Intent(this, LyricsActivity::class.java)
            intent.putExtra("KEY_LYRICS", viewAdapter.getItem(position).lyrics)
            startActivity(intent)
        }
    }
}

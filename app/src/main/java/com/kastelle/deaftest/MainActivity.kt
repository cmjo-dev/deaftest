package com.kastelle.deaftest

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kastelle.deaftest.database.Song

/** UI controller for the main interface. */
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    //private lateinit var songsTextView: TextView

    private lateinit var listView: ListView
    private lateinit var searchView: SearchView
    private lateinit var adapter: ArrayAdapter<Song>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //songsTextView = findViewById(R.id.songs_text)

        listView = findViewById(R.id.list_view)
        searchView = findViewById(R.id.search_view)
        adapter = ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, listOf())
        listView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })

        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
        viewModel.getSongs().observe(this, Observer {
            adapter = ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, it)
            listView.adapter = adapter
        })
    }

/*    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        
*//*        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }*//*

        return true
    }*/
}

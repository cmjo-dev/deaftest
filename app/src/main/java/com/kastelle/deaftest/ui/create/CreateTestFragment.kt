package com.kastelle.deaftest.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.kastelle.deaftest.R
import com.kastelle.deaftest.SongsListAdapter

/** UI controller representing the fragment allowing to create a new deaf test. */
class CreateTestFragment : Fragment(), SongsListAdapter.RecyclerViewClickListener {

    private lateinit var viewModel: CreateTestViewModel

    // TODO: Should be retrieved from VM directly?
    private var databaseCount = 0
    private var filterCount = 0

    private lateinit var songsCountTextView: TextView
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: SongsListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_create_test, container, false)
        songsCountTextView = root.findViewById(R.id.songs_count_text)
        searchView = root.findViewById(R.id.search_view)

        viewManager = LinearLayoutManager(activity)
        viewAdapter = SongsListAdapter(mutableListOf(), this)

        recyclerView = (root.findViewById(R.id.songs_recycler_view) as RecyclerView).apply {
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

        viewModel = ViewModelProvider(this).get(CreateTestViewModel::class.java)
        viewModel.getSongs().observe(viewLifecycleOwner, {
            databaseCount = it.size
            filterCount = databaseCount
            viewAdapter = SongsListAdapter(it.toMutableList(), this)
            recyclerView.adapter = viewAdapter
            updateSongsCountView()
        })

        return root
    }

    override fun onRecyclerViewClicked(position: Int) {
        if (position == NO_POSITION) return

        val lyrics = viewAdapter.getItem(position).lyrics
        if (!lyrics.isNullOrEmpty()) {
            startActivity(activity?.lyricsActivityIntent(lyrics))
        } else {
            Toast.makeText(activity, "This song does not have lyrics!", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Update the view displaying the number of songs in the database and the number of songs matching the search
     * results. Must be updated each time the database song table or the user search query change.
     */
    private fun updateSongsCountView() = songsCountTextView.apply {
        text = getString(R.string.songs_count, databaseCount, filterCount)
    }
}

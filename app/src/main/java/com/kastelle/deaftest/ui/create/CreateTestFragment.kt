package com.kastelle.deaftest.ui.create

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.kastelle.deaftest.LyricsActivity
import com.kastelle.deaftest.R
import com.kastelle.deaftest.SongsListAdapter

/** TODO: add the javadoc. */
class CreateTestFragment : Fragment(), SongsListAdapter.RecyclerViewClickListener {

    private lateinit var viewModel: CreateTestViewModel
    private lateinit var songsCountTextView: TextView

    private var databaseCount = 0
    private var filterCount = 0

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: SongsListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var searchView: SearchView

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

        recyclerView = (root.findViewById<RecyclerView>(R.id.songs_recycler_view) as RecyclerView).apply {
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

        viewModel = ViewModelProviders.of(this)[CreateTestViewModel::class.java]
        viewModel.getSongs().observe(this, Observer {
            databaseCount = it.size
            filterCount = databaseCount
            viewAdapter = SongsListAdapter(it.toMutableList(), this)
            recyclerView.adapter = viewAdapter
            updateSongsCountView()
        })
        return root
    }

    private fun updateSongsCountView() {
        songsCountTextView.text = getString(R.string.songs_count, databaseCount, filterCount)
    }

    override fun onRecyclerViewClicked(position: Int) {
        if (position != NO_POSITION) {
            val intent = Intent(activity, LyricsActivity::class.java)
            intent.putExtra("KEY_LYRICS", viewAdapter.getItem(position).lyrics)
            startActivity(intent)
        }
    }
}

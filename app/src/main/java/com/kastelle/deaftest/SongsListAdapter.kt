package com.kastelle.deaftest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.kastelle.deaftest.database.Song
import kotlinx.android.synthetic.main.songs_list_item.view.*
import java.text.Normalizer
import java.util.*
import kotlin.collections.ArrayList


class SongsListAdapter(private val songs: MutableList<Song>): RecyclerView.Adapter<SongsListAdapter.SongViewHolder>(), Filterable {

    // Single not-to-be-modified copy of original data in the list.
    private val originalList = ArrayList(songs)

    // a method-body to invoke when search returns nothing. It can be null.
    private var onNothingFound: (() -> Unit)? = null

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class SongViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.songs_list_item, parent, false) as View
        // set the view's size, margins, paddings and layout parameters
        //...
        return SongViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val song = songs[position]
        val context = holder.view.context
        holder.view.song_item.text = context.getString(R.string.song_list_item, song.title, song.artist, song.album)
    }

    override fun getItemCount(): Int = songs.size

    private val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()

    private fun CharSequence.normalize(): String {
        val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
        return REGEX_UNACCENT.replace(temp, "").toLowerCase(Locale.getDefault())
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            private val filterResults = FilterResults()
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                songs.clear()
                if (constraint.isNullOrBlank()) {
                    songs.addAll(originalList)
                } else {
                    //TODO: implement multi-search.
                    val normalizedConstraint = constraint.normalize()
                    val searchResults = originalList.filter {
                        (!it.title.isNullOrEmpty() && it.title.normalize().contains(normalizedConstraint)) ||
                                (!it.artist.isNullOrEmpty() && it.artist.normalize().contains(normalizedConstraint))}
                    songs.addAll(searchResults)
                }
                return filterResults.also {
                    it.values = songs
                    it.count = songs.size
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                // no need to use "results" filtered list provided by this method.
                if (originalList.isNullOrEmpty())
                    onNothingFound?.invoke()
                notifyDataSetChanged()
            }
        }
    }
}

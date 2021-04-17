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
import java.util.Locale
import kotlin.collections.ArrayList

/**
 * The adapter to display songs in a recycler view.
 * @param songs the list of songs to display.
 * @param recyclerViewClickListener the listener to notify upon song item click.
 */
class SongsListAdapter(private val songs: MutableList<Song>,
                       private val recyclerViewClickListener: RecyclerViewClickListener):
    RecyclerView.Adapter<SongsListAdapter.SongViewHolder>(), Filterable {

    @FunctionalInterface
    interface RecyclerViewClickListener {
        fun onRecyclerViewClicked(position: Int)
    }

    /** Single not-to-be-modified copy of original data in the list. */
    private val originalList = ArrayList(songs)

    /** A method-body to invoke when search returns nothing, can be null. */
    private var onNothingFound: (() -> Unit)? = null

    /**
     * Provide a reference to the views for each data item.
     * Complex data items may need more than one view per item, and you provide access to all the views for a data
     * item in a view holder. Each data item is just a string in this case that is shown in a TextView.
     */
    class SongViewHolder(val view: View, private val listener: RecyclerViewClickListener) :
        RecyclerView.ViewHolder(view), View.OnClickListener {

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            listener.onRecyclerViewClicked(layoutPosition)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.songs_list_item, parent, false) as View
        return SongViewHolder(view, recyclerViewClickListener)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        val context = holder.view.context
        holder.view.song_item.text = context.getString(R.string.song_list_item, song.title, song.artist, song.album)
    }

    override fun getItemCount(): Int = songs.size

    fun getItem(position: Int) = songs[position]

    private fun CharSequence.normalize(): String {
        val regexUnaccented = "\\p{InCombiningDiacriticalMarks}+".toRegex()
        val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
        return regexUnaccented.replace(temp, "").toLowerCase(Locale.getDefault())
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            private val filterResults = FilterResults()
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                songs.clear()
                if (constraint.isNullOrBlank()) {
                    songs.addAll(originalList)
                } else {
                    //TODO: Implement multi-search.
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

package com.kastelle.deaftest.ui.create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.kastelle.deaftest.R
import com.kastelle.deaftest.ui.create.LyricsActivity.Companion.KEY_LYRICS
import kotlinx.android.synthetic.main.activity_lyrics.lyrics_textView

/** UI controller for the interface which displays the lyrics of a song. */
class LyricsActivity : AppCompatActivity() {

    companion object {
        const val KEY_LYRICS: String =  "KEY_LYRICS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyrics)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        lyrics_textView.text = intent.extras?.getString(KEY_LYRICS)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}

fun Context.lyricsActivityIntent(lyrics: String): Intent {
    return Intent(this, LyricsActivity::class.java).putExtra(KEY_LYRICS, lyrics)
}

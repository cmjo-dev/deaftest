package com.kastelle.deaftest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_lyrics.*

class LyricsActivity : AppCompatActivity() {

    // TODO: define the key more properly so that it can be accessed from other classes (companion?)
    val KEY_LYRICS: String = "KEY_LYRICS"

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyrics)

        // Return button.
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        textView = findViewById(R.id.lyrics_textView)
        lyrics_textView.text = intent.extras?.getString(KEY_LYRICS)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item)
    }
}

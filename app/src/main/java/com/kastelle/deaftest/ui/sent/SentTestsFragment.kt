package com.kastelle.deaftest.ui.sent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kastelle.deaftest.R

/** UI controller representing the fragment which allows to see the sent tests */
class SentTestsFragment : Fragment() {

    private lateinit var viewModel: SentTestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SentTestsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_sent_tests, container, false)
        val textView: TextView = root.findViewById(R.id.text_sent_tests)
        viewModel.text.observe(viewLifecycleOwner, { textView.text = it })
        return root
    }
}

package com.kastelle.deaftest.ui.received

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kastelle.deaftest.R

/** UI controller representing the fragment which allows the see the received tests. */
class ReceivedTestsFragment : Fragment() {

    private lateinit var viewModel: ReceivedTestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ReceivedTestsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_received_tests, container, false)
        val textView: TextView = root.findViewById(R.id.text_received_tests)
        viewModel.text.observe(viewLifecycleOwner, { textView.text = it })
        return root
    }
}

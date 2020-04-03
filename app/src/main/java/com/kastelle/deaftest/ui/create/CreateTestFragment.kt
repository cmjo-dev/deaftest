package com.kastelle.deaftest.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kastelle.deaftest.R

/** TODO: add the javadoc. */
class CreateTestFragment : Fragment() {

    private lateinit var viewModel: CreateTestViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(CreateTestViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_received_tests, container, false)
        val textView: TextView = root.findViewById(R.id.text_received_tests)
        viewModel.text.observe(viewLifecycleOwner, Observer { textView.text = it })
        return root
    }
}

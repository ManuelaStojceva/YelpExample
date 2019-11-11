package com.yelp.yelpapp.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.yelp.yelpapp.R

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_about, container, false)
        val textView: TextView = root.findViewById(R.id.txtAboutApp)

        textView.text = HtmlCompat.fromHtml(getString(R.string.sample_string),  HtmlCompat.FROM_HTML_MODE_LEGACY)
        return root
    }
}
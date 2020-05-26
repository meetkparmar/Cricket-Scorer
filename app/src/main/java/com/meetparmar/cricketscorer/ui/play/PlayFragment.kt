package com.meetparmar.cricketscorer.ui.play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.meetparmar.cricketscorer.R

class PlayFragment : Fragment() {

    private lateinit var playViewModel: PlayViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        playViewModel =
                ViewModelProviders.of(this).get(PlayViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_play, container, false)
//        val textView: TextView = root.findViewById(R.id.)
//        playViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }
}

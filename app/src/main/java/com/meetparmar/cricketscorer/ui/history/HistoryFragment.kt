package com.meetparmar.cricketscorer.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.meetparmar.cricketscorer.R
import com.meetparmar.cricketscorer.database.MatchHistory
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel
    private val adapter = HistoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        historyViewModel =
            ViewModelProviders.of(this).get(HistoryViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        rv_history.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_history.adapter = adapter
        historyViewModel.fetchAllMatchHistory()
        historyViewModel.matchHistory().observe(this.viewLifecycleOwner, matchHistoryObserver)
    }
     private val matchHistoryObserver = Observer<List<MatchHistory>> {
         if (it == null || it.isEmpty()){
             adapter.data = emptyList()
             rv_history.visibility = View.GONE
             empty_history.visibility = View.VISIBLE
         } else {
             adapter.data = it
         }
     }
}

package com.myanmaritc.voteapp.ui.queen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.myanmaritc.voteapp.R
import com.myanmaritc.voteapp.model.KingQueenItem
import com.myanmaritc.voteapp.ui.adapter.KingQueenAdapter
import kotlinx.android.synthetic.main.fragment_queen.*

class QueenFragment : Fragment(), KingQueenAdapter.OnClickListener {

    private lateinit var queenViewModel: QueenViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        queenViewModel =
            ViewModelProvider(this).get(QueenViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_queen, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var queenAdapter = KingQueenAdapter()

        recyclerQueen.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = queenAdapter
        }

        queenAdapter.setOnClickListener(this)

        queenViewModel.loadQueen()
        queenViewModel.getQueen().observe(
            viewLifecycleOwner,
            Observer { queen -> queenAdapter.addKingQueen(queen.dataList as List<KingQueenItem>) })
    }

    override fun onResume() {
        super.onResume()
        queenViewModel.loadQueen()
    }

    override fun onClick(item: KingQueenItem) {
        var directions = QueenFragmentDirections.actionNavigationQueenToQueenDetailFragment(item)
        view?.findNavController()?.navigate(directions)
    }
}
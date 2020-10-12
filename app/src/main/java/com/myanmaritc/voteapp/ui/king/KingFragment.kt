package com.myanmaritc.voteapp.ui.king

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.myanmaritc.voteapp.R
import com.myanmaritc.voteapp.model.KingQueenItem
import com.myanmaritc.voteapp.ui.adapter.KingQueenAdapter
import kotlinx.android.synthetic.main.fragment_king.*

class KingFragment : Fragment(), KingQueenAdapter.OnClickListener {

    private lateinit var kingViewModel: KingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        kingViewModel =
            ViewModelProvider(this).get(KingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_king, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var kingAdapter = KingQueenAdapter()
        recyclerKing.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = kingAdapter
        }

        kingAdapter.setOnClickListener(this)

        kingViewModel.loadKing()
        kingViewModel.getKing().observe(
            viewLifecycleOwner,
            Observer { king -> kingAdapter.addKingQueen(king.dataList as List<KingQueenItem>) })
    }

    override fun onResume() {
        super.onResume()
        kingViewModel.loadKing()
    }

    override fun onClick(item: KingQueenItem) {
        val directions = KingFragmentDirections.actionNavigationKingToDetailFragment(item)
        view?.findNavController()?.navigate(directions)
    }
}
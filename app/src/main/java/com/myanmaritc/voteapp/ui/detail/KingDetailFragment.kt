package com.myanmaritc.voteapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.myanmaritc.voteapp.R
import com.myanmaritc.voteapp.api.VotingClient
import com.myanmaritc.voteapp.model.KingQueenItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_king_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KingDetailFragment : Fragment() {

    private val baseImg: String = "http://voting-monywa.dsv.hoz.mybluehost.me/image/"

    private val args: KingDetailFragmentArgs by navArgs()
    private lateinit var item: KingQueenItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_king_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        item = args.item

        detailName.text = item.name
        detailClassName.text = item.className
        Picasso.get().load(baseImg + args.item.image)
            .into(voteImage)

        btnSubmit.setOnClickListener {
            var apiClient = VotingClient()
            var apiCall = apiClient.voteKing(item.id!!, editCode.text.toString())

            apiCall.enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    result.text = response.body()
                }

            })
        }
    }
}
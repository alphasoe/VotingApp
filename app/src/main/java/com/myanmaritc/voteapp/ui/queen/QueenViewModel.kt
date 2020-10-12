package com.myanmaritc.voteapp.ui.queen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myanmaritc.voteapp.api.VotingClient
import com.myanmaritc.voteapp.model.KingQueenItem
import com.myanmaritc.voteapp.model.KingQueenModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QueenViewModel : ViewModel() {

    private var queenModel: MutableLiveData<KingQueenModel> = MutableLiveData()

    fun getQueen() = queenModel

    fun loadQueen() {
        var apiClient = VotingClient()
        var apiCall = apiClient.getQueen()

        apiCall.enqueue(object : Callback<KingQueenModel> {
            override fun onFailure(call: Call<KingQueenModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<KingQueenModel>,
                response: Response<KingQueenModel>
            ) {
                queenModel.value = response.body()
            }
        })
    }
}
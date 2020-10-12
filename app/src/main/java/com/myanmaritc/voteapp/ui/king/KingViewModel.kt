package com.myanmaritc.voteapp.ui.king

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myanmaritc.voteapp.api.VotingClient
import com.myanmaritc.voteapp.model.KingQueenModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KingViewModel : ViewModel() {
    private var kingModel: MutableLiveData<KingQueenModel> = MutableLiveData()

    fun getKing() = kingModel

    fun loadKing() {
        var apiClient = VotingClient()
        var apiCall = apiClient.getKing()

        apiCall.enqueue(object : Callback<KingQueenModel> {
            override fun onFailure(call: Call<KingQueenModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<KingQueenModel>,
                response: Response<KingQueenModel>
            ) {
                kingModel.value = response.body()
            }

        })
    }
}
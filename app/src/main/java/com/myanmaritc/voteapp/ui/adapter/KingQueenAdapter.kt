package com.myanmaritc.voteapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myanmaritc.voteapp.R
import com.myanmaritc.voteapp.model.KingQueenItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_king_queen.view.*

class KingQueenAdapter : RecyclerView.Adapter<KingQueenAdapter.KingQueenViewHolder>() {

    var clickListener: OnClickListener? = null

    var kingKingQueenList: List<KingQueenItem> = ArrayList()

    inner class KingQueenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        lateinit var item: KingQueenItem

        fun bind(kingKingQueenItem: KingQueenItem) {
            this.item = kingKingQueenItem
            itemView.name.text = kingKingQueenItem.name
            itemView.classname.text = kingKingQueenItem.className

            Picasso.get().load(kingKingQueenItem.image)
                .placeholder(R.drawable.ic_launcher_background).into(itemView.imgKingQueen)

        }

        override fun onClick(v: View?) {
            clickListener?.onClick(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KingQueenViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_king_queen, parent, false)
        return KingQueenViewHolder(view)
    }

    override fun getItemCount(): Int = kingKingQueenList.size

    override fun onBindViewHolder(holder: KingQueenViewHolder, position: Int) {
        holder.bind(kingKingQueenList[position])
    }

    fun addKingQueen(kingKingQueenList: List<KingQueenItem>) {
        this.kingKingQueenList = kingKingQueenList
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onClick(item: KingQueenItem)
    }

    fun setOnClickListener(clickListener: OnClickListener) {
        this.clickListener = clickListener
    }
}
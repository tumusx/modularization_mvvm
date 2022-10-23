package com.github.tumusx.list.presenter.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.tumusx.list.R
import com.github.tumusx.list.domain.vo.CatsUserCaseDTO
import com.github.tumusx.shared.diffutil.DiffUtilClass

class CatsAdapter :
    RecyclerView.Adapter<CatsAdapter.CatsViewHolder>() {

    private var catsList = emptyList<CatsUserCaseDTO>()

    class CatsViewHolder(binding: View) :
        RecyclerView.ViewHolder(binding) {
        @SuppressLint("SetTextI18n")
        fun setCat(cats: CatsUserCaseDTO) {
            itemView.findViewById<TextView>(R.id.txtNameCat).text = cats.name
            itemView.findViewById<TextView>(R.id.txtDescriptionCat).text =
                "Description: " + cats.description
            Glide.with(itemView.findViewById<ImageView>(R.id.imgCat)).load(cats.image?.url)
                .into(itemView.findViewById(R.id.imgCat))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val binding =
            LayoutInflater.from(parent.context).inflate(R.layout.container_cats_item, null, false)
        return CatsViewHolder(binding)
    }

    override fun getItemCount() = catsList.size

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        holder.setCat(catsList[position])
    }

    fun updateList(listNewItemCats: List<CatsUserCaseDTO>) {
        val myDiffUtil = DiffUtilClass(catsList, listNewItemCats)
        val diffResults = DiffUtil.calculateDiff(myDiffUtil)
        catsList = listNewItemCats
        diffResults.dispatchUpdatesTo(this)
    }
}
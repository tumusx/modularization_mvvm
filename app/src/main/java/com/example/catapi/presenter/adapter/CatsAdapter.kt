package com.example.catapi.presenter.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catapi.R
import com.example.catapi.common.diffUtil.DiffUtilClass
import com.example.catapi.data.network.local.entity.Cats
import com.example.catapi.databinding.ContainerCatsItemBinding
import com.example.catapi.domain.dto.CatsUserCaseDTO

class CatsAdapter(private val callback: (CatsUserCaseDTO) -> Unit) :
    RecyclerView.Adapter<CatsAdapter.CatsViewHolder>() {

    private var catsList = emptyList<CatsUserCaseDTO>()

    class CatsViewHolder(val binding: ContainerCatsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setCat(cats: CatsUserCaseDTO) {
            binding.txtNameCat.text = cats.name
            binding.txtDescriptionCat.text = "Description: " + cats.description
            Glide.with(binding.imgCat).load(cats.image?.url).into(binding.imgCat)
            if(cats.catsFavorite == true) binding.icFavoriteItem.setImageDrawable(binding.root.context.getDrawable(R.drawable.ic_favorite_item))
        }
    }

    private fun setSaveItem(holder: CatsViewHolder, position: Int) {
        holder.binding.icFavoriteItem.setOnClickListener {
            holder.binding.icFavoriteItem.setImageDrawable(holder.binding.root.context.getDrawable(R.drawable.ic_favorite_item))
            callback.invoke(catsList[position])
            catsList[position].catsFavorite = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val viewLayout = LayoutInflater.from(parent.context)
        val binding = ContainerCatsItemBinding.inflate(viewLayout, parent, false)
        return CatsViewHolder(binding)
    }

    override fun getItemCount() = catsList.size

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        holder.setCat(catsList[position])
        setSaveItem(holder, position)
    }

    fun updateList(listNewItemCats: List<CatsUserCaseDTO>) {
        val myDiffUtil = DiffUtilClass(catsList, listNewItemCats)
        val diffResults = DiffUtil.calculateDiff(myDiffUtil)
        catsList = listNewItemCats
        diffResults.dispatchUpdatesTo(this)
    }
}
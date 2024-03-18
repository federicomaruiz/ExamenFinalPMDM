package com.utad.examenfinalpmdm.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.utad.examenfinalpmdm.data.network.model.responseItem
import com.utad.examenfinalpmdm.databinding.ItemBinding

class HomeAdapter : ListAdapter<responseItem, HomeAdapter.BaseViewHolder>(ItemCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = getItem(position)
        //TODO pintar datos de la Recycler view
        holder.binding.tvTitle.text = item.taskTitle
        holder.binding.tvName.text = item.employee
        holder.binding.tvFecha.text = item.deadLine
        holder.binding.tvTask.text = item.project.department
        Glide.with(holder.binding.root)
            .load(item.project.projectPhoto)
            .into(holder.binding.ivImg)
    }


    inner class BaseViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)
}

object ItemCallBack : DiffUtil.ItemCallback<responseItem>() {
    override fun areItemsTheSame(oldItem: responseItem, newItem: responseItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: responseItem, newItem: responseItem): Boolean {
        return oldItem == newItem
    }

}
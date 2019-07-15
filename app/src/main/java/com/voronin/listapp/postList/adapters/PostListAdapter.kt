package com.voronin.listapp.postList.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.voronin.listapp.R
import com.voronin.listapp.databinding.PostListItemBinding
import com.voronin.listapp.postList.models.Post

class PostListAdapter(diffCallback: DiffUtil.ItemCallback<Post>) :
    PagedListAdapter<Post, PostListAdapter.ViewHolder>(diffCallback) {

    var onClickListener: ((post: Post) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(PostListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.post = item
        holder.background.setOnClickListener { onClickListener?.invoke(item!!) }
    }

    class ViewHolder(val binding: PostListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val background: View = binding.root.findViewById(R.id.background)
    }
}
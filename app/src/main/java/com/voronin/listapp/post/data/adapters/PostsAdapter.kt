package com.voronin.listapp.post.data.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.voronin.listapp.post.data.models.Post

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    private val items: ArrayList<Post> = ArrayList()

    fun update(items: ArrayList<Post>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]


    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}
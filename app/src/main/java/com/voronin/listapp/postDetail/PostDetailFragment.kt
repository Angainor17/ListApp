package com.voronin.listapp.postDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.voronin.listapp.R
import com.voronin.listapp.postDetail.viewModel.PostDetailViewModel
import com.voronin.listapp.databinding.PostDetailFragmentBinding

/**
 * Open after item in PostListFragment selected
 */
class PostDetailFragment : Fragment() {

    private lateinit var viewModel: PostDetailViewModel
    private lateinit var dataBinding: PostDetailFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PostDetailViewModel::class.java)
        viewModel.refreshScreen()
        arguments.let { viewModel.initialize(it) }

        viewModel.liveData.observe(this) {
            dataBinding.post = it
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.post_detail_fragment, container, false)
        return dataBinding.root
    }
}
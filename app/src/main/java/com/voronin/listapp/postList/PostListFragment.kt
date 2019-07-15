package com.voronin.listapp.postList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.voronin.listapp.R
import com.voronin.listapp.postList.viewModel.ContributorListViewModel
import kotlinx.android.synthetic.main.post_list_fragment.*

class PostListFragment : Fragment() {

    private lateinit var viewModel: ContributorListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.post_list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ContributorListViewModel::class.java)
        viewModel.loadingLiveData.observe(this) {
            swipeRefresh.isRefreshing = it
        }
    }

    private fun initViews() {
        contributorList.itemAnimator = DefaultItemAnimator()
        contributorList.layoutManager = LinearLayoutManager(context)
        contributorList.adapter = viewModel.listAdapter

        swipeRefresh.setOnRefreshListener {
            viewModel.onSwipeRefresh()
        }
    }
}
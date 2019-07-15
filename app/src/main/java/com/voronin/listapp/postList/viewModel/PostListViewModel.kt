package com.voronin.listapp.postList.viewModel

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.os.bundleOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import androidx.recyclerview.widget.DiffUtil
import com.voronin.listapp.R
import com.voronin.listapp.app.App
import com.voronin.listapp.navActivity.viewModel.NavViewModel
import com.voronin.listapp.postList.adapters.PostListAdapter
import com.voronin.listapp.postList.client.PostClient
import com.voronin.listapp.postList.models.POST_TAG
import com.voronin.listapp.postList.models.Post
import com.voronin.listapp.utils.MainThreadExecutor
import kotlinx.coroutines.*
import org.kodein.di.generic.instance
import retrofit2.HttpException
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

const val PAGE_SIZE = 20

class ContributorListViewModel(app: Application) : AndroidViewModel(app) {

    private val context: Context = app

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext = parentJob + Dispatchers.Default

    private val client: PostClient by App.kodein.instance()
    private val navViewModel: NavViewModel by App.kodein.instance()

    val loadingLiveData = MutableLiveData<Boolean>()
    var listAdapter: PostListAdapter? = null
    var pagedList: PagedList<Post>? = null

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(PAGE_SIZE)
        .build()

    private val dataSource = object : PositionalDataSource<Post>() {
        override fun loadInitial(@NonNull params: LoadInitialParams, @NonNull callback: LoadInitialCallback<Post>) {
            loadPage(0) { callback.onResult(it, 0) }
        }

        override fun loadRange(@NonNull params: LoadRangeParams, @NonNull callback: LoadRangeCallback<Post>) {
            loadPage(params.startPosition / PAGE_SIZE) { callback.onResult(it) }
        }

        private fun loadPage(pageNumber: Int, callback: (List<Post>) -> Unit) {
            setLoading(true)
            CoroutineScope(coroutineContext).launch {
                try {
                    val list = client.getContributors(PAGE_SIZE, PAGE_SIZE * pageNumber)
                    callback.invoke(list)
                } catch (e: Exception) {
                    callback.invoke(ArrayList())
                    showErrorMessage(e)
                } finally {
                    setLoading(false)
                }
            }
        }
    }

    init {
        initList()
    }

    private fun showErrorMessage(e: Exception) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, getErrorMessage(e), Toast.LENGTH_SHORT).show()
        }
    }

    private fun getErrorMessage(e: Exception): String {
        if (e is HttpException) {
            return e.message!!
        }
        return context.getString(R.string.no_internet_connection)
    }

    private fun initList() {
        pageListInit()
        listAdapter = PostListAdapter(object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post) = oldItem == newItem
            override fun areContentsTheSame(oldItem: Post, newItem: Post) = oldItem == newItem
        })
        listAdapter?.onClickListener = { onContributorSelected(it) }
        listAdapter?.submitList(pagedList)
    }

    override fun onCleared() {
        super.onCleared()
        cancelAllRequests()
    }

    fun onSwipeRefresh() {
        pageListInit()
        listAdapter?.submitList(pagedList)
        setLoading(false)
    }

    private fun pageListInit() {
        pagedList = PagedList.Builder<Int, Post>(dataSource, config)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .setNotifyExecutor(MainThreadExecutor())
            .build()
    }

    private fun onContributorSelected(it: Post) {
        navViewModel.navigateScreen(
            R.id.action_to_detail,
            bundleOf(POST_TAG to it)
        )
    }

    private fun setLoading(isVisible: Boolean) {
        loadingLiveData.postValue(isVisible)
    }

    private fun cancelAllRequests() = coroutineContext.cancel()
}
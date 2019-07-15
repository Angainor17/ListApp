package com.voronin.listapp.navActivity

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.voronin.listapp.R
import com.voronin.listapp.app.App
import com.voronin.listapp.navActivity.viewModel.NavViewModel
import com.voronin.listapp.utils.KodeinViewModelFactory
import com.voronin.listapp.utils.getColoredVectorDrawable

import kotlinx.android.synthetic.main.activity_nav.*
import org.kodein.di.generic.instance

class NavActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var viewModel: NavViewModel

    private val viewModelFactory: KodeinViewModelFactory by App.kodein.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)
        initToolbar()

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        viewModelInit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun viewModelInit() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NavViewModel::class.java)
        viewModel.onScreenChange = { navController.navigate(it.screenId, it.params) }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id != R.id.postListScreen) {
                showBackBtn()
            } else {
                hideBackBtn()
            }
        }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        setTitle(R.string.posts)
    }

    private fun hideBackBtn() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun showBackBtn() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.navigationIcon = getColoredVectorDrawable(this, R.drawable.ic_arrow_back_24dp, Color.WHITE)
    }
}

class ScreenInfo(val screenId: Int, val params: Bundle? = null)

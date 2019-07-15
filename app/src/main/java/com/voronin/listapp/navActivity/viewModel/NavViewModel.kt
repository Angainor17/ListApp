package com.voronin.listapp.navActivity.viewModel

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.voronin.listapp.navActivity.ScreenInfo

class NavViewModel : ViewModel() {

    var onScreenChange: ((ScreenInfo) -> Unit)? = null

    fun navigateScreen(screen: Int, params: Bundle? = null) {
        onScreenChange?.invoke(ScreenInfo(screen, params))
    }
}
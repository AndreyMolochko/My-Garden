package com.ostrovec.mygarden.ui.base.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivityBaseNavigationBinding

open class BaseNavigationActivity : BaseActivity() {

    private lateinit var binding: ActivityBaseNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_base_navigation)
        initToolbar()
    }

    override fun onDestroy() {
        super.onDestroy()

        binding.unbind()
    }

    protected fun <T : ViewDataBinding> setContainerView(layoutId: Int): T {
        val containerView = findViewById<ViewGroup>(R.id.base_navigation_container_frame_layout)
        val inflatedView = View.inflate(this, layoutId, null)
        containerView.addView(inflatedView)

        return DataBindingUtil.bind(inflatedView)!!
    }

    protected fun initSettingsButton(clickListener: View.OnClickListener?){
        binding.baseNavigationToolbar.setVisibleSettingsButton()
        binding.baseNavigationToolbar.setSettingsButtonListener(clickListener)
    }

    private fun initToolbar() {
        binding.baseNavigationToolbar.setBackButtonClickListener(backButtonListener())
    }

    private fun backButtonListener(): View.OnClickListener {
        return View.OnClickListener {
            finish()
        }
    }

}
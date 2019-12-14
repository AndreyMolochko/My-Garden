package com.ostrovec.mygarden.ui.guide

import android.content.Context
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivityGuideBinding
import com.ostrovec.mygarden.ui.base.activity.BaseActivity
import com.ostrovec.mygarden.ui.settings.SettingsViewModel
import com.ostrovec.mygarden.ui.welcome.WelcomeActivity
import com.ostrovec.mygarden.utils.SharedPrefsWorker

class GuideActivity : BaseActivity() {

    val guideHandler: GuideHandler = object : GuideHandler {
        override fun onClickOK(context: Context) {
            settingsViewModel.loadSettings()
            WelcomeActivity.open(context)
            SharedPrefsWorker(context).setShownGuideActivity(false)
        }

    }

    private lateinit var binding: ActivityGuideBinding
    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!SharedPrefsWorker(this).shownGuide()) {
            WelcomeActivity.open(this@GuideActivity)
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_guide)
        settingsViewModel = getViewModel(SettingsViewModel::class.java)
        initViews()
    }

    private fun initViews() {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        binding.guideViewPager.adapter = viewPagerAdapter
        viewPagerAdapter.setPages(getPagesFragments())
        binding.guideTabLayout.setupWithViewPager(binding.guideViewPager)
        binding.handler = guideHandler
    }

    private fun getPagesFragments(): List<Fragment> {
        val guideSignUpFragment = PageFragment.getNewInstance(R.string
                .guide_text_sign_up,
                R.drawable.ic_log_in)
        val guideAddPlantsFragment = PageFragment.getNewInstance(R.string
                .guide_text_add_plants,
                R.drawable.ic_flower_pot)
        val guideNotificationFragment = PageFragment.getNewInstance(R.string
                .guide_text_turn_notification,
                R.drawable.ic_message)
        return arrayListOf(guideSignUpFragment, guideAddPlantsFragment, guideNotificationFragment)
    }
}
